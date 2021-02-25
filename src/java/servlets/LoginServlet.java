/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Book;
import entity.Cover;
import entity.Reader;
import entity.Role;
import entity.User;
import entity.UserRoles;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.BookFacade;
import session.CoverFacade;
import session.ReaderFacade;
import session.RoleFacade;
import session.UserFacade;
import session.UserRolesFacade;

/**
 *
 * @author jvm
 */
@WebServlet(name = "LoginServlet", loadOnStartup = 1, urlPatterns = {
    "/index",
    "/loginForm",
    "/login",
    "/logout",
    "/registrationForm",
    "/createUser",
    "/listBooks",
})
public class LoginServlet extends HttpServlet {
@EJB
    private UserFacade userFacade;
@EJB
    private ReaderFacade readerFacade;
@EJB
    private BookFacade bookFacade;
@EJB private RoleFacade roleFacade;
@EJB private UserRolesFacade userRolesFacade;
@EJB private CoverFacade coverFacade;

public static final ResourceBundle pathToFile = ResourceBundle.getBundle("property.pathToFile");
        
    @Override
    public void init() throws ServletException {
        
       if(userFacade.findAll().size()>0) return;
        Reader reader = new Reader("Juri", "Melnikov", "56569987");
        readerFacade.create(reader);
        User user = new User("admin", "12345", reader);
        userFacade.create(user);
        Role role = new Role("ADMIN");
        roleFacade.create(role);
        UserRoles userRoles = new UserRoles(user, role);
        userRolesFacade.create(userRoles);
        role = new Role("MANAGER");
        roleFacade.create(role);
        userRoles = new UserRoles(user,role);
        userRolesFacade.create(userRoles);
        role = new Role("READER");
        roleFacade.create(role);
        userRoles = new UserRoles(user,role);
        userRolesFacade.create(userRoles);
        
    }



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
        User user=null;
        if(session != null){
            user = (User) session.getAttribute("user");
        }
        
        request.setAttribute("role", userRolesFacade.getTopRoleForUser(user));
    
        String path = request.getServletPath();
        switch (path) {
            case "/index":
                Map<Integer,List<String>> coversMap = new HashMap<>();
                List<String> listPaths = new ArrayList<>();
                List<Cover> listCovers = coverFacade.findAll();
                Integer key = 0;
                int n = 5;
                while(listCovers.size()>0){
                    listPaths = listCovers.stream()
                            .map(cover -> cover.getPath())
                            .limit(5)
                            .collect(Collectors.toList());
                    listCovers.removeIf(x -> )
                            .filter(x -> x.
                            .map(x -> listCovers.remove(x))
                            .collect(Collectors.toList());
                    coversMap.put(key,listPaths);
                    key++;
                }
                request.setAttribute("coversMap", coversMap);
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("login")).forward(request, response);
                break;
            case "/loginForm":
                request.setAttribute("activeEnter", "true");
                //response.sendRedirect(LoginServlet.pathToFile.getString("login"));
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("login")).forward(request, response);
                break;
            case "/login":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                if("".equals(login) || login == null
                       || "".equals(password) || password == null){
                    request.setAttribute("info","Заполните все поля");
                    request.getRequestDispatcher("/loginForm").forward(request, response);
                    break;
                }
                user = userFacade.findByLogin(login);
                if(user == null){
                    request.setAttribute("info","Нет такого пользователя");
                    request.getRequestDispatcher("/loginForm").forward(request, response);
                    break;
                }
                session = request.getSession(true);
                session.setAttribute("user", user);
                request.setAttribute("info","Вы вошли как "+ user.getLogin());
                request.setAttribute("role", userRolesFacade.getTopRoleForUser(user));
                
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("index")).forward(request, response);
                break;
            case "/logout":
                session = request.getSession(false);
                if(session != null){
                   session.invalidate();
                }
                request.setAttribute("info", "Вы вышли");
                request.setAttribute("role", userRolesFacade.getTopRoleForUser(null));
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("index")).forward(request, response);
                break;
            case "/registrationForm":
                request.setAttribute("activeRegistration", "true");
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("registration")).forward(request, response);
                break;
            case "/createUser":
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String phone = request.getParameter("phone");
                login = request.getParameter("login");
                password = request.getParameter("password");
                if("".equals(firstname) || firstname == null
                       || "".equals(lastname) || lastname == null
                       || "".equals(phone) || phone == null
                       || "".equals(login) || login == null
                       || "".equals(password) || password == null){
                    request.setAttribute("info","Заполните все поля");
                    request.getRequestDispatcher("/registrationForm").forward(request, response);
                    break;
                }
                
                Reader reader = new Reader(firstname, lastname, phone);
                readerFacade.create(reader);
                user = new User(login, password, reader);
                userFacade.create(user);
                //Здесь добавим роль пользователю.
                Role roleReader = roleFacade.findByName("READER");
                UserRoles userRoles = new UserRoles(user, roleReader);
                userRolesFacade.create(userRoles);
                request.setAttribute("info", 
                        "Читатель "+user.getLogin()+" добавлен"     
                );
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("index")).forward(request, response);
                break; 
            case "/listBooks":
                request.setAttribute("activeListBook", "true");
                List<Book> listBooks = bookFacade.findAll();
                request.setAttribute("listBooks", listBooks);
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("listBooks")).forward(request, response);
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
