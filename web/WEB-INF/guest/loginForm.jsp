<%-- 
    Document   : loginForm
    Created on : 11.01.2021, 13:43:50
    Author     : jvm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="mx-auto mt-5  p-3" style="width: 30rem">
        <h3 class="w-100 m-4 text-center">Введите логин и пароль</h3>
        <form action="login" method="POST">
          <div class="row m-2 ">
            <label for="login" class="col-sm-2 col-form-label">Логин</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="login" name="login" value="${login}">
            </div>
          </div>
          <div class="row m-2 ">
            <label for="password" class="col-sm-2 col-form-label">Пароль</label>
            <div class="col-sm-10">
              <input type="password" class="form-control" id="password" name="password" value="${password}">
            </div>
          </div>
          <div class=" mt-4 m-2 w-100 row">
            <input type="submit" value="Войти" class="mx-auto col-4 btn-primary" style="width: 16rem">
          </div>
        </form>
    </div>
    
