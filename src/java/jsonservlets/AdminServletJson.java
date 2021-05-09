/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonservlets;

import entity.Role;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jsoncovertors.JsonUserBuilder;
import servlets.LoginServlet;
import session.BookFacade;
import session.UserFacade;
import session.UserRolesFacade;

/**
 *
 * @author jvm
 */
@MultipartConfig()
@WebServlet(name = "AdminServletJson", urlPatterns = {
  "/listUsersJson",

})
public class AdminServletJson extends HttpServlet {
    @EJB private UserFacade userFacade;
    @EJB private UserRolesFacade userRolesFacade;
    @EJB private BookFacade bookFacade;

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("UTF-8");
    HttpSession session = request.getSession(false);
    String uploadFolder = LoginServlet.pathToFile.getString("dir");
    String json = null;
//    JsonReader jsonReader = Json.createReader(request.getReader());
    JsonObjectBuilder job = Json.createObjectBuilder();
    JsonObject jsonObject = null;
    String path = request.getServletPath();
    switch (path) {
        case "/listUsersJson":
            response.setContentType("application/json"); 
            List<User> listUsers = userFacade.findAll();
            List<Role> listUserRoles = null;
            /*  [{
                    user:{userJson},
                    role:"READER"
                }]
            */
            JsonArrayBuilder jab = Json.createArrayBuilder();
            for(User user : listUsers){
                String role = userRolesFacade.getTopRoleForUser(user);
                jab.add(Json.createObjectBuilder()
                    .add("user", new JsonUserBuilder().createJsonUser(user))
                    .add("role",role).build()
                );
            }
            
            json=job.add("requestStatus", "true")
                        .add("info", "Список пользователей")
                        .add("listUsers", jab.build())
                        .build()
                        .toString();
            break;  
    }
    if(json == null && "".equals(json)){
        json=job.add("requestStatus", "false")
                    .add("info", "Ошибка обработки запроса")
                    .build()
                    .toString();
    }
    try (PrintWriter out = response.getWriter()) {
        out.println(json);
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
