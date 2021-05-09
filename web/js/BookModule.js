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
        <input class="w-50 bg-primary text-white" type="submit" id="btnAddBook" name="submit" value="Добавить">
      </div>
    </div>
    </form>
   `;
    document.getElementById("bookForm").onsubmit = function(e){
      e.preventDefault();
      bookModule.createBook();
    }
  }
  async createBook(){
    document.getElementById('info').innerHTML="Загрузка книги";
      let response = await fetch('createBookJson', {
        method: 'POST',
        body: new FormData(document.getElementById('bookForm'))
      });
      console.log('response.ok: '+ response.ok);
      if(response.ok){
        const result = await response.json()
        document.getElementById('info').innerHTML=result.info;
        let books = JSON.parse(sessionStorage.getItem('books'));
        if(books === null){
          books = [];
        }
        books.push(result.book);
        sessionStorage.setItem('books', JSON.stringify(books));
        console.log("books: ");
        for(let book of books){
          console.log(book +', ');
        };
      }else{
        document.getElementById('info').innerHTML='Ошибка сервера';
      }
    
  }
 async printListBooks(){
    const context = document.getElementById('context');
    context.innerHTML='';
    let listBooks = await bookModule.loadListBooks();
    const today = new Date();
    const divBook = document.createElement('div');
    divBook.classList.add('row');
    divBook.classList.add('w-100');
    divBook.classList.add('d-flex');
    divBook.classList.add('justify-content-center');
    for(let book of listBooks){
      divBook.insertAdjacentHTML('beforeend',`
      <div class="card m-2" style="max-width: 12rem; max-height: 25rem; border:0">
      <p class="card-text text-danger w-100 d-flex justify-content-center">&nbsp;</p>
      <img src="insertFile/${book.cover.path}" class="card-img-top" style="max-width: 12rem; max-height: 15rem" alt="...">
      <div class="card-body">
        <h5 class="card-title m-0">${book.name}</h5>
        <p class="card-text m-0">${book.author}</p>
        <p class="card-text m-0">${book.publishedYear}</p>
        <p class="card-text m-0">${book.price/100} EUR</p>
        <p class="d-inline">
          <a href="#readBook?bookId=${book.id}" class="link text-nowrap">Читать</a>
          <a href="#addToBasket?bookId=${book.id}" class="link text-nowrap">В корзину</a>
        </p>
      </div>
    </div>
      `);
    }
    context.insertAdjacentElement("beforeend",divBook);
    
  }
  async loadListBooks(){
    let response = await fetch('listBooksJson',{
      method: 'GET',
      headers:{
        'Content-Type': 'aplication/json;charser=utf-8'
      }
    })
    if(response.ok){
      let result = await response.json();
      console.log('listBooks: '+result);
      return result;
    }else{
      document.getElementById('info').innerHTML="Ошибка сервера";
      return null;
    }
  }
}
const bookModule = new BookModule();
export {bookModule};