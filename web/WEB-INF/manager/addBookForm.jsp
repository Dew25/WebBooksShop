<%-- 
    Document   : page1
    Created on : Nov 25, 2020, 10:14:04 AM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <h3 class="w-100 text-center my-5 ">Добавить новую книгу</h3>
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
      <div class="row my-4">
        <div class="text-center">    
          <input class="col-2" type="submit" name="submit" value="Добавить">
        </div>
      </div>
    </form>
 