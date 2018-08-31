package com.ability.emp.mobile.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.constant.SysConstant;
import com.ability.emp.mobile.entity.vo.CardListDropletVo;
import com.ability.emp.mobile.entity.vo.ScenListDropLetDataVo;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/getCardListDroplet")
public class MobileCardListDropletAction {
	
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	
	
	@RequestMapping("/getCardListDroplet")
	@ResponseBody
	public String getCardListDroplet() throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		List list = new ArrayList();
		CardListDropletVo cdv1 = new CardListDropletVo();
		cdv1.setCardName("Scenario");
		cdv1.setCardIcon(SysConstant.SERVICE_HOST+"/Emp/mobile/page_011/3.png");
		cdv1.setCardBackImag(SysConstant.SERVICE_HOST+"/Emp/mobile/page_011/5.png");
		cdv1.setCurrentScore("1");
		cdv1.setIndex("1");
		
		CardListDropletVo cdv2 = new CardListDropletVo();
		cdv2.setCardName("Listening");
		cdv2.setCardIcon(SysConstant.SERVICE_HOST+"/Emp/mobile/page_011/1.png");
		cdv2.setCardBackImag(SysConstant.SERVICE_HOST+"/Emp/mobile/page_011/6.png");
		cdv2.setCurrentScore("1");
		cdv2.setIndex("2");
		
		CardListDropletVo cdv3 = new CardListDropletVo();
		cdv3.setCardName("Speaking");
		cdv3.setCardIcon(SysConstant.SERVICE_HOST+"/Emp/mobile/page_011/2.png");
		cdv3.setCardBackImag(SysConstant.SERVICE_HOST+"/Emp/mobile/page_011/7.png");
		cdv3.setCurrentScore("1");
		cdv3.setIndex("3");
		
		CardListDropletVo cdv4 = new CardListDropletVo();
		cdv4.setCardName("Grammer");
		cdv4.setCardIcon(SysConstant.SERVICE_HOST+"/Emp/mobile/page_011/2.png");
		cdv4.setCardBackImag(SysConstant.SERVICE_HOST+"/Emp/mobile/page_011/8.png");
		cdv4.setCurrentScore("1");
		cdv4.setIndex("4");
		
		CardListDropletVo cdv5 = new CardListDropletVo();
		cdv5.setCardName("Dictation");
		cdv5.setCardIcon(SysConstant.SERVICE_HOST+"/Emp/mobile/page_011/2.png");
		cdv5.setCardBackImag(SysConstant.SERVICE_HOST+"/Emp/mobile/page_011/8.png");
		cdv5.setCurrentScore("1");
		cdv5.setIndex("5");
		
		CardListDropletVo cdv6 = new CardListDropletVo();
		cdv6.setCardName("Matching");
		cdv6.setCardIcon(SysConstant.SERVICE_HOST+"/Emp/mobile/page_011/2.png");
		cdv6.setCardBackImag(SysConstant.SERVICE_HOST+"/Emp/mobile/page_011/8.png");
		cdv6.setCurrentScore("1");
		cdv6.setIndex("6");
		
		list.add(cdv1);
		list.add(cdv2);
		list.add(cdv3);
		list.add(cdv4);
		list.add(cdv5);
		list.add(cdv6);
		
		return objectMapper.writeValueAsString(list);
	}

}
