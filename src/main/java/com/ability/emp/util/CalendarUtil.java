package com.ability.emp.util;


public class CalendarUtil {
	
	/**
     * 判断某一年是否是闰年
     * 是闰年返回true
     * @param year
     * @return
     */
    public static boolean isBissextile(int year) {
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            return true;
        }
        return false;
    }
     
    /**
     * 计算具体某月有多少天
     * @param month
     * @param year
     * @return
     */
    public static int daysOfmonthInyear(int month, int year) {
        int months[] = {31,29,31,30,31,30,31,31,30,31,30,31};
        //判断是否是闰年，闰年2月有29 天 
        if (isBissextile(year)) {
            months[1] = 29;
        } else {
            months[1] = 28;
        }
        return months[month - 1];
    }
     
    /**
     * 计算具体某天距离1900年1月1日有多少天数
     * @param day
     * @param month
     * @param year
     * @return
     */
    public static int daysFromNovecento(int day, int month, int year) {
        //接收天数差值
        int daysSum = 0;
        //将距离1900年的整年天数相加
        for (int i = 1900; i < year; i++) {
            //是闰年则为366天不是为365天
            daysSum += isBissextile(i) ? 366 : 365; 
        }
        //计算当年距离1月的整月天数
        for (int j = 1; j < month; j++){
            daysSum += daysOfmonthInyear(j, year);
        }
        //加上当月天数
        daysSum += day;
        return daysSum;
    }
 
    /**
     * <p>判断具体某天是星期几
     * <p>return 1 2 3 4 5 6 0
     * @param day
     * @param month
     * @param year
     * @return
     */
    public static int whichWeek(int day, int month, int year){
        //1900.1.1是星期一，取余得1234560分别代表星期一到星期天
        return daysFromNovecento(day, month, year) % 7;
    }
 
    /**
     * 打印某年某月的所有日期
     * @param month
     * @param year
     */
    public static int printMonthOfYear(int month, int year){
    	
    	return daysOfmonthInyear(month, year);
        //循环次数为当月天数
//        for (int i = 1; i <= daysOfmonthInyear(month, year); i++) {
//            System.out.print(i + ";");
//        }
    }
     
    /**
     * 打印日历--按日历格式打印某一年的所有日期
     * @param year
     */
    public static void printYear(int year){
        System.out.println("\t\t\t" + year + "年");
        //循环12个月  每次调用打印月份方法
        for (int i = 1; i <= 12; i++) {
            System.out.println("\t\t\t" + i + "月");
            printMonthOfYear(i, year);
        }
    }
//    //main方法 --- 测试
//    public static void main(String []args) {
//    	System.out.println(printMonthOfYear(06, 2018));
//    	
//    	
//    }
	

}
