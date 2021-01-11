<%-- 
    Document   : loginForm
    Created on : 11.01.2021, 13:43:50
    Author     : jvm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Вход</title>
    </head>
    <body>
        <h1>Введите логин и пароль</h1>
        <p class="info">${info}</p>
        <form action="login" method="POST">
            Логин: <input type="text" name="login">
            <br>
            Пароль: <input type="password" name="password">
            <br>
            <input type="submit" value="Войти">
        </form>
    </body>
</html>
