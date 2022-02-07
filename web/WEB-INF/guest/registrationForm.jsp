<%-- 
    Document   : page1
    Created on : Nov 25, 2020, 10:14:04 AM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
   <h3 class="w-100 my-5 text-center">Редактировать профайл пльзователя</h3>
   <div class="w-100 d-flex justify-content-center m-2">
    <form action="createUser" method="POST">
      Имя читателя <input type="text" name="firstname" id="firstname"  value="${user.reader.firstname}"><br>
      Фамилия читателя <input type="text" name="lastname" id="lastname"  value="${user.reader.lastname}"><br>
      Телефон <input type="text" name="phone" id="phone"  value="${user.reader.phone}"><br>
      Деньги <input type="text" name="money" id="money"  value="${user.reader.money}"><br>
      Логин <input type="text" name="login" id="login"  value="${user.login}"><br>
      Пароль <input type="text" name="password" id="password" value=""><br>
      <input type="submit" name="submit" value="Зарегистрировать">
    </form>
   </div>
  
