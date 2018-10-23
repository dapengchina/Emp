package com.ability.emp.admin.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.admin.entity.AdminScecategoryEntity;
import com.ability.emp.admin.entity.AdminTaskEntity;
import com.ability.emp.admin.entity.AdminThesauresPramEntity;
import com.ability.emp.admin.entity.AdminUserEntity;
import com.ability.emp.admin.entity.AdminUserTaskEntity;
import com.ability.emp.admin.entity.vo.AdminUserTaskVo;
import com.ability.emp.admin.server.AdminScecategoryService;
import com.ability.emp.admin.server.AdminTaskService;
import com.ability.emp.admin.server.AdminThesauresPramService;
import com.ability.emp.admin.server.AdminUserTaskService;
import com.ability.emp.constant.SysConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/admin/userTask")
public class AdminUserTaskAction {
	
	
	@Resource
	private AdminUserTaskService adminUserTaskService;
	
	@Resource
	private AdminTaskService adminTaskService;
	
	@Resource
	private AdminThesauresPramService adminThesauresPramService;
	
	@Resource
	private AdminScecategoryService adminScecategoryService;
	
	private SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-dd");
	
	ObjectMapper objectMapper = new ObjectMapper();  
	
	
	
	@RequestMapping("")
	public String taskprogressreport(HttpServletRequest request,HttpServletResponse response){
		return "taskprogressreport";
	}
	
	
	/**
	 * 
	 * @param pageSize
	 * @param pageNumber
	 * @param ae
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getUserTask")
	@ResponseBody
	public String getUserTask(int pageSize,int pageNumber,AdminUserTaskEntity ae) throws Exception {
		List<AdminUserTaskVo> list = new ArrayList<AdminUserTaskVo>();
		//第一个参数当前页码，第二个参数每页条数
		PageHelper.startPage(pageNumber,pageSize);  
		List<AdminUserTaskEntity> userTaskList = adminUserTaskService.getUserTaskByUserId(ae);
		if(userTaskList!=null && userTaskList.size()>0){
			AdminScecategoryEntity ase = new AdminScecategoryEntity();
			for(int i=0;i<userTaskList.size();i++){
				AdminUserTaskVo autv = new AdminUserTaskVo();
				AdminTaskEntity task = adminTaskService.queryTaskById(userTaskList.get(i).getTaskid());
				
				if(task!=null){
					if(task.getThesaures_Type()!=null){
						AdminThesauresPramEntity thesaures = adminThesauresPramService.getByID(task.getThesaures_Type());
					    autv.setThesauresTypeName(thesaures!=null?thesaures.getName():"");
					    autv.setThesauresType(task.getThesauresType());
					    autv.setTaskstateName(SysConstant.getTaskStateMap().get(task.getTaskstate()).toString());
					}else{
						autv.setTaskstateName(SysConstant.getTaskStateMap().get(task.getTaskstate()).toString());
					}
					if(task.getCourseid()!="-1" && task.getCourseid()!=null){
						ase.setId(task.getCourseid());
						AdminScecategoryEntity course = adminScecategoryService.getCourseByID(ase);
						if(course!=null){
							autv.setCoursename(course.getScecatname());
							autv.setDropletid(course.getDropletid()!=null?course.getDropletid():"");
							autv.setDropletconftypeid(course.getDropletconftypeid()!=null?course.getDropletconftypeid():"");
						}
					}
					autv.setTaskname(task.getTaskname());
					autv.setStartDate(task.getStart_Date()!=null?sf.format(task.getStart_Date()):"");
					autv.setEndDate(task.getEnd_Date()!=null?sf.format(task.getEnd_Date()):"");
					autv.setTaskstate(task.getTaskstate());
				}
				autv.setCompletepercent(userTaskList.get(i).getCompletepercent());
				autv.setUserid(userTaskList.get(i).getUserid());
				autv.setTaskid(userTaskList.get(i).getTaskid());
				
				list.add(autv);
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		@SuppressWarnings({ "unchecked", "rawtypes" })
		PageInfo<AdminUserEntity> page = new PageInfo(userTaskList);
		map.put("total",page.getTotal());
		map.put("rows", list);
		return objectMapper.writeValueAsString(map);
	}
	
	
	/**
	 * 
	 * @param taskid
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping("/getTask/{taskid}")
	@ResponseBody
	public String getTask(@PathVariable("taskid") String taskid) throws JsonProcessingException{
		Map<String,Object> map = new HashMap<String,Object>();
		AdminUserTaskEntity task = new AdminUserTaskEntity();
		task.setTaskid(taskid);
		List<AdminUserTaskEntity> list = adminUserTaskService.getTask(task);
		if(list!=null && list.size()>0){
			map.put("msg", "任务已指派,不可删除");
			map.put("code", "-1");
		}else{
			map.put("msg", "任务未指派,可删除");
			map.put("code", "0");
		}
		return objectMapper.writeValueAsString(map);
	}
	
	/**
	 * 
	 * @param userid
	 * @param taskid
	 * @return
	 * @throws JsonProcessingException 
	 */
	@RequestMapping("/deleteUserTask/{userid}/{taskid}")
	@ResponseBody
	public String deleteUserTask(@PathVariable("userid") String userid,@PathVariable("taskid") String taskid) throws JsonProcessingException{
		Map<String,Object> map = new HashMap<String,Object>();
		AdminUserTaskEntity aute = new AdminUserTaskEntity();
		aute.setUserid(userid);
		aute.setTaskid(taskid);
		int i = adminUserTaskService.deleteUserTask(aute);
		if(i>0){
			map.put("msg", "移除成功");
			map.put("code", "1");
		}else{
			map.put("msg", "移除失败");
			map.put("code", "-1");
		}
		return objectMapper.writeValueAsString(map);
	}

}
