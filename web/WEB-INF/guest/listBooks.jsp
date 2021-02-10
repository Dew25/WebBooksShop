<%-- 
    Document   : listBooks
    Created on : 04.12.2020, 10:03:17
    Author     : jvm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h3 class="w-100 my-5 text-center">Список книг</h3>
<div class="w-100 d-flex justify-content-center m-2">
  <c:forEach var="book" items="${listBooks}">
    <div class="card m-2" style="max-width: 12rem; max-height: 15rem">
        <img src="..." class="card-img-top" style="max-width: 12rem; max-height: 15rem" alt="...">
        <div class="card-body">
          <h5 class="card-title">${book.name}</h5>
          <p class="card-text">${book.author}</p>
          <p class="card-text">${book.publishedYear}</p>
          <a href="#" class="btn btn-primary">Читать</a>
          <a href="#" class="btn btn-primary">Купить</a>
        </div>
    </div>
  </c:forEach>
        
    
</div>