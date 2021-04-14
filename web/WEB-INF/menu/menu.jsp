<%-- 
    Document   : menu
    Created on : Jan 25, 2021, 2:35:10 PM
    Author     : Melnikov
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="index">Библиотека</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
      <div class="navbar-nav w-100 d-flex justify-content-end">
        <c:choose>
            <c:when test="${role eq 'ADMIN'}">
                <a class="nav-link <c:if test="${activeAddBook}">active</c:if>" aria-current="page" href="addBook">Добавить книгу</a>
                <a class="nav-link <c:if test="${activeListBooks}">active</c:if>" href="listBooks">Список книг</a>
                <a class="nav-link <c:if test="${activePurchasedBooks}">active</c:if>" href="purchasedBooks">Купленные книги</a>
                <a class="nav-link <c:if test="${activeDiscountForms}">active</c:if>" href="discountForm">Скидка</a>
                <a class="nav-link <c:if test="${activeListReaders}">active</c:if>" id="listReaders" href="listReaders">Список читателей</a>
                <a class="nav-link <c:if test="${activeAdminPanel}">active</c:if>" id="adminForm" href="adminForm">Панель администратора</a>
                <a class="nav-link <c:if test="${activeOut}">active</c:if>" id="logout" href="logout">Выйти</a>
            </c:when>
            <c:when test="${role eq 'MANAGER'}">
                <a class="nav-link <c:if test="${activeListBooks}">active</c:if>" href="listBooks">Список книг</a>
                <a class="nav-link <c:if test="${activeDiscountForms}">active</c:if>" href="discountForm">Скидка</a>
                <a class="nav-link <c:if test="${activePurchasedBooks}">active</c:if>" href="purchasedBooks">Купленные книги</a>
                <a class="nav-link <c:if test="${activeAddBook}">active</c:if>" aria-current="page" href="addBook">Добавить книгу</a>
                <a class="nav-link <c:if test="${activeEditProfile}">active</c:if>" href="editProfile">Профиль</a>
                <a class="nav-link <c:if test="${activeOut}">active</c:if>" href="logout">Выйти</a>
            </c:when>
            <c:when test="${role eq 'READER'}">
                <a class="nav-link <c:if test="${activeListBooks}">active</c:if>" href="listBooks">Список книг</a>
                <a class="nav-link <c:if test="${activePurchasedBooks}">active</c:if>" href="purchasedBooks">Купленные книги</a>
                <a class="nav-link <c:if test="${activeEditProfile}">active</c:if>" href="editProfile">Профиль</a>
                <a class="nav-link <c:if test="${activeOut}">active</c:if>" href="logout">Выйти</a>
            </c:when>
            <c:otherwise>
                <a class="nav-link <c:if test="${activeListBook}">active</c:if>" href="listBooks">Список книг</a>
                <a class="nav-link <c:if test="${activeEnter}">active</c:if>" id="loginForm" href="loginForm">Войти</a>
                <a class="nav-link <c:if test="${activeRegistration}">active</c:if>" href="registrationForm">Регистрация</a>
            </c:otherwise>
        </c:choose>
        <c:if test="${basketListCount > 0}">
            <a href="showBasket">
              <div class="text-white m-2">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
                  <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                </svg>
                ${basketListCount}
              </div>
            </a>
        </c:if>
      </div>
    </div>
  </div>
</nav>