package com.ability.emp.mobile.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.mobile.entity.MobileFirstCategoryEntity;
import com.ability.emp.mobile.server.MobileFirstCategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/firstCategory")
public class MobileFirstCategoryAction{
	@Resource
	private MobileFirstCategoryService mobileFirstCategoryService;
	
	ObjectMapper objectMapper = new ObjectMapper();  
	
	@RequestMapping("/getFirstCategory")
	@ResponseBody
	public String getFirstCategory() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		MobileFirstCategoryEntity me = new MobileFirstCategoryEntity();
		List<MobileFirstCategoryEntity> list = mobileFirstCategoryService.getIndexData(me);
		map.put("list", list);
		return objectMapper.writeValueAsString(map);
	}
}


