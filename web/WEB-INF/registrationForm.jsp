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
    <title>Новый читатель</title>
  </head>
  <body>
    <h1>Добавить читателя</h1>
    <p>${info}</p>
    <br>
    <a href="index.jsp">Главная страница</a>
    <form action="createUser" method="POST">
      Имя читателя <input type="text" name="firstname"><br>
      Фамилия читателя <input type="text" name="lastname"><br>
      Телефон <input type="text" name="phone"><br>
      Логин <input type="text" name="login"><br>
      Пароль <input type="text" name="password"><br>
      <input type="submit" name="submit" value="Добавить">
    </form>
  </body>
</html>
