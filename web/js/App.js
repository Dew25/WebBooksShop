import {authModule} from './AuthModule.js';
import {bookModule} from './BookModule.js';
import {userModule } from './UserModule.js';

document.getElementById("addBook").onclick = function (){
    toogleMenuActive("addBook");
    document.getElementById('info').innerHTML='&nbsp;';
    bookModule.printAddBookForm();
   
};
document.getElementById("listBooks").onclick = function (){
    toogleMenuActive("listBooks");
    document.getElementById('info').innerHTML='&nbsp;';
    bookModule.printListBooks();
};
document.getElementById("purchasedBooks").onclick = function (){
    toogleMenuActive("purchasedBooks");
};
document.getElementById("discountForm").onclick = function (){
    toogleMenuActive("discountForm");
};
document.getElementById("listReaders").onclick = function (){
    toogleMenuActive("listReaders");
    document.getElementById('info').innerHTML='&nbsp;';
    userModule.printListUsers();
};
document.getElementById("adminForm").onclick = function (){
    toogleMenuActive("adminForm");
};
document.getElementById("loginForm").onclick = function (){
    toogleMenuActive("loginForm");
    document.getElementById('info').innerHTML='&nbsp;';
    authModule.printLoginForm();
};
document.getElementById("logout").onclick = function (){
    toogleMenuActive("logout");
    document.getElementById('info').innerHTML='';
    authModule.logout();
};

authModule.toogleMenu();

function toogleMenuActive(elementId){
   const activeElement = document.getElementById(elementId);
   const passiveElements = document.getElementsByClassName("nav-link");
    for (let i = 0; i < passiveElements.length; i++) {
        if(activeElement === passiveElements[i]){
            passiveElements[i].classList.add("active-menu");
        }else{
            if(passiveElements[i].classList.contains("active-menu")){
                passiveElements[i].classList.remove("active-menu");
            }
        }
    }
}
