<%-- 
    Document   : listBooks
    Created on : 04.12.2020, 10:03:17
    Author     : jvm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <h3  class="w-100 my-5 text-center">Панель администратора</h3>
<div class="w-100 d-flex justify-content-center m-2">
        <form action="setRole" method="POST">
            <p>
                Список пользователей: 
                <select name="userId" class="form-select">
                    <option value=""  <c:if test="${userId == null}">selected</c:if>>Выберите пользователя</option>
                    <c:forEach var="entry" items="${usersMap}">
                        <option value="${entry.key.id}" <c:if test="${userId == entry.key.id}">selected</c:if>>${entry.key.reader.firstname} ${entry.key.reader.lastname}, логин: ${entry.key.login}, роль:  ${entry.value}</option>
                    </c:forEach>
                </select>
            </p>
            <p>
               Список ролей: 
               <select name="roleId" class="form-select">
                 <option value=""  <c:if test="${roleId == null}">selected</c:if>>Выберите роль</option>
                   <c:forEach var="role" items="${listRoles}">
                     <option value="${role.id}" <c:if test="${roleId == role.id}">selected</c:if>>${role.roleName}</option>
                   </c:forEach>
               </select>
            </p>
            <p><input class="btn btn-primary w-100" type="submit" value="Назначить роль пользователю"></p>
        </form>
</div>

