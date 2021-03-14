/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Book;
import entity.History;
import entity.Reader;
import entity.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Stream;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.BookFacade;
import session.HistoryFacade;
import session.ReaderFacade;
import session.UserFacade;
import session.UserRolesFacade;

/**
 *
 * @author Melnikov
 */
@WebServlet(name = "ReaderServlet", urlPatterns = {
    "/addToBasket",
    "/removeBookFromBasket",
    "/showBasket",
    "/buyBooks",
    "/purchasedBooks",
    "/editProfile",
    "/changeProfile",
    "/readBook",
})
public class ReaderServlet extends HttpServlet {
    @EJB
    private BookFacade bookFacade;
    @EJB
    private ReaderFacade readerFacade;
    @EJB
    private HistoryFacade historyFacade;
    @EJB
    private UserFacade userFacade;
    @EJB private UserRolesFacade userRolesFacade;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if(session == null){
            request.setAttribute("info", "У вас нет права использовать этот ресурс. Войдите!");
            request.getRequestDispatcher("/loginForm").forward(request, response);
            return;
        }
        User user = (User) session.getAttribute("user");
        if (user == null){
            request.setAttribute("info", "У вас нет права использовать этот ресурс. Войдите!");
            request.getRequestDispatcher("/loginForm").forward(request, response);
            return;
        }
        boolean isRole = userRolesFacade.isRole("READER",user);
        if(!isRole){
            request.setAttribute("info", "У вас нет права использовать этот ресурс. Войдите с соответствующими правами!");
            request.getRequestDispatcher("/loginForm").forward(request, response);
            return;
        }
        request.setAttribute("role", userRolesFacade.getTopRoleForUser(user));
        String path = request.getServletPath();
        switch (path) {
            case "/addToBasket":
                String bookId = request.getParameter("bookId");
                if("".equals(bookId) || bookId==null){
                    request.setAttribute("info", "Что то пошло не так");
                    request.getRequestDispatcher("/listBooks").forward(request, response);
                    break;
                }
                Book book = bookFacade.find(Long.parseLong(bookId));
                List<Book> basketList = (List<Book>) session.getAttribute("basketList");
                if(basketList == null) basketList = new ArrayList<>();
                basketList.add(book);
                session.setAttribute("basketList", basketList);
                request.setAttribute("basketListCount", basketList.size());
                request.getRequestDispatcher("/listBooks").forward(request, response);
                break;
            case "/removeBookFromBasket":
                bookId = request.getParameter("bookId");
                if("".equals(bookId) || bookId==null){
                    request.setAttribute("info", "Что то пошло не так");
                    request.getRequestDispatcher("/showBasket").forward(request, response);
                    break;
                }
                book = bookFacade.find(Long.parseLong(bookId));
                basketList = (List<Book>) session.getAttribute("basketList");
                if(basketList.contains(book)){
                    basketList.remove(book);
                    session.setAttribute("basketList", basketList);
                }
                request.setAttribute("basketListCount", basketList.size());
                request.getRequestDispatcher("/showBasket").forward(request, response);
                break;
            case "/showBasket":
                List<Book> listBooksInBasket = (List<Book>) session.getAttribute("basketList");
                request.setAttribute("listBooksInBasket", listBooksInBasket);
                if(listBooksInBasket == null || listBooksInBasket.isEmpty()){
                    request.getRequestDispatcher("/listBooks").forward(request, response);
                    break;
                }
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("showBasket")).forward(request, response);
                break;
            case "/buyBooks":
                //Получаем список книг в корзине из сессии
                listBooksInBasket = (List<Book>) session.getAttribute("basketList");
                //Получаем массив отмеченных для покупки книг в корзине или нажатия ссылки при прочтении отрывка
                String[] selectedBooks = request.getParameterValues("selectedBooks");
                if(selectedBooks == null){
                    request.setAttribute("info", "Чтобы купить выберите книгу.");
                    request.getRequestDispatcher("/listBooks").forward(request, response);
                    break;
                }
                int userMoney = user.getReader().getMoney();
                List<Book> buyBooks = new ArrayList<>();
                int totalPricePurchase = 0;
                //Считаем стоимость покупаемых книг, которые отмечены в корзине
                for(String selectedBookId : selectedBooks){
                    Book b = bookFacade.find(Long.parseLong(selectedBookId));
                    totalPricePurchase += b.getPrice();
                    buyBooks.add(b);
                }
                if(totalPricePurchase > userMoney){
                    request.setAttribute("info", "Недостаточно денег для покупки");
                    request.getRequestDispatcher("/listBooks").forward(request, response);
                    break;
                }
                //Покупаем книгу
                for(Book buyBook : buyBooks){
                    if(listBooksInBasket != null) listBooksInBasket.remove(buyBook); //если запрос пришел из корзины - удаляем из корзины купленную книгу
                    historyFacade.create(new History(buyBook,user.getReader(), new GregorianCalendar().getTime(),null));
                }
                //Списываем у читателя деньги за купленные книги
                Reader r = readerFacade.find(user.getReader().getId());
                r.setMoney(r.getMoney()-totalPricePurchase);
                readerFacade.edit(r);
                //Редактируем данные вошедшего читателя в сессии
                User bUser = userFacade.find(user.getId());
                session.setAttribute("user", bUser);
                
