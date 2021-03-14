<%--
    Document   : takeOnBookForm
    Created on : 08.12.2020, 10:07:51
    Author     : jvm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h3 class="w-100 my-5 text-center">Список книг в корзине</h3>
    <div class="w-100 text-center">Желаете купить:</div>
    <form action="buyBooks" method="POST">
        <div class="row w-100 d-block">
            <table class="table table-striped table-hover w-50 mx-auto">
                <thead>
                        <th class="text-end h-5">Отметить</th>
                        <th class="text-center h-5">Книга</th>
                        <th class="text-center h-5">Удалить из корзины</th>

                </thead>
                <tbody>
            <c:forEach var="book" items="${listBooksInBasket}">
                <tr>
                    <td class="text-end m-2 align-middle">
                        <input class="form-check-input" type="checkbox" value="${book.id}" name="selectedBooks">
                    </td>
                    <td class="m-2">
                        <div class="card mx-auto" style="max-width: 12rem; max-height: 25rem" >
                          <img src="insertFile/${book.cover.path}" class="card-img-top d-block" alt="..." style="min-width: 12rem; min-height: 18rem">
                          <div class="card-body">
                            <div class="card-title">${book.name}</div>
                            <div class="card-text">${book.author}</div>
                            <div class="card-text">${book.publishedYear}</div>
                            <!--  <p class="d-flex justify-content-center">
                              <a href="readBook?bookId=${book.id}" class="w-50 text-nowrap">Читать</a>
                              <a href="addToBasket?bookId=${book.id}" class=" w-50 text-nowrap">В корзину</a>
                            </p>-->
                          </div>
                        </div>
                    </td>
                    <td class="text-start m-2 align-middle">
                        <a href="removeBookFromBasket?bookId=${book.id}" class="w-50 text-nowrap">Удалить из корзины</a>
                    </td>
                </tr>
            </c:forEach>
                <tr>
                    <td colspan="2">
                        <button type="submit" class="btn btn-primary mb-5 w-100">Купить отмеченные книги</button>
                    </td>
                </tr>
                </tbody>
        </table>

        </div>
    </form>





