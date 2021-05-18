class BookModule{
  printAddBookForm(){
    document.getElementById('context').innerHTML=
     `<h3 class="w-100 text-center my-5 ">Добавить новую книгу</h3>
      <form id="bookForm" method="POST" enctype="multipart/form-data">
        <div class="row w-50 my-2 mx-auto">
        <div class="col-4 text-end">
            Название книги 
        </div>
        <div class="col-8 text-start ">
          <input class="w-100" type="text" name="name" id="name">
        </div>
      </div>
      <div class="row w-50 my-2 mx-auto">
        <div class="col-4 text-end">
          Автор книги 
        </div>
        <div class="col-8 text-start">  
          <input class="col-8" type="text" name="author" id="author">
        </div>
      </div>
      <div class="row w-50 my-2 mx-auto">
        <div class="col-4 text-end">   
            Год издания книги 
        </div>
        <div class="col-8 text-start">  
          <input class="col-4" type="text" name="publishedYear" id="publishedYear">
        </div>
      </div>
      <div class="row w-50 my-2 mx-auto">
        <div class="col-4 text-end">   
            ISBN: 
        </div>
        <div class="col-8 text-start">  
          <input class="col-8" type="text" name="isbn" id="isbn">
        </div>
      </div>
      <div class="row w-50 my-2 mx-auto">
        <div class="col-4 text-end">   
            Цена: 
        </div>
        <div class="col-8 text-start">  
          <input class="col-4" type="text" name="price" id="price">
        </div>
      </div>
      <div class="row w-50 my-2 mx-auto">
        <div class="col-4 text-end">
            Загрузите обложку 
        </div>
        <div class="col-8 text-start">     
            <input class="form-control col" type="file" name="file" id="file-cover">
        </div>
      </div>
      <div class="row w-50 my-2 mx-auto">
        <div class="col-4 text-end">
            Текст книги 
        </div>
        <div class="col-8 text-start">     
          <input class="form-control" type="file" name="file" id="file-text">
        </div>
      </div>
      <div class="row w-50 my-2 mx-auto">
        <div class="col-4 text-end">
             
        </div>
        <div class="col-8 text-start mt-3">     
          <input class="w-50 bg-primary text-white" type="submit" name="submit" value="Добавить">
        </div>
      </div>
    </form>`;
    document.getElementById('bookForm').onsubmit = function(e){
      e.preventDefault();
      bookModule.createBook();
    }
  }
  async createBook(){
    let response = await fetch('createBookJson',{
      method: 'POST',
      body: new FormData(document.getElementById('bookForm'))
    })
    if((response).ok){
      const result = await response.json();
      document.getElementById('info').innerHTML = result.info;
      bookModule.printListBooks();
    }else{
      document.getElementById('info').innerHTML='Ошибка сервера';
    }
  }
  async printListBooks(){
    const listBooks = await bookModule.getListBooks();
    let context = document.getElementById('context');
    context.innerHTML = '<h3 class="w-100 my-5 text-center">Список книг</h3>';
    let divForCarts = document.createElement('div');
    divForCarts.classList.add('w-100');
    divForCarts.classList.add('d-flex')
    divForCarts.classList.add('justify-content-center');
    for(let book of listBooks){
      let cart = document.createElement('div');
      cart.classList.add('card');
      cart.classList.add('m-2');
      cart.style.cssText=`max-width: 12rem; max-height: 25rem; border:0`;
      cart.innerHTML= '<p class="card-text text-danger w-100 d-flex justify-content-center">&nbsp;</p>';
      let img = document.createElement('img');
      img.classList.add('card-img-top');
      img.style.cssText=`max-width: 12rem; max-height: 15rem`;
      img.setAttribute('src',`insertFile/${book.cover.path}`);
      cart.insertAdjacentElement('beforeEnd',img);
      cart.insertAdjacentHTML('beforeend',
                  ` <div class="card-body">
                      <h5 class="card-title m-0">${book.name}</h5>
                      <p class="card-text m-0">${book.author}</p>
                      <p class="card-text m-0">${book.publishedYear}</p>
                      <p class="card-text m-0">${book.price/100} EUR</p>
                      <p class="d-inline">
                        <a href="readBook?bookId=${book.id}" class="link text-nowrap">Читать</a>
                        <a href="addToBasket?bookId=${book.id}" class="link text-nowrap">В корзину</a>
                      </p>
                    </div>`
                    );
      divForCarts.insertAdjacentElement('beforeend',cart);
    }

    context.insertAdjacentElement('beforeend',divForCarts);

  }
  async getListBooks(){
    let response = await fetch('listBooksJson',{
      method: 'GET',
      headers: {
        'Content-Type': 'application/json;charset=utf8'
      }
    })
    if(response.ok){
      let result = await response.json();
      return result;
    }else{
      document.getElementById('info').innerHTML='Ошибка сервера';
      return null;
    }
  }
}
const bookModule = new BookModule();
export {bookModule};