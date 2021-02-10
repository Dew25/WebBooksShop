<%-- 
    Document   : takeOnBookForm
    Created on : 08.12.2020, 10:07:51
    Author     : jvm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h3 class="w-100 my-5 text-center">Выдать книгу</h3>
<div class="w-100 d-flex justify-content-center m-2">
       
        <form action="takeOnBook" method="POST">
          <p class=""Список книг:>
          <p>
            <select class="form-control" name="bookId">
                <option value="">Выберите книгу</option>
                <c:forEach var="book" items="${listBooks}">
                    <option value="${book.id}">"${book.name}". ${book.author}. ${book.publishedYear}</option>
                </c:forEach>
            </select>
          </p>
          <p>
            <input class="btn btn-primary w-100" type="submit" value="Взять книгу">
          </p>
        </form>
</div>
    
