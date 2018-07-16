package com.ability.emp.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WeekUtil {
	
	
	public static String[] getWeek() {
		String[] week = new String[2];
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 当前日期
        Calendar instance = Calendar.getInstance();
        // 调整到本周
        instance.add(Calendar.WEDNESDAY, 0);
        // 调整到本周1
        instance.set(Calendar.DAY_OF_WEEK, 2);
        //循环打印
        for (int i = 1; i <= 5; i++) {
            if(i==1){
            	week[0]=format.format(instance.getTime());
            	//System.out.println("星期" + i + ":" + format.format(instance.getTime()));
            }
            if(i==5){
            	week[1]=format.format(instance.getTime());
            	//System.out.println("星期" + i + ":" + format.format(instance.getTime()));
            }
            instance.add(Calendar.DAY_OF_WEEK, 1);
        }
        return week;
    }

}
