<%-- 
    Document   : page1
    Created on : Nov 25, 2020, 10:14:04 AM
    Author     : Melnikov
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Возвращение книги</title>
  </head>
  <body>
    <h1>Возвращение книги</h1>
    <p>${info}</p>
    <br>
    <a href="index.jsp">Главная страница</a>
    <form action="returnBook" method="POST">
        <h1>Список читаемых книг</h1>
        <select name="historyId">
            <option value="">Выберите возвращаемую книгу</option>
            <c:forEach var="history" items="${listHistoriesWithReadingBooks}">
                <option value="${history.id}">"${history.book.name}" читает ${history.reader.firstname} ${history.reader.lastname}</option>
            </c:forEach>
        </select>
        <input type="submit" name="submit" value="Вернуть книгу">
    </form>
  </body>
</html>
