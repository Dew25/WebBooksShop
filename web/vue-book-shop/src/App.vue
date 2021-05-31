<template>
  <div id="app">
    <NavBar :links="listLinks" :role="role" />
    <InfoBar :info="info" />
  </div>
</template>

<script>
import NavBar from './components/NavBar.vue'
import InfoBar from './components/InfoBar.vue'

export default {
  name: 'App',
  components: {
    NavBar,
    InfoBar
  },
  data(){
    return{
      listLinks:[
        {id: 0, url: 'addBook', text: 'Добавить книгу'},
        {id: 1, url: 'listBooks', text: 'Список книг'},
        {id: 2, url: 'purchasedBooks', text: 'Купленные книги'},
        {id: 3, url: 'discountForm', text: 'Скидка'},
        {id: 4, url: 'listReaders', text: 'Список читателей'},
        {id: 5, url: 'adminForm', text: 'Панель администратора'},
        {id: 6, url: 'loginForm', text: 'Вoйти'},
        {id: 7, url: 'logout', text: 'Выйти'},
        {id: 8, url: 'addToCart', text: 'Корзина'},
      ],
      info: 'Добро пожаловать в библиотеку',
      role: 'ADMIN',
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 10px;
}
</style>
