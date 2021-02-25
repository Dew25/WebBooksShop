<%-- 
    Document   : page1
    Created on : Nov 25, 2020, 10:14:04 AM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<p class="w-100 text-center my-5">Добро пожаловать в нашу библиотеку!</p>
<div id="carouselExampleSlidesOnly" class="carousel slide" data-bs-ride="carousel">
  <div class="carousel-inner">
    <c:forEach var="covers" items="${coversMap}">
        <div class="carousel-item active">
          <c:forEach var="imgPath" items="${covers.value}">
            <img src="insertCover/imgPath" class="w-5 " alt="..." style="width: 8rem; height: 10rem">
          </c:forEach>
    </div>
    </c:forEach>
    <div class="carousel-item active">
      <img src="insertCover/D:\UploadJPTVR19WebLibrary\voina-i-mir-cover.jpg" class="w-5 " alt="..." style="width: 8rem; height: 10rem">
      <img src="insertCover/D:\UploadJPTVR19WebLibrary\voina-i-mir-cover.jpg" class=" w-5" alt="..." style="width: 8rem; height: 10rem">
      <img src="insertCover/D:\UploadJPTVR19WebLibrary\voina-i-mir-cover.jpg" class=" w-5" alt="..." style="width: 8rem; height: 10rem">
    </div>
    
  </div>
</div>
    
    
  
