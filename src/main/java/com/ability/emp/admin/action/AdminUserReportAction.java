package com.ability.emp.admin.action;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.admin.entity.AdminUserEntity;
import com.ability.emp.admin.entity.vo.AdminUserReportVo;
import com.ability.emp.admin.server.AdminUserReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;


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
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping("/queryAll")
	@ResponseBody
	public String queryAll(int pageSize,int pageNumber,AdminUserReportVo adminUserReportVo) throws Exception {
		//第一个参数当前页码，第二个参数每页条数
		PageHelper.startPage(pageNumber,pageSize);  
		List<AdminUserReportVo> data = adminUserReportService.getUser(adminUserReportVo);
		Map<String,Object> map = new HashMap<String,Object>();
		List handleAfter = dataHandle(data,"","");
		//PageInfo<AdminUserEntity> page = new PageInfo(data);
		map.put("total",handleAfter.size());
		map.put("rows", handleAfter);
		
		return objectMapper.writeValueAsString(map);
	}
	
	
	private List<AdminUserReportVo> dataHandle(List<AdminUserReportVo> data,String startDate,String endDate){
		startDate = "2018-06-08";
		endDate = "2018-06-10";
		String[] start = startDate.split("-");
		String[] end = endDate.split("-");
	
		int st = Integer.parseInt(start[2]);
		int en = Integer.parseInt(end[2]);
		int x = en - st;
		String y = "";
		
		List<AdminUserReportVo> list = new ArrayList<AdminUserReportVo>();
		AdminUserReportVo v1 = new AdminUserReportVo();
		String sdate = startDate;
		for(int m=0;m<data.size();m++){
			/**
			 * 当进行下一个用户的循环时重置相关数据
			 */
			if(m>0){
				st = Integer.parseInt(start[2]);
				y = "";
				sdate = startDate;
			}
			
			/**
			 * 每个用户循环起止日期内的所有日期
			 */
			for(int i=0;i<x+1;i++){
				v1.setId(data.get(m).getId());
				v1.setStringDate(sdate);
				List<AdminUserReportVo> lst = adminUserReportService.getHitCardRecord(v1);
				//用户没有打卡
				if(lst==null || lst.size()==0){
					AdminUserReportVo aurv = new AdminUserReportVo();
					aurv.setStringDate(sdate+"---未打卡");
					aurv.setId(data.get(m).getId());
					aurv.setNickName(data.get(m).getNickName());
					aurv.setPhone(data.get(m).getPhone());
					aurv.setUserName(data.get(m).getUserName());
					aurv.setTutor(data.get(m).getTutor());
					list.add(aurv);
				}
				
				st++;
				if(st<10 && st<en){
					y="0"+st;
				}
				if(st>10 && st< en){
					y=String.valueOf(st);
				}
				if(st==en){
					sdate=endDate;
				}else{
					sdate="2018-06"+"-"+y;
				}
				
			}
		}
		return list;
	}
	
	
	
	
}
