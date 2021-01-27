<%-- 
    Document   : loginForm
    Created on : 11.01.2021, 13:43:50
    Author     : jvm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <h2>Введите логин и пароль</h2>
    <p class="info">${info}</p>
    <a href="index.jsp">Главная страница</a>
    <form action="login" method="POST">
        Логин: <input type="text" name="login">
        <br>
        Пароль: <input type="password" name="password">
        <br>
        <input type="submit" value="Войти">
    </form>
    
