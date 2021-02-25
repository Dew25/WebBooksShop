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
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;
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
   
    "/takeOnBookForm",
    "/takeOnBook",
    "/returnBookForm",
    "/returnBook",
    
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
            case "/takeOnBookForm":
                request.setAttribute("activeTakeOnBook", "true");
                List<Book> listBooks = bookFacade.findAll();
                request.setAttribute("listBooks", listBooks);
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("takeOnBook")).forward(request, response);
                break;
            case "/takeOnBook":
                String bookId = request.getParameter("bookId");
               
                if("".equals(bookId) || bookId == null){
                    request.setAttribute("info", "Выберите книгу или читателя.");
                    request.getRequestDispatcher("/takeOnBookForm").forward(request, response);
                    break;
                }
                Book book = bookFacade.find(Long.parseLong(bookId));
                Reader reader = readerFacade.find(user.getReader().getId());
                History history = new History(book, reader, new GregorianCalendar().getTime(), null);
                historyFacade.create(history);
                request.setAttribute("info", "Книга "
                                                +history.getBook().getName()
                                                +" выдана читателю "
                                                +history.getReader().getFirstname() 
                                                + " "
                                                +history.getReader().getLastname());
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/returnBookForm":
                request.setAttribute("activeReturnBook", "true");
                List<History> listHistoriesWithReadingBooks = historyFacade.findReadingBooks(user.getReader());
                if(listHistoriesWithReadingBooks == null){
                    request.setAttribute("info", "Нет читаемых книг");
                    request.getRequestDispatcher(LoginServlet.pathToFile.getString("returnBook")).forward(request, response);
                    break;
                }
                request.setAttribute("listHistoriesWithReadingBooks", listHistoriesWithReadingBooks);
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("returnBook")).forward(request, response);
                break;
            case "/returnBook":
                String historyId = request.getParameter("historyId");
                if("".equals(historyId) || historyId == null){
                    request.setAttribute("info", "Выберите возвращаемую книгу.");
                    request.getRequestDispatcher("/returnBookForm").forward(request, response);
                    break;
                }
                history = historyFacade.find(Long.parseLong(historyId));
                history.setReturnDate(new GregorianCalendar().getTime());
                historyFacade.edit(history);
                request.setAttribute("info", "Возвращена книга: "+ history.getBook().getName());
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("index")).forward(request, response);
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
