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
    <title>Новая книга</title>
  </head>
  <body>
    <h1>Добавить новую книгу</h1>
    <p>${info}</p>
    <br>
    <a href="index.jsp">Главная страница</a>
    <form action="createBook" method="POST">
      Название книги <input type="text" name="name"><br>
      Автор книги <input type="text" name="author"><br>
      Год издания книги <input type="text" name="publishedYear"><br>
      <input type="submit" name="submit" value="Добавить">
    </form>
  </body>
</html>
