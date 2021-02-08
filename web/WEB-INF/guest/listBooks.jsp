<%-- 
    Document   : listBooks
    Created on : 04.12.2020, 10:03:17
    Author     : jvm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h3 class="w-100 my-5 text-center">Список книг</h3>
       
        <ol class="w-50 mx-auto border">
            <c:forEach var="book" items="${listBooks}">
                <li><a href="readBook?bookId=${book.id}">"${book.name}". ${book.author}. ${book.publishedYear}</a></li>
            </c:forEach>
        </ol>
    
