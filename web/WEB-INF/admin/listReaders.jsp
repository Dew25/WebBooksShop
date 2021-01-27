<%-- 
    Document   : listBooks
    Created on : 04.12.2020, 10:03:17
    Author     : jvm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <h1>Список читателей</h1>
        <a href="index.jsp">Главная страница</a>
        <ol>
            <c:forEach var="reader" items="${listReaders}">
                <li>"${reader.firstname}". ${reader.lastname}. ${reader.phone}</li>
            </c:forEach>
        </ol>
   
