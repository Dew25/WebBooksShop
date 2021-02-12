<%-- 
    Document   : page1
    Created on : Nov 25, 2020, 10:14:04 AM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
   <h3 class="w-100 my-5 text-center">Добавить читателя</h3>
   <div class="w-100 d-flex justify-content-center m-2">
    <form action="createUser" method="POST">
      Имя читателя <input type="text" name="firstname"><br>
      Фамилия читателя <input type="text" name="lastname"><br>
      Телефон <input type="text" name="phone"><br>
      Логин <input type="text" name="login"><br>
      Пароль <input type="text" name="password"><br>
      <input type="submit" name="submit" value="Добавить">
    </form>
   </div>
  
