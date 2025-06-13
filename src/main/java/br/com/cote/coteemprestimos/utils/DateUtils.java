package br.com.cote.coteemprestimos.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class DateUtils {


    public static LocalDate getLastBusinessDay(LocalDate date) {
        while (isHolidayOrWeekend(date)) {
            date = date.minusDays(1);
        }
        return date;
    }

    private static boolean isHolidayOrWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY ||
                date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

}
