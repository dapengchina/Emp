package com.ability.emp.mobile.action;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.constant.SysConstant;
import com.ability.emp.mobile.entity.MobileHitCardEntity;
import com.ability.emp.mobile.entity.MobileTaskEntity;
import com.ability.emp.mobile.entity.MobileUserTaskEntity;
import com.ability.emp.mobile.server.MobileStudyCalendarService;
import com.ability.emp.mobile.server.MobileTaskService;
import com.ability.emp.mobile.server.MobileUserService;
import com.ability.emp.mobile.server.MobileUserTaskService;
import com.ability.emp.util.QueryDateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/studycalendar")
public class MobileStudyCalendarAction {
	
	
	   @Resource
	   private MobileStudyCalendarService mobileStudyCalendarService;
	   
	   @Resource
	   private MobileUserService mobileUserService;
	   
	   @Resource
	   private MobileTaskService mobileTaskService;
	   
	   @Resource
	   private MobileUserTaskService mobileUserTaskService;
	   
	   private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
	
	   ObjectMapper objectMapper = new ObjectMapper(); 
	   
	   
	   /**
	    * 学习日历 返回用户打卡数据
	    * @param id 用户ID
	    * @param date 年月
	    * @return
	    * @throws JsonProcessingException 
	    */
	   @RequestMapping("/query/{id}/{date}")
	   @ResponseBody
	   public String query(@PathVariable("id") String id,@PathVariable("date") String date) throws JsonProcessingException{
		   MobileHitCardEntity mhce = new MobileHitCardEntity();
		   mhce.setUserId(id);
		   mhce.setStringDate(date);
		   List<MobileHitCardEntity> list = mobileStudyCalendarService.studyCalendar(mhce);
		   SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		   for(int i=0;i<list.size();i++){
			   list.get(i).setStringDate(sf.format(list.get(i).getDate()));
		   }
		   return objectMapper.writeValueAsString(list);
	   }
	   
	   /**
	    * 学习日历 返回用户开始任务月份和结束月份
	    * @param id 用户ID
	    * @param date 年月
	    * @return
	    * @throws JsonProcessingException 
	    */
	   @RequestMapping("/queryDate/{id}")
	   @ResponseBody
	   public String queryDate(@PathVariable("id") String id) throws JsonProcessingException{
		 QueryDateUtil qd = new QueryDateUtil();
		 //获取用户任务
		 MobileUserTaskEntity userTask = new MobileUserTaskEntity();
		 userTask.setUserid(id);
		 List<MobileUserTaskEntity> userTaskList = mobileUserTaskService.getUserTask(userTask);
			
		 for(int i=0;i<userTaskList.size();i++){
			//查询用户未完成的任务
			if(userTaskList.get(i).getCompletepercent().equals(SysConstant.COMPLETE_PERCENT_INIT)){
				MobileTaskEntity task = mobileTaskService.queryById(userTaskList.get(i).getTaskid());
				if(task.getTasktype().equals(SysConstant.TASK_TYPE0)){
					if(task.getStartDate()!=null && task.getEndDate()!=null){
						qd.setStartDate(sf.format(task.getStartDate()));
						qd.setEndDate(sf.format(task.getEndDate()));
					}
				}
			}
		 }
			
		 return objectMapper.writeValueAsString(qd);
	   }

}
