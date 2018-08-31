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
import com.ability.emp.mobile.entity.vo.ScenListDropLetVo;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/getScenListDropLetData")
public class MobileGetScenListDropLetDataAction {
	
	
	
ObjectMapper objectMapper = new ObjectMapper();  
	
	
	
	@RequestMapping("/getScenListDropLetData/{dropLetId}/{dropLetConfTypeId}")
	@ResponseBody
	public String getIndexData(@PathVariable("dropLetId") String dropLetId,@PathVariable("dropLetConfTypeId") String dropLetConfTypeId) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		List list = new ArrayList();
		ScenListDropLetVo  sv1 = new ScenListDropLetVo();
		sv1.setScenName("Stand Up Meeting");
		sv1.setRelaDropLet("../page_011/page_011");
		sv1.setAverageScore("83");
		sv1.setIndex("1");
		sv1.setCompPerc("60");
		sv1.setCurrentPoint("100");
		sv1.setTotalPoint("500");
		sv1.setScenIcon(SysConstant.SERVICE_HOST+"/Emp/mobile/page_021/5.png");
		
		ScenListDropLetVo  sv2 = new ScenListDropLetVo();
		sv2.setScenName("Working Report");
		sv2.setIndex("2");
		sv2.setCompPerc("0");
		sv2.setCurrentPoint("100");
		sv2.setTotalPoint("500");
		sv2.setScenIcon(SysConstant.SERVICE_HOST+"/Emp/mobile/page_021/1.png");
		
		ScenListDropLetVo  sv3 = new ScenListDropLetVo();
		sv3.setScenName("Issue Report");
		sv3.setIndex("3");
		sv3.setCompPerc("0");
		sv3.setCurrentPoint("100");
		sv3.setTotalPoint("500");
		sv3.setScenIcon(SysConstant.SERVICE_HOST+"/Emp/mobile/page_021/2.png");
		
		ScenListDropLetVo  sv4 = new ScenListDropLetVo();
		sv4.setScenName("Exchange Meeting");
		sv4.setIndex("4");
		sv4.setCompPerc("0");
		sv4.setCurrentPoint("100");
		sv4.setTotalPoint("500");
		sv4.setScenIcon(SysConstant.SERVICE_HOST+"/Emp/mobile/page_021/6.png");
		
		ScenListDropLetVo  sv5 = new ScenListDropLetVo();
		sv5.setScenName("Progress Report");
		sv5.setIndex("5");
		sv5.setCompPerc("0");
		sv5.setCurrentPoint("100");
		sv5.setTotalPoint("500");
		sv5.setScenIcon(SysConstant.SERVICE_HOST+"/Emp/mobile/page_021/7.png");
		
		ScenListDropLetVo  sv6 = new ScenListDropLetVo();
		sv6.setScenName("Shown Demo");
		sv6.setIndex("6");
		sv6.setCompPerc("60");
		sv6.setCurrentPoint("100");
		sv6.setTotalPoint("500");
		sv6.setScenIcon(SysConstant.SERVICE_HOST+"/Emp/mobile/page_021/3.png");
		
		ScenListDropLetVo  sv7 = new ScenListDropLetVo();
		sv7.setScenName("Finance Report");
		sv7.setIndex("7");
		sv7.setCompPerc("0");
		sv7.setCurrentPoint("100");
		sv7.setTotalPoint("500");
		sv7.setScenIcon(SysConstant.SERVICE_HOST+"/Emp/mobile/page_021/4.png");
		
		
		list.add(sv1);
		list.add(sv2);
		list.add(sv3);
		
		list.add(sv4);
		list.add(sv5);
		list.add(sv6);
		list.add(sv7);
		
		
		System.out.println("=============="+dropLetConfTypeId);
		return objectMapper.writeValueAsString(list);
	}

}
