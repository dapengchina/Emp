package com.ability.emp.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarCountUtil {
	
	
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    
    public static int getDays(String first,String second) throws Exception{
//        first = "2018-06-30";
//        second = "2018-07-01";
        Date firstdate = format.parse(first);
        Date seconddate = format.parse(second);
        int cnt = longOfTwoDate(firstdate, seconddate);
        
        return cnt;
    }
    
    public static int longOfTwoDate(Date first,Date second){
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(first);
                int cnt = 0;
                while(calendar.getTime().compareTo(second)!=0){
                    calendar.add(Calendar.DATE, 1);
                    cnt++;
                }
                return cnt;
    }

}
