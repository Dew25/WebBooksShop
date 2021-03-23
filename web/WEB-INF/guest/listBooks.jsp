<%-- 
    Document   : listBooks
    Created on : 04.12.2020, 10:03:17
    Author     : jvm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h3 class="w-100 my-5 text-center">Список книг</h3>

<div class="w-100 d-flex justify-content-center">
  <c:forEach var="book" items="${listBooks}">
    <div class="card m-2 border" style="max-width: 12rem; max-height: 25rem">
        <img src="insertFile/${book.cover.path}" class="card-img-top" style="max-width: 12rem; max-height: 15rem" alt="...">
        <div class="card-body">
          <h5 class="card-title">${book.name}</h5>
          <p class="card-text">${book.author}</p>
          <p class="card-text">${book.publishedYear}</p>
          <p class="d-inline">
            <a href="readBook?bookId=${book.id}" class="link text-nowrap">Читать</a>
            <a href="addToBasket?bookId=${book.id}" class="link text-nowrap">В корзину</a>
          </p>
        </div>
    </div>
  </c:forEach>
        
    
</div>