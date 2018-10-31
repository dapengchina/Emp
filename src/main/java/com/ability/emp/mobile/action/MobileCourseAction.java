package com.ability.emp.mobile.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.constant.SysConstant;
import com.ability.emp.mobile.entity.MobileSceCategoryEntity;
import com.ability.emp.mobile.entity.vo.CourseVo;
import com.ability.emp.mobile.server.MobileSceCategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/getCourse")
public class MobileCourseAction {
	
	ObjectMapper objectMapper = new ObjectMapper(); 
	
	@Resource
	private MobileSceCategoryService mobileSceCategoryService;
	
	
	
	@RequestMapping("/getCourse")
	@ResponseBody
	public String getCourse() throws Exception {
		List<CourseVo> list = new ArrayList<CourseVo>();
		MobileSceCategoryEntity me = new MobileSceCategoryEntity();
		me.setCoursestate(SysConstant.ON_LINE);//查询在线课程
		List<MobileSceCategoryEntity> courseList = mobileSceCategoryService.getCourseData(me);
		for(int i=0;i<courseList.size();i++){
			CourseVo cv = new CourseVo();
			cv.setCourseID(courseList.get(i).getId());
			cv.setScecatname(courseList.get(i).getScecatname());
			cv.setIcon(SysConstant.SERVICE_HOST+courseList.get(i).getIcon());
			cv.setCoursestate(courseList.get(i).getCoursestate());
			cv.setCoursestatename(SysConstant.getCourseStateMap().get(courseList.get(i).getCoursestate()).toString());
			
			list.add(cv);
		}
		return objectMapper.writeValueAsString(list);
	}

}
