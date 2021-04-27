import {authModule} from './AuthModule.js';
class UserModule{
    registration(){
        document.getElementById('context').innerHTML=
        `<h3 class="w-100 my-5 text-center">Регистрация пользователя</h3>
        <div class="w-100 d-flex justify-content-center m-2">
          <form action="createUser" method="POST" onsubmit="false">
              <div class="row">
                  <div class="col">
                    <input type="text" id="firstname" class="form-control my-2 g-2" placeholder="Имя" aria-label="Имя">
                  </div>
                  <div class="col">
                    <input type="text" id="lastname" class="form-control my-2 g-2" placeholder="Фамилия" aria-label="Фамилия">
                  </div>
                </div>
                <div class="row">
                  <div class="col">
                    <input type="text" id="phone" class="form-control my-2 g-2" placeholder="Телефон" aria-label="Телефон">
                  </div>
                  <div class="col">
                    <input type="text" id="money" class="form-control my-2 g-2" placeholder="Деньги" aria-label="Деньги">
                  </div>
                </div>
                <div class="row">
                  <div class="col">
                    <input type="text" id="login" class="form-control my-2 g-2" placeholder="Логин" aria-label="Логин">
                  </div>
                  <div class="col">
                    <input type="text" id="password" class="form-control my-2 g-2" placeholder="Пароль" aria-label="Пароль">
                  </div>
                </div>
                <div class="row">
                  <div class="col">
                    <input type="button" id="btnRegistration" class="form-control my-2 g-2 text-white bg-primary" value="Зарегистрировать">
                  </div>
                </div>
          
          </form>
      </div>`;

      document.getElementById('info').innerHTML='';
      document.getElementById('btnRegistration').addEventListener('click', userModule.createUser); 
    }

    async createUser(){
        document.getElementById('info').innerHTML='';
        const firstname = document.getElementById("firstname").value;
        const lastname = document.getElementById("lastname").value;
        const phone = document.getElementById("phone").value;
        const money = document.getElementById("money").value;
        const login = document.getElementById("login").value;
        const password = document.getElementById("password").value;
        const user = {
            "firstname": firstname,
            "lastname": lastname,
            "phone": phone,
            "money": money,
            "login": login,
            "password": password
        };

       const response = await fetch('createUserJson',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset:utf8'
            },
            body: JSON.stringify(user)
        })
        if(response.ok){
          const result = await response.json();
          document.getElementById('info').innerHTML=result.info;
          console.log("Request status: "+result.requestStatus);
          authModule.printLoginForm();
        }else{
          console.log("Ошибка получения данных");
        }
        
    }
}
const userModule = new UserModule();
export {userModule};