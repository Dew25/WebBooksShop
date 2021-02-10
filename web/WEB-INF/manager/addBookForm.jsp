<%-- 
    Document   : page1
    Created on : Nov 25, 2020, 10:14:04 AM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <h3 class="w-100 text-center my-5 ">Добавить новую книгу</h3>
    <form action="createBook" method="POST">
      <div class="row w-50 my-2 mx-auto">
        <div class="col-4 text-end">
            Название книги 
        </div>
        <div class="col-8 text-start ">
          <input class="col-9" type="text" name="name">
        </div>
      </div>
      <div class="row w-50 my-2 mx-auto">
        <div class="col-4 text-end">
          Автор книги 
        </div>
        <div class="col-8 text-start">  
          <input class="col-7" type="text" name="author">
        </div>
      </div>
      <div class="row w-50 my-2 mx-auto">
        <div class="col-4 text-end">   
            Год издания книги 
        </div>
        <div class="col-8 text-start">  
          <input class="col-4" type="text" name="publishedYear">
        </div>
      </div>
      <div class="row w-50 my-2 mx-auto">
        <div class="col-4 text-end">
            ISBN 
        </div>
        <div class="col-8 text-start">     
            <input class="col-9" type="text" name="isbn">
        </div>
      </div>
      <div class="row w-50 my-2 mx-auto">
        <div class="col-4">
        </div>
        <div class="col-8 mt-3 text-start">    
          <input class="col-9 bg-primary text-white" type="submit" name="submit" value="Добавить">
        </div>
      </div>
    </form>
 