                if(listBooksInBasket != null){
                    //есои запрос из корзины
                    request.setAttribute("listBooksInBasket", listBooksInBasket);
                    request.setAttribute("basker", listBooksInBasket.size());
                }
                request.setAttribute("info", "Куплено книг: "+selectedBooks.length);
                request.getRequestDispatcher("/listBooks").forward(request, response);
                break;
            case "/purchasedBooks":
                request.setAttribute("activePurchasedBooks", "true");
                List<Book> purchasedBooks = historyFacade.findPurchasedBook(user.getReader());
                request.setAttribute("listBooks", purchasedBooks);
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("purchasedBooks")).forward(request, response);
                break;
            case "/editProfile":
                user = (User) session.getAttribute("user");
                request.setAttribute("user", user);
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("editProfile")).forward(request, response);
                break;
            case "/changeProfile":
                User pUser = userFacade.find(user.getId());
                Reader pReader = readerFacade.find(user.getReader().getId());
                String firstname = request.getParameter("firstname");
                if(pReader != null && !"".equals(firstname)) pReader.setFirstname(firstname);
                String lastname = request.getParameter("lastname");
                if(pReader != null && !"".equals(lastname)) pReader.setLastname(lastname);
                String phone = request.getParameter("phone");
                if(pReader != null && !"".equals(phone)) pReader.setPhone(phone);
                String money = request.getParameter("money");
                if(pReader != null && !"".equals(money)) pReader.setMoney(money);
                String login = request.getParameter("login");
                if(pUser != null && !"".equals(login)) pUser.setLogin(login);
                String password = request.getParameter("password");
                if(pUser != null && !"".equals(password)){
                    //здесь шифруем пароль и получаем соль
                    pUser.setPassword(password);
                    //user.setSalt(salt);
                    
                }
                readerFacade.edit(pReader);
                pUser.setReader(pReader);
                userFacade.edit(pUser);
                session.setAttribute("user", null);//эта строка может быть избыточной
                session.setAttribute("user", pUser);
                session.setAttribute("info", "Профиль читателя изменен");
                request.getRequestDispatcher("/editProfile").forward(request, response);
                break;
            case "/readBook":
                bookId = request.getParameter("bookId");
                if(bookId == null || "".equals(bookId)){
                    request.setAttribute("info","Выберите книгу" );
                    request.getRequestDispatcher("/purchasedBooks").forward(request, response);
                }
                book = bookFacade.find(Long.parseLong(bookId));
                List<Book> buyBooksList = historyFacade.findPurchasedBook(user.getReader());
                try {
                    File file = new File(book.getText().getPath());
                    FileReader fileReader = new FileReader(file);
                    BufferedReader reader = new BufferedReader(fileReader);
                    try (PrintWriter out = response.getWriter()) {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
                        out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1\" crossorigin=\"anonymous\">");
                        out.println("<head>");
                        out.println("<title>"+book.getName()+"</title>");            
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<div class=\"container\">");
                        out.println("</p>");
                        if(buyBooksList.contains(book)){//если список купленных пользователем книг СОДЕРЖИТ книгу
                            try(Stream<String> stream = Files.lines(file.toPath(), StandardCharsets.UTF_8)){
                                stream.forEachOrdered(line -> out.print(line));
                            }
                        }else{//если список купленных пользователем книг НЕ СОДЕРЖИТ книгу
                            try (Stream<String> lines = Files.lines (file.toPath(), StandardCharsets.UTF_8)){
                                int numLine = 0;
                                for (String line : (Iterable<String>) lines::iterator)
                                {
                                    out.print(line);
                                    numLine++;
                                    if(numLine > 200) break;
                                }
                            }
                            out.println("... ");
                            out.println("<br>");
                            out.println("<p class=\"w-100 d-flex justify-content-center\"><a href=\"buyBooks?selectedBooks="+book.getId()+"\">(Для продолжения чтения купите книгу).</a></p>");
                            out.println("</p>");
                        }
                        out.println("</p>");
                        out.println("</div>");
                        out.println("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW\" crossorigin=\"anonymous\"></script>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                    
                } catch (Exception e) {
                    request.setAttribute("info", "Невозможно прочесть файл");
                    request.getRequestDispatcher("/listBooks").forward(request, response);
                }
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
