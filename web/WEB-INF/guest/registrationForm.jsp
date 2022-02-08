<%-- 
    Document   : page1
    Created on : Nov 25, 2020, 10:14:04 AM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
   <h3 class="w-100 my-5 text-center">Регистрация нового пользователя</h3>
   <div class="w-100 d-flex justify-content-center m-2">
       <div class="card border-0" style="width: 28rem;">
        <form action="createUser" method="POST">
            <div class="mb-3">
                <label for="firstname" class="form-label">Имя читателя</label>
                <input type="text" class="form-control" name="firstname" id="firstname" placeholder="" value="${user.reader.firstname}">
            </div>
            <div class="mb-3">
                <label for="lastname" class="form-label">Фамилия читателя</label>
                <input type="text" class="form-control" name="lastname" id="lastname"  value="${user.reader.lastname}">
            </div>
            <div class="mb-3">
                <label for="phone" class="form-label">Телефон</label>
                <input type="text" class="form-control" name="phone" id="phone"  value="${user.reader.phone}">
            </div>
            <div class="mb-3">
                <label for="money" class="form-label">Деньги</label>
                <input type="text" class="form-control" name="money" id="money"  value="${user.reader.money}">
            </div>
            <div class="mb-3">
                <label for="login" class="form-label">Логин</label>
               <input type="text" class="form-control" name="login" id="login"  value="${user.login}">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Пароль</label>
                <input type="text" class="form-control" name="password" id="password" value="">
            </div>
          
          <input type="submit" class="btn btn-primary" name="submit" value="Зарегистрировать">
        </form>
       </div>
   </div>
