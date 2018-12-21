package com.ability.emp.admin.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/adminLogin")
public class AdminLoginPageAction {
	
	
	@RequestMapping("")
	public String loginPage(){
		
		return "login";
	}

}
