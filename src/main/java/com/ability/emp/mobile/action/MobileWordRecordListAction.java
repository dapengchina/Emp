package com.ability.emp.mobile.action;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ability.emp.mobile.server.MobileWordRecordService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/wordrecord")
public class MobileWordRecordListAction {
	@Resource
	private MobileWordRecordService wordRecordService;
	
	ObjectMapper objectMapper = new ObjectMapper(); 
	
	//易错单词接口
	@RequestMapping("/fallibleWord")
	@ResponseBody
	public Map<String, String> fallibleWord(String userId) throws JsonProcessingException {
		Map<String, String> map =new HashMap<String, String>();
		map = wordRecordService.fallibleWord(userId);
		objectMapper.writeValueAsString(map);
		return map;
	}

}
