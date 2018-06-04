package com.ability.emp.mobile.action;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.util.LoginUtil;
import com.ability.emp.util.TemplateChildMessageUtil;
import com.ability.emp.util.TemplateMessageUtil;
import com.ability.emp.util.TemplateMessageValueUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/templete")
public class MobileTempleteMessageAction {
	
	
	ObjectMapper objectMapper = new ObjectMapper(); 
	
	
	@RequestMapping("/message")
	@ResponseBody
	public String message(@RequestBody TemplateMessageUtil tmu) throws Exception {
	
		TemplateChildMessageUtil tcmu = new TemplateChildMessageUtil();
		TemplateMessageValueUtil tmvu1 = new TemplateMessageValueUtil();
		
		TemplateMessageValueUtil tmvu2 = new TemplateMessageValueUtil();
		TemplateMessageValueUtil tmvu3 = new TemplateMessageValueUtil();
		tmvu1.setValue("专业银行英语学习");
		tmvu2.setValue("正在学习中");
		tmvu3.setValue("您今天还未完成打卡任务");
		
		tcmu.setKeyword1(tmvu1);
		tcmu.setKeyword2(tmvu2);
		tcmu.setKeyword3(tmvu3);
		
		
		
		tmu.setData(tcmu);
		tmu.setForm_id("1527849443774");
		tmu.setTemplate_id("GLLVC6wsdPwZtACiSuqGiCuw1n8372dDeVxRpKn-oJk");
		tmu.setTouser("o2lEu5bIFui52igXj5OF63E7DFpI");
		String temp = objectMapper.writeValueAsString(tmu);
		String result = LoginUtil.sendTempleteMessage(temp);
		System.out.println(result);
		return "";
	}

}
