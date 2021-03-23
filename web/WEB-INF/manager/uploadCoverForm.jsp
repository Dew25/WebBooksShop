<%-- 
    Document   : uploadForm
    Created on : Feb 15, 2021, 1:15:41 PM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h3 class="w-100 my-5 text-center">Загрузить файл обложки</h3>
        <form action="uploadCover" method="POST"  enctype="multipart/form-data">
            <div class="w-50 mx-auto">
                <div class=" row my-2 w-100">
                    <div class="col-auto">
                      <label for="description" class="form-label ">Описание</label>
                    </div>
                    <div class="col">
                      <input class="form-control  w-100" type="text" name="description" id="description">
                    </div>
                </div>
                <div class="row w-100">
                  <input class="form-control col" type="file" name="file" id="file">
                </div>
                <div class="row mt-3">
                    <button type="submit" class="btn btn-primary col">Загрузить файл</button>
                </div>
            </div>
            
        </form>
