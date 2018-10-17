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
	   //离线课程默认2个月期限
	   public static final int OFF_LINE_DEADLINE = 2;
	   
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
	    * 任务状态
	    */
	   public static final String TASK_STATE0 = "0";//未结束
	   public static final String TASK_STATE1 = "1";//结束
	   public static final String TASK_STATE2 = "2";//延期
	   public static final Map<String,Object> map = new HashMap<String,Object>();
	   public static Map<String,Object> getTaskStateMap(){
		   map.put("0", "未结束");
		   map.put("1", "结束");
		   map.put("2", "延期");
		   return map;
	   }
	   
	   /**
	    * 任务类型
	    */
	   public static final String TASK_TYPE0 = "0";//其它
	   public static final String TASK_TYPE1 = "1";//背单词
	   public static Map<String,Object> getTaskTypeMap(){
		   map.put("0", "其它");
		   map.put("1", "背单词");
		   return map;
	   }
	   
	   /**
	    * 用户任务完成百分比初始值
	    */
	   public static final String COMPLETE_PERCENT_INIT = "0";
	   
	   
	   /**
	    * dropLet对应的表
	    */
	   public static final String DROPLET_ID2 = "2";//ScenListDropLet
	   public static final String DROPLET_ID3 = "3";//CardListDroplet
	   public static final String DROPLET_ID4 = "4";//ScenarioDropLet
	   public static final String DROPLET_ID5 = "5";//TextChoiceDropLet
	   public static final String DROPLET_ID6 = "6";//FillBlankDropLet
	   public static final String DROPLET_ID7 = "7";//SuccessDropLet
	   public static final String DROPLET_ID8 = "8";//AudioChoiceDropLet
	   public static final String DROPLET_ID9 = "9";//ReadSpeakDropLet
	   public static final String DROPLET_ID10 = "10";//SortDropLet
	   public static final String DROPLET_ID11 = "11";//MatchDropLet
	   public static final String DROPLET_ID12 = "12";//VideoDropLet
	   public static final String DROPLET_ID13 = "13";//DictationDropLet
	   
	   //一个card 默认100个星
	   public static final Integer CARD_STAR = 100;
	   //卡片场景学习通过
	   public static final String CARD_STUDY_PASS = "0";
	   //卡片场景学习未通过
	   public static final String CARD_STUDY_NOPASS = "1";
	   
	   

}
