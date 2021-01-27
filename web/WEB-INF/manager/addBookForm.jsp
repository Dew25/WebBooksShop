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
      <div class="row m-2">
        <div class="col text-end">
            Название книги 
        </div>
        <div class="col text-start">
          <input type="text" name="name">
        </div>
      </div>
      <div class="row m-2">
        <div class="col text-end">
          Автор книги 
        </div>
        <div class="col text-start">  
          <input type="text" name="author">
        </div>
      </div>
      <div class="row m-2">
        <div class="col text-end">   
            Год издания книги 
        </div>
        <div class="col text-start">  
          <input type="text" name="publishedYear">
        </div>
      </div>
      <div class="row m-2">
        <div class="col text-end">
            ISBN 
        </div>
        <div class="col text-start">     
            <input type="text" name="isbn">
        </div>
      </div>
      <div class="row m-2">
        <div class="col text-center mt-3">    
            <input type="submit" name="submit" value="Добавить">
        </div>
      </div>
    </form>
 