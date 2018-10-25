package com.ability.emp.admin.action;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
					     data.get(i).setScore(data.get(i).getScore()!=null?data.get(i).getScore():"0");
					 }
					 
					 Map<String,Object> map = new HashMap<String,Object>();
					 PageInfo<AdminUserSubTaskEntity> page = new PageInfo<>(data);
					 map.put("total",page.getTotal());
					 map.put("rows", data);
					 return objectMapper.writeValueAsString(map);
				   }
			   }
		   }else{
			 int currentPoint = 0;
		     double done = 0.0;
			 double total = 0.0;
			 Double score = 0.0;
			 DecimalFormat df = (DecimalFormat)NumberFormat.getInstance();
			 DecimalFormat df1 = (DecimalFormat)NumberFormat.getInstance();
			 //第一个参数当前页码，第二个参数每页条数
			 PageHelper.startPage(1,10);
			 List<AdminUserSubTaskEntity> data = adminUserSubTaskService.getUserSubTask(auste);
			 /**
			  * 处理用户得分
			  */
			 AdminScenListDropLetEntity asl = new AdminScenListDropLetEntity();
			 for(int i=0;i<data.size();i++){
				 if(data.get(i).getDropletid().equals(SysConstant.DROPLET_ID2)){
					 asl.setDropletid(data.get(i).getDropletid());
					 asl.setDropletconftypeid(data.get(i).getDropletconftypeid());
					 asl.setIndex(data.get(i).getIndex());
					 AdminScenListDropLetEntity scen = adminScenListDropLetService.getScen(asl);
					 if(scen!=null && 
							  scen.getReladropletid()!=null && 
							  scen.getReladropletcontypeid()!=null &&
							  !"".equals(scen.getReladropletid()) &&
							  !"".equals(scen.getReladropletcontypeid())
					   ){
						 auste.setDropletid(scen.getReladropletid());
						 auste.setDropletconftypeid(scen.getReladropletcontypeid());
						 List<AdminUserSubTaskEntity> subt = adminUserSubTaskService.getUserSubTask(auste);
						 if(subt!=null && subt.size()>0){
							 total = subt.size();
							 data.get(i).setTotalPoint(String.valueOf(subt.size() * SysConstant.CARD_STAR));
							 for(int j=0;j<subt.size();j++){
									if(subt.get(j).getIfpass().equals(SysConstant.CARD_STUDY_PASS)){
										currentPoint = currentPoint + SysConstant.CARD_STAR;
										done = done + 1;
									}
									if(subt.get(j).getScore()!=null){
										score = score + Double.parseDouble(subt.get(j).getScore());
									}
							  }
							  //可以设置精确几位小数
							  df.setMaximumFractionDigits(0);
							  df1.setMaximumFractionDigits(1);
							  //模式四舍五入
							  df.setRoundingMode(RoundingMode.HALF_UP);
							  data.get(i).setCurrentPoint(String.valueOf(currentPoint));
							  data.get(i).setAverageScore(String.valueOf(df1.format(score/total)));
							  double accuracy_num = done / total;
							  data.get(i).setCompletePercent(Double.parseDouble(df.format(accuracy_num * 100)));
						 }else{
							 data.get(i).setAverageScore("0");
							 data.get(i).setCompletePercent(0);
						 }
					 }else{
						 data.get(i).setAverageScore("0");
						 data.get(i).setCompletePercent(0);
					 }
				 }
			 }  
			 
			 
			 Map<String,Object> map = new HashMap<String,Object>();
			 PageInfo<AdminUserSubTaskEntity> page = new PageInfo<>(data);
			 map.put("total",page.getTotal());
			 map.put("rows", data);
			 return objectMapper.writeValueAsString(map);
		   }
		return "";
	   }

}
