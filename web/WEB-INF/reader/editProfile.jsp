<%-- 
    Document   : page1
    Created on : Nov 25, 2020, 10:14:04 AM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
   <h3 class="w-100 my-5 text-center">Профиль читателя</h3>
    <div class="w-50 d-flex justify-content-center mx-auto">
      <form class="row g-2" action="changeProfile" method="POST">
        <div class="col-md-6">
          <label for="firstname" class="form-label">Имя</label>
          <input type="text" class="form-control" id="firstname" name="firstname" value="${user.reader.firstname}">
        </div>
        <div class="col-md-6">
          <label for="lastname" class="form-label">Фамилия</label>
          <input type="text" class="form-control" id="lastname" name="lastname"  value="${user.reader.lastname}">
        </div>
        <div class="col-md-6">
          <label for="phone" class="form-label">Телефон</label>
          <input type="text" class="form-control" id="phone" name="phone"  value="${user.reader.phone}">
        </div>
        <div class="col-md-6">
          <label for="money" class="form-label">Деньги</label>
          <input type="text" class="form-control" id="money" name="money"  value="${user.reader.money/100}">
        </div>
        <div class="col-md-6">
          <label for="login" class="form-label">Логин</label>
          <input readonly type="text" class="form-control" id="login" name="login"  value="${user.login}">
        </div>
        <div class="col-md-6">
          <label for="password" class="form-label">Пароль</label>
          <input type="password" class="form-control" id="password" name="password"  value="">
        </div>
        <div class="col-12 text-center">
            <input type="submit" class="btn btn-primary w-25" name="submit" value="Изменить">
        </div>
      </form>
    </div>

