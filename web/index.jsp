<%-- 
    Document   : page1
    Created on : Nov 25, 2020, 10:14:04 AM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Наша библиотека</title>
  </head>
  <body>
    <h1>Библиотека JPTVR19</h1>
    <p>${info}</p>
    <br>
    <a href="loginForm">Войти</a><br>
    <a href="logout">Выйти</a><br>
    <a href="addBook">Добавить книгу</a><br>
    <a href="registrationForm">Регистрация</a><br>
    <a href="listBooks">Список книг</a><br>
    <a href="listReaders">Список читателей</a><br>
    <a href="takeOnBookForm">Выдать книгу</a><br>
    <a href="returnBookForm">Вернуть книгу</a><br>
    <a href="adminForm">Панель администратора</a><br>
    
  </body>
</html>
