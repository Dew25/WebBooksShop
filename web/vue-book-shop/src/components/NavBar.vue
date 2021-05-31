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
          v-for="link in links"
          :link="item"
          :key="link.id"
        >
          <a 
            class="nav-link" 
            aria-current="page" 
            v-if="activeLink(link)"
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
    links: Array,
    role: String
  },
  data(){
    return {
     
    }

  },
  computed: {
    activeLink(link){
      if(this.role === 'null'){
        switch(link.id) {
          case '6': // Вход
          case '2': // Список книг
            return 'true';
            break;

          default:
            return 'false';
        }
      }
      if(this.role === 'READER'){
        switch(link.id) {
          case '1': // Список книг 
          case '2': // Купленные книги
          case '7': // Выход
          case '8': // В корзину 
            return 'true';
            break;
          default:
            return 'false';
        }
      }
      if(this.role === 'MANAGER'){
        switch(link.id) {
          case '0': // Добавить книгу 
          case '1': // Список книг 
          case '2': // Купленные книги 
          case '3': // Скидка 
          case '7': // Выход
          case '8': // В корзину 
            return 'true';
            break;

          default:
            return 'false';
        }
      }
      if(this.role === 'ADMIN'){
        switch(link.id) {
          case '0': // Добавить книгу 
          case '1': // Список книг 
          case '2': // Купленные книги 
          case '3': // Скидка 
          case '4': // Список читателей 
          case '5': // Панель администратора 
          case '7': // Выход
          case '8': // В корзину 
            return 'true';
            break;

          default:
            return 'false';
        }
      }
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
