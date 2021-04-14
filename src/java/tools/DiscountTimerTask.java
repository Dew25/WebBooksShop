/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import entity.Book;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import session.BookFacade;

/**
 *
 * @author jvm
 */
public class DiscountTimerTask extends TimerTask{
    private Book discountBook;
    private BookFacade bookFacade;

    public DiscountTimerTask(Book discountBook) {
        Context ctx;
        try {
            ctx = new InitialContext();
            this.bookFacade = (BookFacade)ctx.lookup("java:global/WebBooksShop/BookFacade");
        } catch (NamingException ex) {
            Logger.getLogger(DiscountTimerTask.class.getName()).log(Level.SEVERE, "Не найден BookFacade", ex);
        }
        this.discountBook = discountBook;
    }
    
    @Override
    public void run() {
        discountBook.setDiscount(0);
        discountBook.setDiscountDate(null);
        discountBook.setDiscountDuration(0);
        bookFacade.edit(discountBook);
    }
    
}
