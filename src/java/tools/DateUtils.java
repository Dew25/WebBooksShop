/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author jvm
 */
public class DateUtils {
    /**
     * Set time date:00.00.00
     * @param date type Date
     * @return Date
     */
    public static Date getStartOfDay(Date date){
        LocalDateTime localDateTime = asLocalDateTime(date);
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return asDate(startOfDay);
    }
    
    public static Date plusDaysToDate(Date date, int days){
        LocalDateTime localDateTime = asLocalDateTime(date);
        LocalDateTime localDateTimePluss = localDateTime.plusDays(days);
        return asDate(localDateTimePluss);
    }
    
    public static Date plusHoursToDate(Date date, int hours){
        LocalDateTime localDateTime = asLocalDateTime(date);
        LocalDateTime localDateTimeMinus = localDateTime.plusHours(hours);
        return asDate(localDateTimeMinus);
    }
    
    public static Date plusMinutesToDate(Date date, int minutes){
        LocalDateTime localDateTime = asLocalDateTime(date);
        LocalDateTime localDateTimePluss = localDateTime.plusMinutes(minutes);
        return asDate(localDateTimePluss);
    }
    
    public static Date minusDaysToDate(Date date, int days){
        LocalDateTime localDateTime = asLocalDateTime(date);
        LocalDateTime localDateTimeMinus = localDateTime.minusDays(days);
        return asDate(localDateTimeMinus);
    }
    public static Date minusMinutesToDate(Date date, int minutes){
        LocalDateTime localDateTime = asLocalDateTime(date);
        LocalDateTime localDateTimeMinus = localDateTime.minusMinutes(minutes);
        return asDate(localDateTimeMinus);
    }
    public static Date minusHoursToDate(Date date, int hours){
        LocalDateTime localDateTime = asLocalDateTime(date);
        LocalDateTime localDateTimeMinus = localDateTime.minusHours(hours);
        return asDate(localDateTimeMinus);
    }
    
    public static Date asDate(LocalDate localDate) {
      return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
      return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
      return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime asLocalDateTime(Date date) {
      return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
