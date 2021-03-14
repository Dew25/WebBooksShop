<%-- 
    Document   : takeOnBookForm
    Created on : 08.12.2020, 10:07:51
    Author     : jvm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h3 class="w-100 my-5 text-center">Список книг</h3>
    <div class="w-100 text-center">Список книг:</div>
    <div class="row w-100 d-flex justify-content-center">
        <c:forEach var="book" items="${listBooks}">
          <div class="card m-2" style="max-width: 12rem; max-height: 18rem" >
            <img src="insertFile/${book.cover.path}" class="card-img d-block" alt="..." style="min-width: 12rem; min-height: 18rem">
        
            <div class="card-body">
              <div class="card-title">${book.name}</div>
              <div class="card-text">${book.author}</div>
              <div class="card-text">${book.publishedYear}</div>
              <p class="d-flex justify-content-center"> 
                <a href="readBook?bookId=${book.id}" class="w-50 text-nowrap">Читать</a>
                <a href="addToBasket?bookId=${book.id}" class=" w-50 text-nowrap">В корзину</a>
              </p>
            </div>
          </div>
        </c:forEach>
    </div>
            
          
        

    
