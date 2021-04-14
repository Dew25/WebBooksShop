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
    <div class="card m-2" style="max-width: 12rem; max-height: 25rem; border:0">
        <p class="card-text text-danger w-100 d-flex justify-content-center"><c:if test="${book.discount > 0}">Скидка ${book.discount}%!</c:if>&nbsp;</p>
        <img src="insertFile/${book.cover.path}" class="card-img-top" style="max-width: 12rem; max-height: 15rem" alt="...">
        <div class="card-body">
          <h5 class="card-title m-0">${book.name}</h5>
          <p class="card-text m-0">${book.author}</p>
          <p class="card-text m-0">${book.publishedYear}</p>
          <c:if test="${book.discount <= 0 || book.discountDate > today}">
              <p class="card-text m-0">${book.price/100} EUR</p>
          </c:if>
          <c:if test="${book.discount > 0 && book.discountDate < today}">
              <p class="card-text m-0 text-danger"><span class="text-decoration-line-through text-black-50">${book.price/100}</span> ${(book.price - book.price*book.discount/100)/100} EUR</p>
          </c:if>
          <p class="d-inline">
            <a href="readBook?bookId=${book.id}" class="link text-nowrap">Читать</a>
            <a href="addToBasket?bookId=${book.id}" class="link text-nowrap">В корзину</a>
          </p>
        </div>
    </div>
  </c:forEach>
        
    
</div>