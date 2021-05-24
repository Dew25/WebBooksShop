/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonservlets;

import entity.Reader;
import entity.Role;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
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
import session.ReaderFacade;
import session.RoleFacade;
import session.UserFacade;
import session.UserRolesFacade;
import tools.EncryptPassword;

/**
 *
 * @author jvm
 */
@MultipartConfig()
@WebServlet(name = "AdminServletJson", urlPatterns = {
  "/listUsersJson",
  "/getUserJson",
  "/editUserJson",
  "/listUsersWithRoleJson",
  "/listRolesJson",
  "/setRoleToUserJson",
  

})
public class AdminServletJson extends HttpServlet {
    @EJB private UserFacade userFacade;
    @EJB private ReaderFacade readerFacade;
    @EJB private UserRolesFacade userRolesFacade;
    @EJB private BookFacade bookFacade;
    @EJB private RoleFacade roleFacade;
    
    @Inject EncryptPassword ep;

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("UTF-8");
    String uploadFolder = LoginServlet.pathToFile.getString("dir");
    String json = null;
//    JsonReader jsonReader = Json.createReader(request.getReader());
    JsonObjectBuilder job = Json.createObjectBuilder();
    JsonObject jsonObject = null;
    HttpSession session = request.getSession(false);
        if(session == null){
            json=job.add("requestStatus", "false")
                    .add("info", "Авторизуйтесь")
                    .build()
                    .toString();
            try (PrintWriter out = response.getWriter()) {
                out.println(json);
            }
            return;
        }
        User user = (User) session.getAttribute("user");
        if(user == null){
            json=job.add("requestStatus", "false")
                    .add("info", "Авторизуйтесь")
                    .build()
                    .toString();
            
            try (PrintWriter out = response.getWriter()) {
                out.println(json);
            }
            return;
        }
        boolean isRole = userRolesFacade.isRole("ADMIN",user);
        if(!isRole){
            json=job.add("requestStatus", "false")
                    .add("info", "У вас недостаточно прав")
                    .build()
                    .toString();
            
            try (PrintWriter out = response.getWriter()) {
                out.println(json);
            }
            return;
        }
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
            for(User u : listUsers){
                String role = userRolesFacade.getTopRoleForUser(u);
                jab.add(Json.createObjectBuilder()
                    .add("user", new JsonUserBuilder().createJsonUser(u))
                    .add("role",role).build()
                );
            }
            
            json=job.add("requestStatus", "true")
                        .add("info", "Список пользователей")
                        .add("listUsers", jab.build())
                        .build()
                        .toString();
            break;  
        case "/getUserJson":
            JsonReader jsonReader = Json.createReader(request.getInputStream());
            jsonObject = jsonReader.readObject();
            long id = jsonObject.getInt("userId", -1);
            if(id < 0){
                break;
            }
            User editUser = userFacade.find(id);
            json = job.add("requestStatus", "true")
                      .add("info", "Профиль пользователя "+editUser.getLogin())
                      .add("user", new JsonUserBuilder().createJsonUser(editUser))
                      .build()
                      .toString();
            break;
        case "/editUserJson":
            jsonReader = Json.createReader(request.getInputStream());
            jsonObject = jsonReader.readObject();
            String userId = jsonObject.getString("userId", "");
            String firstname = jsonObject.getString("firstname", "");
            String lastname = jsonObject.getString("lastname", "");
            String phone = jsonObject.getString("phone", "");
            String money = jsonObject.getString("money", "");
            String login = jsonObject.getString("login", "");
            String password = jsonObject.getString("password", "");
            if("".equals(userId) || "".equals(firstname)
                    || "".equals(lastname) || "".equals(phone)
                    || "".equals(money) || "".equals(login)
                    || "".equals(userId) || "".equals(userId)
                    ){
                break;
            }
            editUser = userFacade.find(Long.parseLong(userId));
            if("admin".equals(editUser.getLogin()) && !"admin".equals(user.getLogin())){
                json=job.add("requestStatus", "false")
                    .add("info", "Изменения невозможны")
                    .build()
                    .toString();
                break;
            }
            Reader editReader = editUser.getReader();
            editReader.setFirstname(firstname);
            editReader.setLastname(lastname);
            editReader.setMoney(money);
            editReader.setPhone(phone);
           // editUser.setLogin(login);
            if(password != null && !password.isEmpty()){
                password = ep.createHash(password, editUser.getSalt());
                editUser.setPassword(password);
            }
            readerFacade.edit(editReader);
            userFacade.edit(editUser);
            json = job.add("requestStatus", "true")
                      .add("info", "Профиль пользователя "+editUser.getLogin()+" изменен.")
                      .add("userId", editUser.getId().toString())
                      .build()
                      .toString();
            break;
        case "/listUsersWithRoleJson":
            listUsers = userFacade.findAll();
            jab = Json.createArrayBuilder();
            job=Json.createObjectBuilder();
            for(User u: listUsers){
                String role = userRolesFacade.getTopRoleForUser(u);
                job.add("user", new JsonUserBuilder().createJsonUser(u));
                job.add("role", role);
                jab.add(job.build());
            }
            json = jab.build().toString();
            break;
        case "/listRolesJson":
            List<Role> listRoles = roleFacade.findAll();
            jab = Json.createArrayBuilder();
            job = Json.createObjectBuilder();
            
            for(Role r : listRoles){
               job.add("id", r.getId())
                       .add("roleName", r.getRoleName());
               jab.add(job.build());
            }
            json = jab.build().toString();
            break;
        case "/setRoleToUserJson":
            jsonReader = Json.createReader(request.getInputStream());
            jsonObject = jsonReader.readObject();
            long LuserId = jsonObject.getInt("userId");
            long LroleId = jsonObject.getInt("roleId");
            Role role = roleFacade.find(LroleId);
            userRolesFacade.setRole(role.getRoleName(), userFacade.find(LuserId));
            json = "{\"info\":\"Ok\"}";
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
