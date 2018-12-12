package com.ability.emp.admin.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.constant.SysConstant;
import com.ability.emp.util.UUIDUtil;
import com.ability.emp.admin.entity.AdminCourseApplyEntity;
import com.ability.emp.admin.entity.AdminFirstCategoryEntity;
import com.ability.emp.admin.entity.AdminUserEntity;
import com.ability.emp.admin.entity.AdminScecategoryEntity;
import com.ability.emp.admin.server.AdminCourseApplyListService;
import com.ability.emp.admin.server.AdminFirstCategoryService;
import com.ability.emp.admin.server.AdminUserService;
import com.ability.emp.admin.server.AdminScecategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@CrossOrigin // 解决跨域请求
@Controller
@RequestMapping("/admin/courseApply")
public class AdminCourseApplyAction{
	
	@Resource
	private AdminCourseApplyListService adminCourseApplyListService;
	
	@Resource
	private AdminUserService adminUserService;
	
	@Resource
	private AdminFirstCategoryService adminFirstCategoryService;
	
	@Resource
	private AdminScecategoryService adminScecategoryService;
	
	ObjectMapper objectMapper = new ObjectMapper();  
	
	@RequestMapping("")
	public String listPage(HttpServletRequest request,HttpServletResponse response){
		return "courseApply";
	}
	
	
	@RequestMapping("/queryCourseType")
	@ResponseBody
	public String queryCourseType(final HttpServletRequest request,
            final HttpServletResponse response)throws JsonProcessingException, UnsupportedEncodingException{
		
		List<AdminFirstCategoryEntity> courseTypes = adminFirstCategoryService.queryCourseType();
		
		return objectMapper.writeValueAsString(courseTypes);
	}
	
	@RequestMapping("/queryAll")
	@ResponseBody
	public String queryAll(int pageNumber, int pageSize, AdminCourseApplyEntity ase, final HttpServletRequest request,
            final HttpServletResponse response)throws JsonProcessingException, UnsupportedEncodingException, ParseException{
		//第一个参数当前页码，第二个参数每页条数
		PageHelper.startPage(pageNumber,pageSize); 
		List<AdminCourseApplyEntity> data = adminCourseApplyListService.queryAll(ase);
		for(int i=0; i<data.size(); i++){
			AdminUserEntity user = adminUserService.queryUserById(data.get(i).getApplyuserid());
			data.get(i).setApplyUserName(user.getUserName());
			data.get(i).setCoursestateName(SysConstant.getCourseStateMap().get(data.get(i).getCoursestate()).toString());
			AdminFirstCategoryEntity courseTypeInfo =  adminFirstCategoryService.getIndexData(data.get(i).getCoursefirstleveltype());
			data.get(i).setCourseFirstTypeName(courseTypeInfo.getFircatname());
		}
		Map<String,Object> map = new HashMap<String,Object>();
		@SuppressWarnings({ "unchecked", "rawtypes" })
		PageInfo<AdminUserEntity> page = new PageInfo(data);
		map.put("total",page.getTotal());
		map.put("rows", data);
		return objectMapper.writeValueAsString(map);
	}
	
	@SuppressWarnings("null")
	@RequestMapping("/update")
	@ResponseBody
	public boolean update(AdminCourseApplyEntity ase,final HttpServletRequest request,
	        final HttpServletResponse response){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp approvedate =null;
		try {
			approvedate = new Timestamp(sf.parse(sf.format(new Date())).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ase.setApprovedate(approvedate);
		boolean state = adminCourseApplyListService.update(ase);
		if(state){
			if(ase.getApprovestate().equals("1")){
				AdminCourseApplyEntity courseInfo = adminCourseApplyListService.selectByPrimaryKey(ase.getId());
				AdminScecategoryEntity course = new AdminScecategoryEntity();
				String firstLevel = courseInfo.getCoursefirstleveltype();
				int max = 1;
				List<AdminScecategoryEntity> indexList = adminScecategoryService.getIndexList(firstLevel);
				for(int i=0; i<indexList.size(); i++){
					if(indexList.get(i).getIndex() > max){
						max = indexList.get(i).getIndex();
					}
				}
				max++;
				course.setId(UUIDUtil.generateUUID());
				course.setFircatid(firstLevel);
				course.setScecatname(courseInfo.getCoursename());
				course.setCoursestate(courseInfo.getCoursestate());
				course.setIndex(max);
				course.setCoursetype("1");
				return adminScecategoryService.insert(course);
			}else{
				return true;
			}
			
		}else{
			return false;
		}
	}
}