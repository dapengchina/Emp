package com.ability.emp.admin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.admin.entity.AdminScenListDropLetEntity;
import com.ability.emp.admin.entity.AdminUserSubTaskEntity;
import com.ability.emp.admin.server.AdminScenListDropLetService;
import com.ability.emp.admin.server.AdminUserSubTaskService;
import com.ability.emp.constant.SysConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/admin/usersubtask")
public class AdminUserSubTaskAction {
	
	   ObjectMapper objectMapper = new ObjectMapper();  
	
	   @Resource
	   private AdminUserSubTaskService adminUserSubTaskService;
	   
	   @Resource
	   private AdminScenListDropLetService adminScenListDropLetService;
	   
	   
	   @RequestMapping("/getUserSubTask")
	   @ResponseBody
	   public String getUserSubTask(@RequestBody AdminUserSubTaskEntity auste) throws JsonProcessingException{
		   if(auste.getIndex()!=null){
			   AdminScenListDropLetEntity asl = new AdminScenListDropLetEntity();
			   if(auste.getDropletid().equals(SysConstant.DROPLET_ID2)){
				   asl.setDropletid(auste.getDropletid());
				   asl.setDropletconftypeid(auste.getDropletconftypeid());
				   asl.setIndex(auste.getIndex());
				   AdminScenListDropLetEntity scen = adminScenListDropLetService.getScen(asl);
				   if(scen!=null && 
					  scen.getReladropletid()!=null && 
					  scen.getReladropletcontypeid()!=null &&
					  !"".equals(scen.getReladropletid()) &&
					  !"".equals(scen.getReladropletcontypeid())
					 ){
					 auste.setDropletid(scen.getReladropletid());
					 auste.setDropletconftypeid(scen.getReladropletcontypeid());
					 //第一个参数当前页码，第二个参数每页条数
					 PageHelper.startPage(1,10);
					 List<AdminUserSubTaskEntity> data = adminUserSubTaskService.getUserSubTask(auste);
					 for(int i=0;i<data.size();i++){
						 data.get(i).setIfpassname(SysConstant.getCardStudyIfPassName().get(data.get(i).getIfpass()).toString());
					 }
					 
					 Map<String,Object> map = new HashMap<String,Object>();
					 PageInfo<AdminUserSubTaskEntity> page = new PageInfo<>(data);
					 map.put("total",page.getTotal());
					 map.put("rows", data);
					 return objectMapper.writeValueAsString(map);
				   }
			   }
		   }else{
			 //第一个参数当前页码，第二个参数每页条数
			 PageHelper.startPage(1,10);
			 List<AdminUserSubTaskEntity> data = adminUserSubTaskService.getUserSubTask(auste);
			   
			 Map<String,Object> map = new HashMap<String,Object>();
			 PageInfo<AdminUserSubTaskEntity> page = new PageInfo<>(data);
			 map.put("total",page.getTotal());
			 map.put("rows", data);
			 return objectMapper.writeValueAsString(map);
		   }
		return "";
	   }

}
