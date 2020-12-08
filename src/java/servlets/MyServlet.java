/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Book;
import entity.History;
import entity.Reader;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.BookFacade;
import session.HistoryFacade;
import session.ReaderFacade;

/**
 *
 * @author Melnikov
 */
@WebServlet(name = "MyServlet", urlPatterns = {
    "/addBook",
    "/createBook",
    "/addReader",
    "/createReader",
    "/listBooks",
    "/listReaders",
    "/takeOnBookForm",
    "/takeOnBook",
    "/returnBookForm",
    "/returnBook",
    
})
public class MyServlet extends HttpServlet {
    @EJB
    private BookFacade bookFacade;
    @EJB
    private ReaderFacade readerFacade;
    @EJB
    private HistoryFacade historyFacade;
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
        
        String path = request.getServletPath();
        switch (path) {
            case "/addBook":
                request.getRequestDispatcher("/WEB-INF/addBookForm.jsp").forward(request, response);
                break;
            case "/createBook":
                String name = request.getParameter("name");
                String author = request.getParameter("author");
                String publishedYear = request.getParameter("publishedYear");
                String isbn = request.getParameter("isbn");
                request.setAttribute("info", 
                        "Добавлена книга "+name+
                        ", автор: " + author +
                        ", год издания: "+ publishedYear        
                );
                Book book = new Book(name, author, Integer.parseInt(publishedYear), isbn);
                bookFacade.create(book);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/addReader":
                request.getRequestDispatcher("/WEB-INF/addReaderForm.jsp").forward(request, response);
                break;
            case "/createReader":
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String phone = request.getParameter("phone");
               
                request.setAttribute("info", 
                        "Читатель "+firstname+" добавлен"     
                );
                Reader reader = new Reader(firstname, lastname, phone);
                readerFacade.create(reader);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/listBooks":
                List<Book> listBooks = bookFacade.findAll();
                request.setAttribute("listBooks", listBooks);
                request.getRequestDispatcher("/WEB-INF/listBooks.jsp").forward(request, response);
                break;
            case "/listReaders":
                List<Reader> listReaders = readerFacade.findAll();
                request.setAttribute("listReaders", listReaders);
                request.getRequestDispatcher("/WEB-INF/listReaders.jsp").forward(request, response);
                break;
            case "/takeOnBookForm":
                listBooks = bookFacade.findAll();
                request.setAttribute("listBooks", listBooks);
                listReaders = readerFacade.findAll();
                request.setAttribute("listReaders", listReaders);
                request.getRequestDispatcher("/WEB-INF/takeOnBookForm.jsp").forward(request, response);
                break;
            case "/takeOnBook":
                String bookId = request.getParameter("bookId");
                String readerId = request.getParameter("readerId");
                if("".equals(bookId) || bookId == null
                        || "".equals(readerId) || readerId == null){
                    request.setAttribute("info", "Выберите книгу или читателя.");
                    request.getRequestDispatcher("/takeOnBookForm").forward(request, response);
                    break;
                }
                book = bookFacade.find(Long.parseLong(bookId));
                reader = readerFacade.find(Long.parseLong(readerId));
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
                List<History> listHistoriesWithReadingBooks = historyFacade.findReadingBooks();
                if(listHistoriesWithReadingBooks == null){
                    request.setAttribute("info", "Нет читаемых книг");
                    request.getRequestDispatcher("/WEB-INF/returnBookForm.jsp").forward(request, response);
                    break;
                }
                request.setAttribute("listHistoriesWithReadingBooks", listHistoriesWithReadingBooks);
                request.getRequestDispatcher("/WEB-INF/returnBookForm.jsp").forward(request, response);
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
                request.getRequestDispatcher("/index.jsp").forward(request, response);
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
