package com.ability.emp.admin.action;

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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.admin.entity.AdminSystemParamEntity;
import com.ability.emp.admin.entity.AdminTaskEntity;
import com.ability.emp.admin.entity.AdminThesauresPramEntity;
import com.ability.emp.admin.server.AdminSystemParamService;
import com.ability.emp.admin.server.AdminTaskService;
import com.ability.emp.admin.server.AdminThesauresPramService;
import com.ability.emp.constant.SysConstant;
import com.ability.emp.util.UUIDUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;



/**
 * 任务
 * @author Devin
 * @since 2018-4-3
 *
 */
@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/admin/task")
public class AdminTaskListAction {
	
	
	@Resource
	private AdminTaskService adminTaskService;
	@Resource
	private AdminSystemParamService adminSystemParamService;
	@Resource
	AdminThesauresPramService adminThesauresPramService;
	
	ObjectMapper objectMapper = new ObjectMapper();  
	
	
	/**
	 * 返回首页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("")
	public String listPage(HttpServletRequest request,HttpServletResponse response){
		return "tasklist";
	}
	
	/**
	 * 返回数据
	 * @param request
	 * @param response
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping("/queryAll")
	@ResponseBody
	public String queryAll(int pageSize,int pageNumber,AdminTaskEntity ate) throws JsonProcessingException{
		//第一个参数当前页码，第二个参数每页条数
		PageHelper.startPage(pageNumber,pageSize);  
		List<AdminTaskEntity> data = adminTaskService.queryAll(ate);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		AdminThesauresPramEntity adminThesauresPramEntity=null;
		String tParamid="";
		/**
		 * 日期转换为String类型
		 */
		if(data!=null && data.size()>0){
			for(int i=0;i<data.size();i++){
				data.get(i).setStartStringDate(sf.format(data.get(i).getStartDate()!=null?data.get(i).getStartDate():""));
				data.get(i).setEndStringDate(sf.format(data.get(i).getEndDate()!=null?data.get(i).getEndDate():""));
				tParamid=data.get(i).gettParamid();
				if(!StringUtils.pathEquals("", tParamid)){
					adminThesauresPramEntity=adminThesauresPramService.selectByPrimaryKey(data.get(i).gettParamid());
					data.get(i).settParamid(adminThesauresPramEntity.gettParamName());
				}
			}
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		PageInfo<AdminTaskEntity> page = new PageInfo<>(data);
		map.put("total",page.getTotal());
		map.put("rows", data);
		return objectMapper.writeValueAsString(map);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public String addTask(AdminTaskEntity taskEntity) throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	    
		taskEntity.setStartDate(sf.parse(taskEntity.getStartStringDate()));
		taskEntity.setEndDate(sf.parse(taskEntity.getEndStringDate()));
		taskEntity.setId(UUIDUtil.generateUUID());
		taskEntity.setDel(SysConstant.NO_DEL);
		int i = adminTaskService.insert(taskEntity);  
		if(i>0){
			return "0";
		}else{
			return "1";
		}
	}
	
	@RequestMapping(value = "/taskedit/{id}")
	@ResponseBody
	public String queryTaskById(@PathVariable("id") String id) throws Exception {
		List<AdminTaskEntity> task = adminTaskService.queryTaskById(id);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		if(task!=null && task.size()>0){
			for(int i=0;i<task.size();i++){
				task.get(i).setStartStringDate(sf.format(task.get(i).getStartDate()!=null?task.get(i).getStartDate():""));
				task.get(i).setEndStringDate(sf.format(task.get(i).getEndDate()!=null?task.get(i).getEndDate():""));
				AdminSystemParamEntity aspe = adminSystemParamService.queryById(task.get(i).getParamid());
				if(aspe!=null){
					task.get(i).setParamValue(aspe.getChildValue());
				}
			}
		}
		return objectMapper.writeValueAsString(task.get(0));
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public String updateTask(AdminTaskEntity taskEntity) throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");	
		
		taskEntity.setStartDate(sf.parse(taskEntity.getStartStringDate()));
		taskEntity.setEndDate(sf.parse(taskEntity.getEndStringDate()));
		int i = adminTaskService.update(taskEntity); 
		if(i>0){
			return "0";
		}else{
			return "1";
		}
	}
	
	//学习日历接口
	@RequestMapping("/studyCalendar")
	@ResponseBody
	public String studyCalendar(String id) throws Exception {
		List<AdminTaskEntity> data = adminTaskService.selectStudyCalendar(id);
        Date startDate=data.get(0).getStartDate();
        Date endDate=data.get(0).getEndDate();
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start = sDateFormat.format(startDate);
        String end = sDateFormat.format(endDate);
        System.out.println(start+","+end);
		return start+","+end;
	}
}
