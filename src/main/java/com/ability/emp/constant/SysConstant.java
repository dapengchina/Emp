package com.ability.emp.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统常量
 * @author Devin
 * @since 2018-4-6
 *
 */
public class SysConstant {
	
	
	   //未删除
	   public static final String NO_DEL = "0";
	   //已删除
	   public static final String DEL = "1";
	   //已启用
	   public static final String STARTUSE = "0";
	   //已禁用
	   public static final String FORBIDDEN = "1";
	   //未指派
	   public static final String NOT_ASSIGNED = "0";
	   //已指派
	   public static final String ASSIGNED = "1";
	   //系统默认初始密码
	   public static final String INITPWD = "123456";
	   //选中
	   public static final String CHECKED = "0";
	   //未选中
	   public static final String NO_CHECKED = "1";
	   //错误
	   public static final String ERROR = "0";
	   //正确
	   public static final String RIGHT = "1";
	   //考试通过
	   public static final String PASS = "0";
	   //考试未通过
	   public static final String NO_PASS = "1";
	   //服务器地址
	   public static final String SERVICE_HOST = "https://www.learnzp.com";
	   //在线课程
	   public static final String ON_LINE = "1";
	   //离线课程
	   public static final String OFF_LINE = "0";
	   
	   /**
	    * choicetextdropletdata
	    */
	   //问题为文本
	   public static final String QUESTION_TEXT = "0";
	   //问题为音频
	   public static final String QUESTION_AUDIO = "1";
	   //dictation占位符
	   public static final String PLACE_HOLDER = "$$$";
	   
	   /**
	    * 任务类型
	    */
	   public static final String TASK_TYPE0 = "0";//背单词
	   public static final String TASK_TYPE1 = "1";//其它
	   public static final Map<String,Object> map = new HashMap<String,Object>();
	   public static Map<String,Object> getTaskTypeMap(){
		   map.put("0", "背单词");
		   map.put("1", "其它");
		   return map;
	   }
	   
	   /**
	    * 用户任务完成百分比初始值
	    */
	   public static final String COMPLETE_PERCENT_INIT = "0";
	   
	   

}
