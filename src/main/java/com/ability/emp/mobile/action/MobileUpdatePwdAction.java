package com.ability.emp.mobile.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.mobile.entity.MobileUserEntity;
import com.ability.emp.mobile.server.MobileUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/updatePwd")

public class MobileUpdatePwdAction{
	
	@Resource
	private MobileUserService mobileUserService;
	
	 ObjectMapper objectMapper = new ObjectMapper();
	
	@RequestMapping("/updateLoginPwd")
	@ResponseBody
	public String updateLoginPwd(@RequestBody MobileUserEntity mue) throws Exception {
		MobileUserEntity user = mobileUserService.queryById(mue.getId());
		String oldPwd = mue.getOldPwd();
		String newPwd = mue.getNewPwd();
		String code = user.getCode();
		if(code.equals(oldPwd)){
			mue.setCode(newPwd);
			if(mobileUserService.update(mue) > 0){
				return "1";
			}else{
				return "2";
			}
		}else{
			return "0"; //0 原密码不正确
		}
		
	}
	
}