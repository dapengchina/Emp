package com.ability.emp.admin.action;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.admin.entity.AdminScecategoryEntity;
import com.ability.emp.admin.server.AdminScecategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/admin/scecategory")
public class AdminScecategoryAction {
	
	
	
	@Resource
	private AdminScecategoryService adminScecategoryService;
	
	ObjectMapper objectMapper = new ObjectMapper();  
	
	
	/**
	 * 返回首页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getScecategory")
	@ResponseBody
	public String getScecategory(AdminScecategoryEntity ase) throws JsonProcessingException{
		List<AdminScecategoryEntity> list = adminScecategoryService.getCourse(ase);
		return objectMapper.writeValueAsString(list);
	}

}
