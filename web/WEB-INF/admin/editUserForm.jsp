<%-- 
    Document   : page1
    Created on : Nov 25, 2020, 10:14:04 AM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
   <h3 class="w-100 my-5 text-center">Профиль читателя</h3>
   <div class="w-100 d-flex justify-content-center m-2">
    <form action="changeUser" method="POST">
                   <input type="hidden" name="userId" value="${user.id}">
      Имя читателя <input type="text" name="firstname" value="${user.reader.firstname}"><br>
      Фамилия читателя <input type="text" name="lastname"  value="${user.reader.lastname}"><br>
      Телефон <input type="text" name="phone"  value="${user.reader.phone}"><br>
      Деньги <input type="text" name="money"  value="${user.reader.money/100}"><br>
      Логин <input type="text" name="login"  value="${user.login}"><br>
      Пароль <input type="text" name="password" value=""><br>
      <input type="submit" name="submit" value="Изменить">
    </form>
   </div>
  
