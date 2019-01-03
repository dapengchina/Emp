package com.ability.emp.mobile.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.constant.SysConstant;
import com.ability.emp.mobile.entity.MobileSceCategoryEntity;
import com.ability.emp.mobile.entity.MobileTaskEntity;
import com.ability.emp.mobile.entity.MobileUserTaskEntity;
import com.ability.emp.mobile.entity.vo.CourseVo;
import com.ability.emp.mobile.server.MobileSceCategoryService;
import com.ability.emp.mobile.server.MobileTaskService;
import com.ability.emp.mobile.server.MobileUserTaskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/myCourse")
public class MobileMyCourseAction {
	
	
	
	@Resource
	private MobileUserTaskService mobileUserTaskService;
	
	@Resource
	private MobileTaskService mobileTaskService;
	
	@Resource
	private MobileSceCategoryService mobileSceCategoryService;
	
	ObjectMapper objectMapper = new ObjectMapper(); 
	
	
	private SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-dd");
	
	
	@RequestMapping("/getMyCourse/{userid}")
	@ResponseBody
	public String getMyCourse(@PathVariable("userid") String userid) throws JsonProcessingException{
		List<CourseVo> list = new ArrayList<CourseVo>();
		MobileUserTaskEntity me = new MobileUserTaskEntity();
		me.setUserid(userid);
		me.setState(SysConstant.TASK_STATE0);
		List<MobileUserTaskEntity> userTaskList = mobileUserTaskService.getUserTask(me);
		if(userTaskList!=null && userTaskList.size()>0){
			MobileTaskEntity mte = new MobileTaskEntity();
			MobileSceCategoryEntity course = new MobileSceCategoryEntity();
			for(int i=0;i<userTaskList.size();i++){
				mte.setId(userTaskList.get(i).getTaskid());
				mte.setTaskstate(SysConstant.TASK_STATE0);
				MobileTaskEntity task = mobileTaskService.getTask(mte);
				if(task!=null){
					course.setId(task.getCourseid());
					//查询用户的课程
					MobileSceCategoryEntity co = mobileSceCategoryService.getCourse(course);
					if(co!=null){
						CourseVo cv = new CourseVo();
						cv.setCourseID(co.getId());
						cv.setScecatname(co.getScecatname());
						cv.setIcon(SysConstant.SERVICE_HOST+co.getIcon());
						cv.setCoursestate(co.getCoursestate());
						cv.setCoursestatename(SysConstant.getCourseStateMap().get(co.getCoursestate()).toString());
						
						if(co.getCoursestate().equals(SysConstant.OFF_LINE)){
							if(co.getCourseenddate()!=null && !"".equals(co.getCourseenddate())){
								cv.setCourseEndDate(sf.format(co.getCourseenddate()));
							}
						}
						
						list.add(cv);
					}
				}
			}
		}
		
		return objectMapper.writeValueAsString(list);
	}

}
