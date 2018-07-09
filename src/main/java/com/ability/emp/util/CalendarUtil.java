package com.ability.emp.util;

import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {
	
//	public static void main(String[] args) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd E");
//        for (Date date : getWeekDay()) {
//            System.out.println(dateFormat.format(date));
//        }
//    }
    public static Date[] getWeekDay() {
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DAY_OF_WEEK, -1);
        }
        Date[] dates = new Date[5];
        for (int i = 0; i < 5; i++) {
            dates[i] = calendar.getTime();
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }

}
