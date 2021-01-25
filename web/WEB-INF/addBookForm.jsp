<%-- 
    Document   : page1
    Created on : Nov 25, 2020, 10:14:04 AM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <h1>Добавить новую книгу</h1>
    <p>${info}</p>
    <br>
    <a href="index.jsp">Главная страница</a>
    <form action="createBook" method="POST">
      Название книги <input type="text" name="name"><br>
      Автор книги <input type="text" name="author"><br>
      Год издания книги <input type="text" name="publishedYear"><br>
      ISBN <input type="text" name="isbn"><br>
      <input type="submit" name="submit" value="Добавить">
    </form>
 