/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonservlets;

import entity.Book;
import entity.Cover;
import entity.Text;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import jsonbuilders.JsonBookBuilder;
import session.BookFacade;
import session.CoverFacade;
import session.TextFacade;

/**
 *
 * @author jvm
 */
@WebServlet(name = "ManagerServletJson", urlPatterns = {
  "/createBookJson",

})
@MultipartConfig
public class ManagerServletJson extends HttpServlet {
    @EJB private CoverFacade coverFacade;
    @EJB private TextFacade textFacade;
    @EJB private BookFacade bookFacade;
    
    public static final ResourceBundle pathToFile = ResourceBundle.getBundle("property.pathToFile");
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
    String json = null;
    JsonObjectBuilder job = Json.createObjectBuilder();
    String uploadFolder = ManagerServletJson.pathToFile.getString("dir");
    String path = request.getServletPath();
    switch (path) {
        case "/createBookJson":
          List<Part> fileParts = request
                  .getParts()
                  .stream()
                  .filter(part -> "file".equals(part.getName()))
                  .collect(Collectors.toList());
            Set<String> imagesExtensions = new HashSet<>();
            imagesExtensions.add("jpg");
            imagesExtensions.add("png");
            imagesExtensions.add("gif");
            String fileFolder = "";
            Book book = null;
            Cover cover = null;
            Text text = null;
            for(Part filePart : fileParts){
                String fileName = getFileName(filePart);
                String fileExtension = fileName.substring(fileName.length()-3,fileName.length());
                if(imagesExtensions.contains(fileExtension)){
                    fileFolder = "images";
                }else{
                    fileFolder = "texts";
                }
                StringBuilder sbFullPathToFile = new StringBuilder();
                sbFullPathToFile.append(uploadFolder)
                        .append(File.separator)
                        .append(fileFolder)
                        .append(File.separator)
                        .append(fileName);
                File file = new File(sbFullPathToFile.toString());
                file.mkdirs();
                try(InputStream fileContent = filePart.getInputStream()){
                    Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                if("images".equals(fileFolder)){
                    cover = new Cover(fileName,sbFullPathToFile.toString());
                    coverFacade.create(cover);
                }else{
                    text = new Text(fileName, sbFullPathToFile.toString());
                    textFacade.create(text);
                }
            }
            if(cover == null || text == null){
                json=job.add("requestStatus", "false")
                    .add("info", "Выберите файл обложки и текст книги")
                    .build()
                    .toString();
                break;   
            }
            String name = request.getParameter("name");
            String author = request.getParameter("author");
            String publishedYear = request.getParameter("publishedYear");
            String isbn = request.getParameter("isbn");
            String price = request.getParameter("price");
            if(name == null || "".equals(name)
                  || author == null || "".equals(author)
                  || publishedYear == null || "".equals(publishedYear)
                  || isbn == null || "".equals(isbn)
                  || price == null || "".equals(price)
                  ){
                json=job.add("requestStatus", "false")
                        .add("info", "Заполните все поля")
                        .build()
                       .toString();
                break;  
            }
            book = new Book(name, author, Integer.parseInt(publishedYear), isbn, price, cover, text);
            bookFacade.create(book);
            JsonBookBuilder jbb = new JsonBookBuilder();
            JsonObject jsonBook = jbb.createJsonBook(book);
            json=job.add("requestStatus", "true")
                        .add("info", "Добавлена книга \""+book.getName()+"\".")
                        .add("book", jsonBook)
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
