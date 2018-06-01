package com.ability.emp.mobile.action;

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
import com.ability.emp.mobile.entity.MobileWordRecordEntity;
import com.ability.emp.mobile.server.MobileWordRecordService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/easymistake")
public class MobileEasyMistakeAction {
	
	
	
	
	
	@Resource
	private MobileWordRecordService wordRecordService;
	
	ObjectMapper objectMapper = new ObjectMapper(); 
	
	
	@RequestMapping("/query/{id}")
	@ResponseBody
	public String query(@PathVariable("id") String id) throws JsonProcessingException {
		MobileWordRecordEntity mwre = new MobileWordRecordEntity();
		mwre.setUserId(id);
		mwre.setIsFail(SysConstant.ERROR);
		Map<String, Object> map =new HashMap<String, Object>();
		List<MobileWordRecordEntity> list = wordRecordService.queryEasyMistake(mwre);
		map.put("total", list.size());
		map.put("rows", list);
		
		return objectMapper.writeValueAsString(map);
	}

}
