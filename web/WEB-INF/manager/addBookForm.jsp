<%-- 
    Document   : page1
    Created on : Nov 25, 2020, 10:14:04 AM
    Author     : Melnikov
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <h3 class="w-100 text-center my-5 ">Добавить новую книгу</h3>
    <div class="row w-100"><a href="uploadForm" class="col-4 mx-auto">Загрузить обложку для книги</a></p>
    <form action="createBook" method="POST">
      <div class="row w-50 my-2 mx-auto">
        <div class="col-4 text-end">
            Название книги 
        </div>
        <div class="col-8 text-start ">
          <input class="col-9" type="text" name="name">
        </div>
      </div>
      <div class="row w-50 my-2 mx-auto">
        <div class="col-4 text-end">
          Автор книги 
        </div>
        <div class="col-8 text-start">  
          <input class="col-7" type="text" name="author">
        </div>
      </div>
      <div class="row w-50 my-2 mx-auto">
        <div class="col-4 text-end">   
            Год издания книги 
        </div>
        <div class="col-8 text-start">  
          <input class="col-4" type="text" name="publishedYear">
        </div>
      </div>
      <div class="row w-50 my-2 mx-auto">
        <div class="col-4 text-end">
            Обложка 
        </div>
        <div class="col-8 text-start">     
          <select class="form-select" name="coverId" aria-label="Выбрать обложку">
            <option selected>Open this select menu</option>
            <c:forEach var="cover" items="${listCovers}">
                  <option value="${cover.id}">${cover.description}</option>
            </c:forEach>
          </select>
        </div>
      </div>
     
        <div class="col mt-3 text-center">    
          <input class="col-2 bg-primary text-white" type="submit" name="submit" value="Добавить">
        </div>
      
    </form>
 