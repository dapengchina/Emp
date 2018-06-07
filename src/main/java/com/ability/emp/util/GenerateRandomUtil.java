package com.ability.emp.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GenerateRandomUtil {
	
	
	
	
	
	
//	 public static void main(String args[]){
//		 @SuppressWarnings("rawtypes")
//		 Set set = getRandomNumber();
//		 Object[] list = set.toArray();
//		 System.out.println(list.length);
//		 for(int k=0;k<list.length;k++){
//			 System.out.println(list[k]);
//		 }
//	  }
	
	
	
	 //获得四位随机数不能重复
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public static Set getRandomNumber(){
	     //创建set保证数字不会重复
	     Set set=new HashSet();
	     //随机数
	     Random r=new Random();
	  
	     for(int i=0;i<999999;i++){
	         //从0到9999
	         int number=r.nextInt(999999);
	         //如果随机的是六位数添加
	         if(number>=100000){
	            set.add(number);
	         }
	         //添加30万个6位不重复的密码
	         if(set.size()==300000){
	            break;
	         }
	     }
	     return set;
	 }
}