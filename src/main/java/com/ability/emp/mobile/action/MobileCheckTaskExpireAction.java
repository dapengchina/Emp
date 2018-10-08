package com.ability.emp.mobile.action;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.constant.SysConstant;
import com.ability.emp.mobile.entity.MobileTaskEntity;
import com.ability.emp.mobile.entity.MobileUserTaskEntity;
import com.ability.emp.mobile.server.MobileTaskService;
import com.ability.emp.mobile.server.MobileUserService;
import com.ability.emp.mobile.server.MobileUserTaskService;
import com.fasterxml.jackson.databind.ObjectMapper;



@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/checkTask")
public class MobileCheckTaskExpireAction {
	
	
	
	@Resource
	private MobileUserService mobileUserService;
	
	@Resource
	private MobileTaskService mobileTaskService;
	
	@Resource
	private MobileUserTaskService mobileUserTaskService;
	
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	
	
	/**
	 * 返回数据
	 * @param id 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/whetherExpire/{id}")
	@ResponseBody
	public String query(@PathVariable("id") String id) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//获取用户任务
		MobileUserTaskEntity userTask = new MobileUserTaskEntity();
		userTask.setUserid(id);
		List<MobileUserTaskEntity> userTaskList = mobileUserTaskService.getUserTask(userTask);
				
		
		for(int i=0;i<userTaskList.size();i++){
			//查询用户未完成的任务
			if(userTaskList.get(i).getCompletepercent().equals(SysConstant.COMPLETE_PERCENT_INIT)){
				MobileTaskEntity task = mobileTaskService.queryById(userTaskList.get(i).getTaskid());
				if(task.getCourseid().equals(SysConstant.TASK_TYPE1)){
					map.put("endDate", sdf.format(task.getEndDate()));
					break;
				}
			}
		}
		return objectMapper.writeValueAsString(map);
	}

}
