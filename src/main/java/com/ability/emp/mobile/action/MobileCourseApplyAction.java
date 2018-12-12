package com.ability.emp.mobile.action;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.mobile.entity.MobileCourseApplyEntity;
import com.ability.emp.mobile.server.MobileCourseApplyService;
import com.ability.emp.util.UUIDUtil;
import com.fasterxml.jackson.databind.ObjectMapper;


@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/courseApply")
public class MobileCourseApplyAction{
	@Resource
	private MobileCourseApplyService mobileCourseApplyService;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@RequestMapping("/addCourse")
	@ResponseBody
	public String addCourse(@RequestBody MobileCourseApplyEntity ase) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		List<MobileCourseApplyEntity> courseCheck =  mobileCourseApplyService.selectByCourseInfo(ase);
		if(courseCheck.size()>0 ){
			map.put("state",2);  // 2已存在；1成功；0未成功
			return objectMapper.writeValueAsString(map);
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp applydate =null;
		try {
			applydate = new Timestamp(sf.parse(sf.format(new Date())).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ase.setApplydate(applydate);
		ase.setId(UUIDUtil.generateUUID());
		ase.setApprovestate("0");
		ase.setApprovedate(null);
		if(mobileCourseApplyService.insert(ase)){
			map.put("state",1);
		}else{
			map.put("state",0);
		}
		return objectMapper.writeValueAsString(map);
	}
}