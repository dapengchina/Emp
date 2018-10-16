package com.ability.emp.mobile.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.mobile.entity.MobileSubTaskEntity;
import com.ability.emp.mobile.server.MobileSubTaskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/subtask")
public class MobileSubTaskAction {
	
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Resource
	private MobileSubTaskService mobileSubTaskService;
	
	
	@RequestMapping("/saveScore")
	@ResponseBody
	public String saveScore(@RequestBody MobileSubTaskEntity mste) throws JsonProcessingException{
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			MobileSubTaskEntity subtask = mobileSubTaskService.getSubTask(mste);
			if(subtask!=null){
				mste.setId(subtask.getId());
				mobileSubTaskService.saveScore(mste);
				map.put("result", "保存分数成功");
				map.put("code", "1");
			}
		}catch(Exception e){
			map.put("result", "保存分数失败");
			map.put("code", "-1");
		}
		return objectMapper.writeValueAsString(map);
	}

}
