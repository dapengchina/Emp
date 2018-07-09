package com.ability.emp.admin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.admin.entity.AdminUserEntity;
import com.ability.emp.admin.entity.vo.AdminUserReportVo;
import com.ability.emp.admin.server.AdminUserReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/admin/userreport")
public class AdminUserReportAction {
	
	
	
	@Resource
	private AdminUserReportService adminUserReportService;
	
	ObjectMapper objectMapper = new ObjectMapper();  
	
	
	/**
	 * 返回首页
	 * @param userEntity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("")
	public String listPage(AdminUserEntity userEntity) throws Exception {
		return "userreportlist";
	}
	
	
	/**
	 * 返回数据
	 * @param userEntity
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryAll")
	@ResponseBody
	public String queryAll(int pageSize,int pageNumber,AdminUserReportVo adminUserReportVo) throws Exception {
		//第一个参数当前页码，第二个参数每页条数
		PageHelper.startPage(pageNumber,pageSize);  
		List<AdminUserReportVo> data = adminUserReportService.queryAll(adminUserReportVo);
		Map<String,Object> map = new HashMap<String,Object>();
		@SuppressWarnings("unchecked")
		PageInfo<AdminUserEntity> page = new PageInfo(data);
		map.put("total",page.getTotal());
		map.put("rows", data);
		return objectMapper.writeValueAsString(map);
	}
	
	
	/**
	 * 返回用户打卡记录
	 * @param userEntity
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryHitCardRecord")
	@ResponseBody
	public String queryHitCardRecord(String id,Integer pageSize,Integer pageNumber,AdminUserReportVo adminUserReportVo) throws Exception {
		adminUserReportVo.setId(id);
		//第一个参数当前页码，第二个参数每页条数
		//PageHelper.startPage(pageNumber,pageSize);  
		List<AdminUserReportVo> data = adminUserReportService.queryHitCardRecord(adminUserReportVo);
		Map<String,Object> map = new HashMap<String,Object>();
		@SuppressWarnings("unchecked")
		PageInfo<AdminUserEntity> page = new PageInfo(data);
		map.put("total",page.getTotal());
		map.put("rows", data);
		return objectMapper.writeValueAsString(map);
	}

}
