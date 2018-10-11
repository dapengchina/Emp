package com.ability.emp.mobile.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.constant.SysConstant;
import com.ability.emp.mobile.entity.MobileAdminEntity;
import com.ability.emp.mobile.entity.vo.TutorVo;
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
		   List<TutorVo> list = new ArrayList<TutorVo>();
		   MobileAdminEntity me = new MobileAdminEntity();
		   me.setStatus(SysConstant.STARTUSE);
		   List<MobileAdminEntity> tutorList = mobileAdminService.getTutor(me);
		   TutorVo temp = new TutorVo();
		   temp.setId("-1");
		   temp.setName("--请选择--");
		   list.add(temp);
		   for(int i=0;i<tutorList.size();i++){
			   TutorVo tv = new TutorVo();
			   tv.setId(tutorList.get(i).getId());
			   tv.setName(tutorList.get(i).getAdminName());
			   
			   list.add(tv);
		   }
		   return objectMapper.writeValueAsString(list);
	   }

}
