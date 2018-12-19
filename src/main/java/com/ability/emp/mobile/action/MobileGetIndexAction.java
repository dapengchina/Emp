package com.ability.emp.mobile.action;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.constant.SysConstant;
import com.ability.emp.mobile.entity.MobileDropLetEntity;
import com.ability.emp.mobile.entity.MobileFirstCategoryEntity;
import com.ability.emp.mobile.entity.MobileSceCategoryEntity;
import com.ability.emp.mobile.entity.MobileSubTaskEntity;
import com.ability.emp.mobile.entity.MobileTaskEntity;
import com.ability.emp.mobile.entity.MobileUserTaskEntity;
import com.ability.emp.mobile.entity.bean.SceCategoryBean;
import com.ability.emp.mobile.entity.MobileHomeAnnounceEntity;
import com.ability.emp.mobile.entity.vo.FirstCategoryVo;
import com.ability.emp.mobile.server.MobileDropLetService;
import com.ability.emp.mobile.server.MobileFirstCategoryService;
import com.ability.emp.mobile.server.MobileSceCategoryService;
import com.ability.emp.mobile.server.MobileSubTaskService;
import com.ability.emp.mobile.server.MobileTaskService;
import com.ability.emp.mobile.server.MobileUserTaskService;
import com.ability.emp.mobile.server.MobileHomeAnnounceService;
import com.fasterxml.jackson.databind.ObjectMapper;



@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/getIndexData")
public class MobileGetIndexAction {
	
	
	
	
	ObjectMapper objectMapper = new ObjectMapper();  
	
	@Resource
	private MobileFirstCategoryService mobileFirstCategoryService;
	
	@Resource
	private MobileSceCategoryService mobileSceCategoryService;
	
	@Resource
	private MobileDropLetService mobileDropLetService;
	
	@Resource
	private MobileUserTaskService mobileUserTaskService;
	
	@Resource
	private MobileTaskService mobileTaskService;
	
	@Resource
	private MobileSubTaskService mobileSubTaskService;
	
	@Resource
	private MobileHomeAnnounceService mobileHomeAnnounceService;
	
	
	
	@SuppressWarnings({ "unchecked", "null" })
	@RequestMapping("/getIndexData/{userid}")
	@ResponseBody
	public String getIndexData(@PathVariable("userid") String userid) throws Exception {
		
		Map<String,Object> map = new HashMap<String,Object>();
		MobileFirstCategoryEntity me = new MobileFirstCategoryEntity();
		MobileSceCategoryEntity mf = new MobileSceCategoryEntity();
		MobileDropLetEntity md = new MobileDropLetEntity();
		@SuppressWarnings("rawtypes")
		List firstlist = new ArrayList();
		
		
		List<MobileFirstCategoryEntity> list = mobileFirstCategoryService.getIndexData(me);
		
		for(int i=0;i<list.size();i++){
			@SuppressWarnings("rawtypes")
			List seclist = new ArrayList();
			FirstCategoryVo fv = new FirstCategoryVo();
			fv.setFirCatName(list.get(i).getFircatname());
			fv.setIndex(list.get(i).getIndex().toString());
			
        	mf.setFircatid(list.get(i).getId());
        	List<MobileSceCategoryEntity> mobileSceCategoryList = mobileSceCategoryService.getScenListDropLetData(mf);
        	for(int j=0;j<mobileSceCategoryList.size();j++){
        		SceCategoryBean scb = new SceCategoryBean();
        		scb.setSceCatName(mobileSceCategoryList.get(j).getScecatname());
        		scb.setIcon(SysConstant.SERVICE_HOST+mobileSceCategoryList.get(j).getIcon());
        		scb.setIndex(mobileSceCategoryList.get(j).getIndex().toString());
        		scb.setDropLetId(mobileSceCategoryList.get(j).getDropletid());
        		scb.setDropLetConfigTypeId(mobileSceCategoryList.get(j).getDropletconftypeid());
        		scb.setId(mobileSceCategoryList.get(j).getId());
        		//如果课程类型是工具类，则所有人都可以使用
        		if(mobileSceCategoryList.get(j).getCoursetype().equals(SysConstant.COURSE_TYPE0)){
        			scb.setIfpermissions(true);
        		}else{
        			scb.setIfpermissions(PermissionProcess(userid,mobileSceCategoryList.get(j).getId()));
        		}
        		
        		md.setId(mobileSceCategoryList.get(j).getDropletid());
        		MobileDropLetEntity mde = mobileDropLetService.getDropLetByID(md);
        		if(mde!=null){
        			scb.setDropLetLink(mde.getDropletlink());
        		}
        		seclist.add(scb);
        	}
        	fv.setSceCategoryListBean(seclist);
        	firstlist.add(fv);
        }
		
		/**
		 * 处理顶部power用户总得分
		 */
		MobileSubTaskEntity userSubTask = new MobileSubTaskEntity();
		userSubTask.setUserid(userid);
		double score = 0.0;
		List<MobileSubTaskEntity> userSubTaskList = mobileSubTaskService.getSubTaskList(userSubTask);
		if(userSubTaskList!=null){
			for(int k=0;k<userSubTaskList.size();k++){
				if(userSubTaskList.get(k).getScore()!=null && !"".equals(userSubTaskList.get(k).getScore())){
					score = score + Double.parseDouble(userSubTaskList.get(k).getScore());
				}
			}
		}
		
		/**首页轮播及公告**/
		MobileHomeAnnounceEntity ase = new MobileHomeAnnounceEntity();
		ase.setGrouptype("1");
		ase.setState("1");
		MobileHomeAnnounceEntity titlease = new MobileHomeAnnounceEntity();
		titlease.setGrouptype("2");
		titlease.setState("1");
		List<MobileHomeAnnounceEntity> imgList =  mobileHomeAnnounceService.selectList(ase);
		List<MobileHomeAnnounceEntity> msgList = mobileHomeAnnounceService.selectList(titlease);
		map.put("list", firstlist);
		map.put("score", score);//用户总得分
		map.put("imgList", imgList); //轮播图片信息
		map.put("msgList", msgList); //轮播公告信息
		return objectMapper.writeValueAsString(map);
	}
	
	/**
	 * 用户权限处理
	 * @param userid
	 */
	public boolean PermissionProcess(String userid,String courseid){
		MobileUserTaskEntity usertask = new MobileUserTaskEntity();
		MobileTaskEntity task = new MobileTaskEntity();
		Set<String> userCourseIDSet = new HashSet<String>();
		usertask.setUserid(userid);
		List<MobileUserTaskEntity> userTaskList = mobileUserTaskService.getUserTask(usertask);
		if(userTaskList!=null && userTaskList.size()>0){
			for(int i=0;i<userTaskList.size();i++){
				task.setId(userTaskList.get(i).getTaskid());
				MobileTaskEntity retask = mobileTaskService.getTask(task);
				if(retask!=null){
					userCourseIDSet.add(retask.getCourseid());
				}
			}
		}
		return userCourseIDSet.contains(courseid);
	}

}
