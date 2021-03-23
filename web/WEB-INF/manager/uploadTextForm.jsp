<%-- 
    Document   : uploadForm
    Created on : Feb 15, 2021, 1:15:41 PM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h3 class="w-100 my-5 text-center">Загрузить файл с текстом</h3>
        <form action="uploadText" method="POST"  enctype="multipart/form-data">
            <div class="w-50 mx-auto">
                <div class=" row mb-3">
                  <label for="description" class="form-label">Описание</label>
                  <input class="form-control" type="text" name="description" id="description">
                </div>
                <div class="row mb-3">
                  <input class="form-control" type="file" name="file" id="file">
                </div>
                <div class="row mt-3">
                    <button type="submit" class="btn btn-primary">Загрузить файл</button>
                </div>
            </div>
        </form>
