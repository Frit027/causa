package com.site.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static Date parseDate(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isDateNotExpired(Date deadlineDate) {
        Calendar now = Calendar.getInstance();
        Calendar deadline = Calendar.getInstance();
        now.setTime(new Date());
        deadline.setTime(deadlineDate);

        return  deadline.getTime().after(now.getTime()) ||
                now.get(Calendar.DAY_OF_YEAR) == deadline.get(Calendar.DAY_OF_YEAR) &&
                now.get(Calendar.YEAR) == deadline.get(Calendar.YEAR);
    }

    public static int getDaysLeftInMonth() {
        Calendar cal = Calendar.getInstance();
        int today = cal.get(Calendar.DAY_OF_MONTH);
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        return lastDay - today;
    }

    public static boolean isSameMonth(Date date) {
        LocalDate localDate = new Date(date.getTime()).toInstant()
                                                      .atZone(ZoneId.systemDefault())
                                                      .toLocalDate();
        int dateMonth = localDate.getMonthValue();
        int dateYear = localDate.getYear();

        LocalDate today = new Date().toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate();
        int todayMonth = today.getMonthValue();
        int todayYear = today.getYear();

        return dateMonth == todayMonth && dateYear == todayYear;
    }
}
