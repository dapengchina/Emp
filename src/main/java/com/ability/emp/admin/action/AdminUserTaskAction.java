package com.ability.emp.admin.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.admin.entity.AdminTaskEntity;
import com.ability.emp.admin.entity.AdminThesauresPramEntity;
import com.ability.emp.admin.entity.AdminUserEntity;
import com.ability.emp.admin.entity.AdminUserTaskEntity;
import com.ability.emp.admin.entity.vo.AdminUserTaskVo;
import com.ability.emp.admin.server.AdminTaskService;
import com.ability.emp.admin.server.AdminThesauresPramService;
import com.ability.emp.admin.server.AdminUserTaskService;
import com.ability.emp.constant.SysConstant;
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
	
	private SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-dd");
	
	ObjectMapper objectMapper = new ObjectMapper();  
	
	
	/**
	 * 返回首页
	 * @param userEntity
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
			for(int i=0;i<userTaskList.size();i++){
				AdminUserTaskVo autv = new AdminUserTaskVo();
				AdminTaskEntity task = adminTaskService.queryTaskById(userTaskList.get(i).getTaskid());
				
				if(task!=null){
					if(task.getTasktype().equals(SysConstant.TASK_TYPE0)){
						AdminThesauresPramEntity thesaures = adminThesauresPramService.getByID(task.getThesaures_Type());
					    autv.setThesauresTypeName(thesaures.getName());
					    autv.setThesauresType(task.getThesauresType());
					    autv.setTasktypeName("背单词");
					}
					autv.setTaskname(task.getTaskname());
					autv.setStartDate(task.getStart_Date()!=null?sf.format(task.getStart_Date()):"");
					autv.setEndDate(task.getEnd_Date()!=null?sf.format(task.getEnd_Date()):"");
					autv.setTasktype(task.getTasktype());
				}
				autv.setCompletepercent(userTaskList.get(i).getCompletepercent());
				
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

}
