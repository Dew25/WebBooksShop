import { bookModule } from './BookModule.js';
import {userModule} from './UserModule.js';
class AuthModule{
    printLoginForm(){
       document.getElementById('context').innerHTML=
        `<div class="mx-auto mt-5  p-3" style="width: 30rem">
            <h3 class="w-100 m-4 text-center">Введите логин и пароль</h3>
              <div class="row m-2 ">
                <label for="login" class="col-sm-2 col-form-label">Логин</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="login" name="login" value="">
                </div>
              </div>
              <div class="row m-2 ">
                <label for="password" class="col-sm-2 col-form-label">Пароль</label>
                <div class="col-sm-10">
                  <input type="password" class="form-control" id="password" name="password" value="">
                </div>
              </div>
              <div class=" mt-4 m-2 w-100 row">
                <input type="button" id="btnEnter" value="Войти" class="mx-auto col-4 btn-primary" style="width: 16rem">
              </div>
              <div class=" m-0 w-100 row">
                <a class="mx-auto text-center" id="registration-link" href="#registration">Регистрация</a>
              </div>
        </div>`;
        document.getElementById('btnEnter').addEventListener('click',authModule.auth); 
        document.getElementById('password').addEventListener("keyup", function(e){
          if(e.key === 'Enter')  authModule.auth();
        }); 
        document.getElementById('registration-link').addEventListener('click',userModule.registration); 
        
    }
    async auth(){
       const login = document.getElementById('login').value;
       const password = document.getElementById('password').value;
       const credential= {
         "login": login,
         "password": password
       };
       const response = await fetch('loginJson', {
         method: 'POST',
         headers: {
          'Content-Type': 'application/json;charset:utf8'
         },
         body: JSON.stringify(credential)
       });
       if(response.ok){
        const result = await response.json();
        document.getElementById('info').innerHTML=result.info;
        console.log("Request status: "+result.requestStatus);
        document.getElementById('context').innerHTML='';
        bookModule.printListBooks();
        if(result.requestStatus){
          sessionStorage.setItem('token',JSON.stringify(result.token));
          sessionStorage.setItem('role',JSON.stringify(result.role));
        }else{
          if(sessionStorage.getItem(token) !== null){
            sessionStorage.removeItem('token');
            sessionStorage.removeItem('role');
          }
        }
      }else{
        console.log("Ошибка получения данных");
      }
      authModule.toogleMenu();
      // console.log('Auth: token'+sessionStorage.getItem('token'));
      // console.log('Auth: role'+sessionStorage.getItem('role'));
    }
    async logout(){
      const response = await fetch('logoutJson',{
        method: 'GET',
        headers:{
          'Content-Type': 'application/json;charset:utf8'
        }
      });
      if(response.ok){
        const result = await response.json();
          sessionStorage.removeItem('token');
          sessionStorage.removeItem('role');
          document.getElementById('info').innerHTML=result.info;
          bookModule.printListBooks();
      }
      authModule.toogleMenu();

    }
    toogleMenu(){
      let role = null;
      if(sessionStorage.getItem('role') !== null){
        role = JSON.parse(sessionStorage.getItem('role'));
      }
      console.log('Auth: token - '+sessionStorage.getItem('token'));
      console.log('Auth: role - '+sessionStorage.getItem('role'));
      
      if(role===null){
        document.getElementById("listBooks").style.display = 'block';
        document.getElementById("loginForm").style.display = 'block';
        document.getElementById("logout").style.display = 'none';
        document.getElementById("addBook").style.display = 'none';
        document.getElementById("purchasedBooks").style.display = 'none';
        document.getElementById("discountForm").style.display = 'none';
        document.getElementById("listReaders").style.display = 'none';
        document.getElementById("adminForm").style.display = 'none';
        document.getElementById("basket").style.display = 'none';
      }else if(role==="READER"){
        document.getElementById("listBooks").style.display = 'block';
        document.getElementById("loginForm").style.display = 'none';
        document.getElementById("logout").style.display = 'block';
        document.getElementById("addBook").style.display = 'none';
        document.getElementById("purchasedBooks").style.display = 'block';
        document.getElementById("discountForm").style.display = 'none';
        document.getElementById("listReaders").style.display = 'none';
        document.getElementById("adminForm").style.display = 'none';
        document.getElementById("basket").style.display = 'block';
      }else if(role==="MANAGER"){
        document.getElementById("listBooks").style.display = 'block';
        document.getElementById("loginForm").style.display = 'none';
        document.getElementById("logout").style.display = 'block';
        document.getElementById("addBook").style.display = 'block';
        document.getElementById("purchasedBooks").style.display = 'block';
        document.getElementById("discountForm").style.display = 'block';
        document.getElementById("listReaders").style.display = 'none';
        document.getElementById("adminForm").style.display = 'none';
        document.getElementById("basket").style.display = 'block';
      }else if(role==="ADMIN"){
        document.getElementById("listBooks").style.display = 'block';
        document.getElementById("loginForm").style.display = 'none';
        document.getElementById("logout").style.display = 'block';
        document.getElementById("addBook").style.display = 'block';
        document.getElementById("purchasedBooks").style.display = 'block';
        document.getElementById("discountForm").style.display = 'block';
        document.getElementById("listReaders").style.display = 'block';
        document.getElementById("adminForm").style.display = 'block';
        document.getElementById("basket").style.display = 'block';
      }
    
    }
   
}
const authModule = new AuthModule();
export {authModule};


