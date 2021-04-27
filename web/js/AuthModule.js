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
        document.getElementById('registration-link').addEventListener('click',userModule.registration); 
        
    }
    auth(){
        document.getElementById('context').innerHTML='';
        document.getElementById('info').innerHTML='отработал метод authModule.auth()';
    }
   
}
const authModule = new AuthModule();
export {authModule};


