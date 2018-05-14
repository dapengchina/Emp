package com.ability.emp.admin.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ability.emp.admin.entity.AdminWordRecordEntity;
import com.ability.emp.admin.server.AdminWordRecordService;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/admin/wordrecord")
public class AdminWordRecordListAction {
	@Resource
	private AdminWordRecordService wordRecordService;
	
	ObjectMapper objectMapper = new ObjectMapper();  
	
	@RequestMapping("/queryAll")
	@ResponseBody
	public List<AdminWordRecordEntity> queryAll() throws Exception {
		/*
		 * List<AdminWordRecordEntity> list = wordRecordService.queryAll();
		String wordId = list.get(0).getWord();
		String word = list.get(0).getId();
		*/
		return wordRecordService.queryAll();
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Integer insert(AdminWordRecordEntity wordRecordEntiy) {
		return wordRecordService.insert(wordRecordEntiy);
	}
	
	//易错单词接口
	@RequestMapping("/fallibleWord")
	@ResponseBody
	public Map<String, String> fallibleWord(String userId) {
		return wordRecordService.fallibleWord(userId);
	}
}
