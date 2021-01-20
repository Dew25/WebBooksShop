<%-- 
    Document   : listBooks
    Created on : 04.12.2020, 10:03:17
    Author     : jvm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Панель администратора</title>
    </head>
    <body>
        <h1>Панель администратора</h1>
        <form action="addNewRole" method="POST">
            <p>
                Список пользователей: 
                <select name="userId">
                    <option selected>Выберите пользователя</option>
                    <c:forEach var="entry" items="${usersMap}">
                        <option value="${entry.key.id}">${entry.key.reader.firstname} ${entry.key.reader.lastname}, Логин: ${entry.key.login}, роль:  ${entry.value}</option>
                    </c:forEach>
                </select>
            </p>
            <p>
               Список ролей: 
               <select name="roleId">
                   <option selected>Выберите роль</option>
                   <c:forEach var="role" items="${listRoles}">
                     <option value="${role.id}">${role.roleName}</option>
                   </c:forEach>
               </select>
            </p>
            <p><input type="submit" value="Назначить роль пользователю"></p>
        </form>
    </body>
</html>
