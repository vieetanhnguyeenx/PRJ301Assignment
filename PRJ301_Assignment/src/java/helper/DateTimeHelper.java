/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class DateTimeHelper {

    public static LocalDate toLocalDate(String value) {
        LocalDate lc = LocalDate.parse(value);
        return lc;
    }
    
    public static String getDayNameOfWeek(LocalDate lc) {
        String day = lc.getDayOfWeek().toString();
        switch(day) {
            case "MONDAY": return "MON";
            case "TUESDAY": return "TUE";
            case "WEDNESDAY": return "WED";
            case "THURSDAY": return "THU";
            case "FRIDAY": return "FRI";
            case "SATURDAY": return "SAT";
            case "SUNDAY": return "SUN";
        }
        return "ERROR";
    }
    
    
    public static ArrayList<LocalDate> getNearestSunAndMon(LocalDate lc) {
        ArrayList<LocalDate> list = new ArrayList<>();
        String day = lc.getDayOfWeek().toString();
        switch(day) {
            case "MONDAY":
                list.add(lc);
                list.add(lc.plusDays(6));
                return list;
            case "TUESDAY":
                list.add(lc.plusDays(-1));
                list.add(lc.plusDays(5));
                return list;
            case "WEDNESDAY":
                list.add(lc.plusDays(-2));
                list.add(lc.plusDays(4));
                return list;
            case "THURSDAY":
                list.add(lc.plusDays(-3));
                list.add(lc.plusDays(3));
                return list;
            case "FRIDAY":
                list.add(lc.plusDays(-4));
                list.add(lc.plusDays(2));
                return list;
            case "SATURDAY":
                list.add(lc.plusDays(-5));
                list.add(lc.plusDays(1));
                return list;
            case "SUNDAY":
                list.add(lc.plusDays(-6));
                list.add(lc);
                return list;
        }
        return null;
    }
    
    public static ArrayList<LocalDate> getDateList(LocalDate from, LocalDate to) {
        ArrayList<LocalDate> dates = new ArrayList<>();
        int day = 0;
        while (true) {            
            LocalDate lc = from.plusDays(day);
            dates.add(lc);
            day++;
            if (lc.compareTo(to) >= 0){
                break;
            }
        }
        return dates;
    }
    
    public static Date toDateSql(LocalDate lc) {
        Date d = Date.valueOf(lc);
        return d;
    }
    
    public static LocalDate toLocalDate(Date d) {
        LocalDate lc = LocalDate.parse(d.toString());
        return lc;
    }
    
    public static int isEqual(LocalDate lc1, LocalDate lc2) {
        if(lc1.equals(lc2)) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public static void main(String[] args) {
        String rawFrom = "2022-10-01";
        String rawTo = "2022-10-23";
        LocalDate from = null;
        LocalDate to = null;
        
        if (rawFrom.isEmpty()|| rawFrom.length() == 0) {
            LocalDate today = LocalDate.now();
            ArrayList<LocalDate> sunAndMon = DateTimeHelper.getNearestSunAndMon(today);
            from = sunAndMon.get(0);
            to = sunAndMon.get(1);
        } else {
            from = LocalDate.parse(rawFrom);
            to = LocalDate.parse(rawTo);
        }
        ArrayList<LocalDate> lcall = DateTimeHelper.getDateList(from, to);
        for (LocalDate l : lcall) {
            System.out.println(l.toString() + l.getDayOfWeek());
        }
    }
    
    
}
