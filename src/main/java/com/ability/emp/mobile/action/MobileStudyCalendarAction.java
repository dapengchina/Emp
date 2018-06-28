package com.ability.emp.mobile.action;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.mobile.entity.MobileHitCardEntity;
import com.ability.emp.mobile.entity.MobileTaskEntity;
import com.ability.emp.mobile.entity.MobileUserEntity;
import com.ability.emp.mobile.server.MobileStudyCalendarService;
import com.ability.emp.mobile.server.MobileTaskService;
import com.ability.emp.mobile.server.MobileUserService;
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
		    //获取用户任务ID
			MobileUserEntity mue = mobileUserService.queryById(id);
			//获取参数ID
			MobileTaskEntity mte = mobileTaskService.queryById(mue.getTaskid());
			
			QueryDateUtil qd = new QueryDateUtil();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
			if(mte!=null){
				if(mte.getStartDate()!=null && mte.getEndDate()!=null){
					qd.setStartDate(sf.format(mte.getStartDate()));
					qd.setEndDate(sf.format(mte.getEndDate()));
				}
			}
			return objectMapper.writeValueAsString(qd);
	   }

}
