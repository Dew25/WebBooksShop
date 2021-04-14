/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import entity.Book;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;

/**
 *
 * @author jvm
 */
public class SheduleDiscount {
    public Book setDiscount(Book book, int discount, Date date, int duration, String durationType){
        book.setDiscount(discount);
        book.setDiscountDuration(duration);
        Date startDate = DateUtils.getStartOfDay(date); // приведение даты к началу дня
        Date endDate;
        Calendar c = new GregorianCalendar();
        switch (durationType) {
            case "MINUTE":
                book.setDiscountDate(c.getTime());
                endDate = DateUtils.plusMinutesToDate(c.getTime(), duration);
                break;
            case "HOUR":
                book.setDiscountDate(c.getTime());
                endDate = DateUtils.plusHoursToDate(c.getTime(), duration);
                break;
            case "DAY":
                book.setDiscountDate(startDate);
                endDate = DateUtils.plusMinutesToDate(startDate, duration);
                break;
            default:
                book.setDiscountDate(startDate);
                endDate = DateUtils.plusMinutesToDate(startDate, duration);
        }
        Timer timer = new Timer();
        DiscountTimerTask discountTimerTask = new DiscountTimerTask(book);
        timer.schedule(discountTimerTask, endDate);
        return book;
    }
    
}
