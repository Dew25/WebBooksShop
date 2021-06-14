<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Магазин книг</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav w-100 d-flex justify-content-end">
        <li 
          class="nav-item"
          v-for="link in activeListLinks"
          :link="link"
          :key="link.id"
        >
          <a 
            class="nav-link" 
            aria-current="page" 
            
            :href="link.url"
            >{{ link.text }}</a>
        </li>
        
      </ul>
    </div>
  </div>
</nav>
</template>

<script>

export default {
  name: 'NavBar',
  
  props: { 
    role: String,
  },
  computed: {
    activeListLinks(){
      let listLinksForRole=[];
      switch (this.role) {
        case 'ADMIN':
          listLinksForRole = [
            {id: 0, url: 'addBook', text: 'Добавить книгу'},
            {id: 1, url: 'listBooks', text: 'Список книг'},
            {id: 2, url: 'purchasedBooks', text: 'Купленные книги'},
            {id: 3, url: 'discountForm', text: 'Скидка'},
            {id: 4, url: 'listReaders', text: 'Список читателей'},
            {id: 5, url: 'adminForm', text: 'Панель администратора'},
            {id: 7, url: 'logout', text: 'Выйти'},
            {id: 8, url: 'addToCart', text: 'Корзина'},
          ]
          break
        case 'MANAGER':
          listLinksForRole = [
            {id: 0, url: 'addBook', text: 'Добавить книгу'},
            {id: 1, url: 'listBooks', text: 'Список книг'},
            {id: 2, url: 'purchasedBooks', text: 'Купленные книги'},
            {id: 3, url: 'discountForm', text: 'Скидка'},
            {id: 7, url: 'logout', text: 'Выйти'},
            {id: 8, url: 'addToCart', text: 'Корзина'},
          ]
          break;
        case 'READER':
          listLinksForRole = [
            {id: 1, url: 'listBooks', text: 'Список книг'},
            {id: 2, url: 'purchasedBooks', text: 'Купленные книги'},
            {id: 7, url: 'logout', text: 'Выйти'},
            {id: 8, url: 'addToCart', text: 'Корзина'},
          ]
          break;
        default:
          listLinksForRole = [
            {id: 6, url: 'loginForm', text: 'Вoйти'},
            {id: 1, url: 'listBooks', text: 'Список книг'},
          ]
          break;
      }
      
      return listLinksForRole;
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
