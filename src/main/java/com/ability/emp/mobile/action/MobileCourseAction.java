package com.ability.emp.mobile.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/getCourse")
public class MobileCourseAction {
	
	ObjectMapper objectMapper = new ObjectMapper(); 
	
	@Resource
	private MobileSceCategoryService mobileSceCategoryService;
	
	@Resource
	private MobileUserTaskService mobileUserTaskService;
	
	@Resource
	private MobileTaskService mobileTaskService;
	
	private SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-dd");
	
	
	
	@RequestMapping("/getCourse")
	@ResponseBody
	public String getCourse() throws Exception {
		List<CourseVo> list = new ArrayList<CourseVo>();
		MobileSceCategoryEntity me = new MobileSceCategoryEntity();
		me.setCoursetype(SysConstant.COURSE_TYPE1);//查询课程
		List<MobileSceCategoryEntity> courseList = mobileSceCategoryService.getCourseData(me);
		for(int i=0;i<courseList.size();i++){
			CourseVo cv = new CourseVo();
			cv.setCourseID(courseList.get(i).getId());
			cv.setScecatname(courseList.get(i).getScecatname());
			cv.setIcon(SysConstant.SERVICE_HOST+courseList.get(i).getIcon());
			cv.setCoursestate(courseList.get(i).getCoursestate());
			cv.setCoursestatename(SysConstant.getCourseStateMap().get(courseList.get(i).getCoursestate()).toString());
			
			if(courseList.get(i).getCoursestate().equals(SysConstant.OFF_LINE)){
				if(courseList.get(i).getCourseenddate()!=null && !"".equals(courseList.get(i).getCourseenddate())){
					cv.setCourseEndDate(sf.format(courseList.get(i).getCourseenddate()));
				}
			}
			
			list.add(cv);
		}
		return objectMapper.writeValueAsString(list);
	}
	
	
	/**
	 * 返回用户未选择的课程
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getUserNotSelectCourse/{userId}")
	@ResponseBody
	public String getUserNotSelectCourse(@PathVariable("userId") String userId) throws Exception {
		
		/**
		 * 拿到用户选择的课程(未结束的)
		 */
		//根据userid去查询usertask
		MobileUserTaskEntity usertask = new MobileUserTaskEntity();
		usertask.setUserid(userId);
		List<MobileUserTaskEntity> usertasklist = mobileUserTaskService.getUserTask(usertask);
		//根据taskid去查询task
		MobileTaskEntity task = new MobileTaskEntity();
		Set<String> selectedCourse = new HashSet<String>();
		for(int i=0;i<usertasklist.size();i++){
			task.setId(usertasklist.get(i).getTaskid());
			task.setTaskstate(SysConstant.TASK_STATE0);//未结束
			MobileTaskEntity retask = mobileTaskService.getTask(task);
			if(retask!=null){
				selectedCourse.add(retask.getCourseid());
			}
		}
		
		/**
		 * 拿到全部课程
		 */
		MobileSceCategoryEntity me = new MobileSceCategoryEntity();
		me.setCoursetype(SysConstant.COURSE_TYPE1);//查询课程
		List<MobileSceCategoryEntity> allCourseList = mobileSceCategoryService.getCourseData(me);
		
		/**
		 * 返回用户未选择的课程
		 */
		List<CourseVo> list = new ArrayList<CourseVo>();
		for(int j=0;j<allCourseList.size();j++){
			//返回true表示此课程已被用户选择
			boolean flag = selectedCourse.contains(allCourseList.get(j).getId());
		    if(flag==false){
		    	CourseVo cv = new CourseVo();
				cv.setCourseID(allCourseList.get(j).getId());
				cv.setScecatname(allCourseList.get(j).getScecatname());
				cv.setIcon(SysConstant.SERVICE_HOST+allCourseList.get(j).getIcon());
				cv.setCoursestate(allCourseList.get(j).getCoursestate());
				cv.setCoursestatename(SysConstant.getCourseStateMap().get(allCourseList.get(j).getCoursestate()).toString());
				
				if(allCourseList.get(j).getCoursestate().equals(SysConstant.OFF_LINE)){
					if(allCourseList.get(j).getCourseenddate()!=null && !"".equals(allCourseList.get(j).getCourseenddate())){
						cv.setCourseEndDate(sf.format(allCourseList.get(j).getCourseenddate()));
					}
				}
				
				list.add(cv);
		    }
		}
		return objectMapper.writeValueAsString(list);
	}

	
	
	/**
	 * 选择课程
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/chooseCourse")
	@ResponseBody
	public String chooseCourse(@RequestBody MobileSceCategoryEntity me) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			mobileSceCategoryService.chooseCourse(me);
			map.put("msg", "选择成功");
			map.put("code", "1");
		}catch(Exception e){
			map.put("msg", "选择失败");
			map.put("code", "0");
		}
		
		return objectMapper.writeValueAsString(map);
	}
}
