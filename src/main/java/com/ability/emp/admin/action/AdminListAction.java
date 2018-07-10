package com.ability.emp.admin.action;

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

import com.ability.emp.admin.entity.AdminEntity;
import com.ability.emp.admin.server.AdminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 管理员列表
 * @author Devin
 * @since 2018-3-31
 *
 */
@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/admin/adminuser")
public class AdminListAction {
	
	
	@Resource
	private AdminService adminService;
	
	ObjectMapper objectMapper = new ObjectMapper();  
	
	
	/**
	 * 返回首页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("")
	public String listPage(HttpServletRequest request,HttpServletResponse response){
		return "adminlist";
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
	public String queryAll(int pageSize,int pageNumber, AdminEntity adminEntity) throws JsonProcessingException{
		//第一个参数当前页码，第二个参数每页条数
		PageHelper.startPage(pageNumber,pageSize);
		List<AdminEntity> data = adminService.queryAll(adminEntity);
		/**
		 * 汉字转换
		 */
		for(int i=0;i<data.size();i++){
			//已启用
			if(data.get(i).getStatus().equals("0")){
				data.get(i).setStatusName("已启用");
			}
			//已禁用
            if(data.get(i).getStatus().equals("1")){
            	data.get(i).setStatusName("已禁用");
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		PageInfo<AdminEntity> page = new PageInfo<>(data);
		map.put("total",page.getTotal());
		map.put("rows", data);
		return objectMapper.writeValueAsString(map);
	}
	@RequestMapping(value="/verifieName")
	@ResponseBody
	public String verifieName(String AdminName) throws JsonProcessingException {
		boolean result;
		Integer verify = adminService.verifieName(AdminName);
		if (verify == 0) {
			result = true;
		}else {
			result = false;
		}
		Map<String, Boolean> map = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		map.put("valid", result);
		return mapper.writeValueAsString(map);
	}
	
	@RequestMapping(value="/addAdmin")
	@ResponseBody
	public Integer addAdmin(String adminName, String adminPwd) {
		return adminService.insert(adminName, adminPwd);
	}
	
	@RequestMapping(value="/update")
	@ResponseBody
	public Integer update(String adminId, String adminStatus) {
		AdminEntity admin = new AdminEntity();
		admin.setId(adminId);
		admin.setStatus(adminStatus);
		return adminService.update(admin);
	}
	
	@RequestMapping("/getTutor")
	@ResponseBody
	public String getTutor(AdminEntity adminEntity) throws JsonProcessingException{
		List<AdminEntity> data = adminService.queryAll(adminEntity);
		return objectMapper.writeValueAsString(data);
	}
}
