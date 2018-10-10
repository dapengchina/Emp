package com.ability.emp.mobile.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.constant.SysConstant;
import com.ability.emp.mobile.entity.MobileAdminEntity;
import com.ability.emp.mobile.server.MobileAdminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/admin")
public class MobileAdminAction {
	
	
	   @Resource
	   private MobileAdminService mobileAdminService;
	   
	   ObjectMapper objectMapper = new ObjectMapper();
	   
	   @RequestMapping("/getTutor")
	   @ResponseBody
	   public String getTutor() throws JsonProcessingException{
		   MobileAdminEntity me = new MobileAdminEntity();
		   me.setStatus(SysConstant.STARTUSE);
		   List<MobileAdminEntity> tutorList = mobileAdminService.getTutor(me);
		   return objectMapper.writeValueAsString(tutorList);
	   }

}
