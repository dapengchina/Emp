package com.ability.emp.mobile.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.mobile.entity.MobileUserTaskEntity;
import com.ability.emp.mobile.server.MobileUserTaskService;
import com.ability.emp.util.UUIDUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/userTask")
public class MobileUserTaskAction {
	
	
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Resource
	private MobileUserTaskService mobileUserTaskService;
	
	
	@RequestMapping("/addUserTask/{id}")
	@ResponseBody
	public String addUserTask(MobileUserTaskEntity me) throws JsonProcessingException{
		Map<String,Object> map = new HashMap<String,Object>();
		me.setId(UUIDUtil.generateUUID());//主键
		int i = mobileUserTaskService.addUserTask(me);
		if(i>0){
			map.put("result", "保存任务成功");
			map.put("code", 1);
		}else{
			map.put("result", "保存任务失败");
			map.put("code", 0);
		}
		return objectMapper.writeValueAsString(map);
	}

}
