/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Cover;
import entity.Text;
import entity.User;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import session.CoverFacade;
import session.TextFacade;
import session.UserRolesFacade;

/**
 *
 * @author Melnikov
 */
@WebServlet(name = "UploadServlet", urlPatterns = {
    "/uploadCoverForm",
    "/uploadCover",
    "/uploadTextForm",
    "/uploadText",
})
@MultipartConfig
public class UploadServlet extends HttpServlet {
    @EJB UserRolesFacade userRolesFacade;
    @EJB CoverFacade coverFacade;
    @EJB TextFacade textFacade;
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
        if(user == null){
            request.setAttribute("info", "У вас нет права использовать этот ресурс. Войдите!");
            request.getRequestDispatcher("/loginForm").forward(request, response);
            return;
        }
        boolean isRole = userRolesFacade.isRole("MANAGER",user);
        if(!isRole){
            request.setAttribute("info", "У вас нет права использовать этот ресурс. Войдите с соответствующими правами!");
            request.getRequestDispatcher("/loginForm").forward(request, response);
            return;
        }
        request.setAttribute("role", userRolesFacade.getTopRoleForUser(user));
        String uploadFolder = LoginServlet.pathToFile.getString("dir");
        String path = request.getServletPath();
        switch (path) {
            case "/uploadCoverForm":
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("uploadCoverForm")).forward(request, response);
                break;
            case "/uploadCover"://Можно отправлять несколько файлов в одной форме
                List<Part> fileParts = request
                        .getParts()
                        .stream()
                        .filter(part -> "file".equals(part.getName()))
                        .collect(Collectors.toList());
                StringBuilder sb = new StringBuilder();
                for(Part filePart : fileParts){
                    sb.append(uploadFolder)//указана в файле свойств
                      .append(File.separator)
                      .append("images")//директория с обложками
                      .append(File.separator)
                      .append(getFileName(filePart));// имя загружаемого файла
                    File file = new File(sb.toString());
                    file.mkdirs();
                    try(InputStream fileContent = filePart.getInputStream()){
                       Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                    String description = request.getParameter("description");
                    Cover cover = new Cover(description,sb.toString());
                    coverFacade.create(cover);
                }
                request.setAttribute("info", "Файлы загруженны");
                request.getRequestDispatcher("/addBook").forward(request, response);
                break;
            case "/uploadTextForm":
                request.getRequestDispatcher(LoginServlet.pathToFile.getString("uploadTextForm")).forward(request, response);
                break;
            case "/uploadText"://Можно отправлять несколько файлов в одной форме
                fileParts = request
                        .getParts()
                        .stream()
                        .filter(part -> "file".equals(part.getName()))
                        .collect(Collectors.toList());
                sb = new StringBuilder();
                for(Part filePart : fileParts){
                    sb.append(uploadFolder)//указана в файле свойств
                      .append(File.separator)
                      .append("texts")//директория с текстами
                      .append(File.separator)
                      .append(getFileName(filePart)); // имя загружаемого файла
                    File file = new File(sb.toString());
                    file.mkdirs();
                    try(InputStream fileContent = filePart.getInputStream()){
                       Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                    String description = request.getParameter("description");
                    Text text = new Text(description,sb.toString());
                    textFacade.create(text);
                }
                request.setAttribute("info", "Файлы загружены");
                request.getRequestDispatcher("/addBook").forward(request, response);
                break;
            
        }
    }
    private String getFileName(Part part){
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")){
            if(content.trim().startsWith("filename")){
                return content
                        .substring(content.indexOf('=')+1)
                        .trim()
                        .replace("\"",""); 
            }
        }
        return null;
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
