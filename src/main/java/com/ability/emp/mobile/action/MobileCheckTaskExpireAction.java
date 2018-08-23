package com.ability.emp.mobile.action;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.mobile.entity.MobileTaskEntity;
import com.ability.emp.mobile.entity.MobileUserEntity;
import com.ability.emp.mobile.server.MobileTaskService;
import com.ability.emp.mobile.server.MobileUserService;
import com.fasterxml.jackson.databind.ObjectMapper;



@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/checkTask")
public class MobileCheckTaskExpireAction {
	
	
	
	@Resource
	private MobileUserService mobileUserService;
	
	@Resource
	private MobileTaskService mobileTaskService;
	
	
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
		//获取用户任务ID
		MobileUserEntity mue = mobileUserService.queryById(id);
		//获取参数ID
		MobileTaskEntity mte = mobileTaskService.queryById(mue.getTaskid());
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("endDate", sdf.format(mte.getEndDate()));
		
		return objectMapper.writeValueAsString(map);
	}

}
