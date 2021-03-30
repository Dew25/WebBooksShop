<%-- 
    Document   : listBooks
    Created on : 04.12.2020, 10:03:17
    Author     : jvm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <h3 class="w-100 my-5 text-center">Список читателей</h3>
        <p class="">Всего пользователей: ${usersCount}<p>
        <table id="tableListReaders" class="table table-striped">
              <thead>
              <th>№</th>
              <th>Id</th>
              <th>Имя</th>
              <th>Фамилия</th>
              <th>Деньги</th>
              <th>Логин</th>
              <th>Роль1</th>
              <th>Роль2</th>
              <th>Роль3</th>
              <th>Активность</th>
              <th></th>
              </thead>
              <tbody>
                <c:forEach var="entry" items="${usersMapWithArrayRoles}" varStatus="status">
                    <tr>
                        <td>${status.index+1}</td>
                        <td>${entry.key.id}</td>
                        <td>${entry.key.reader.firstname}</td>
                        <td>${entry.key.reader.lastname}</td>
                        <td>${entry.key.reader.money/100}</td>
                        <td>${entry.key.login}</td>
                        <td><c:if test="${entry.value[0] ne ''}">${entry.value[0]}</c:if></td>
                        <td><c:if test="${entry.value[1] ne ''}">${entry.value[1]}</c:if></td>
                        <td><c:if test="${entry.value[2] ne ''}">${entry.value[2]}</c:if></td>
                        <td>Yes</td>
                        <td><a class="btn bg-primary text-white" href="editUser?userId=${entry.key.id}">Изменить</a></td>
                    </tr>
                </c:forEach>
                    
              </tbody>
          
        </table>
        
   
