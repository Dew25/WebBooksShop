import {authModule} from './AuthModule.js';
import {bookModule} from './BookModule.js';

document.getElementById("addBook").onclick = function (){
    toogleMenuActive("addBook");
    bookModule.printAddBookForm();
};
document.getElementById("listBooks").onclick = function (){
    toogleMenuActive("listBooks");
};
document.getElementById("purchasedBooks").onclick = function (){
    toogleMenuActive("purchasedBooks");
};
document.getElementById("discountForm").onclick = function (){
    toogleMenuActive("discountForm");
};
document.getElementById("listReaders").onclick = function (){
    toogleMenuActive("listReaders");
};
document.getElementById("adminForm").onclick = function (){
    toogleMenuActive("adminForm");
};
document.getElementById("loginForm").onclick = function (){
    toogleMenuActive("loginForm");
    authModule.printLoginForm();
};
document.getElementById("logout").onclick = function (){
    toogleMenuActive("logout");
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
