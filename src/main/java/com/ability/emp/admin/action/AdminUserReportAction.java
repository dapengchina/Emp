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
import com.ability.emp.util.CalendarCountUtil;
import com.ability.emp.util.CalendarUtil;
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
	
	
	private List<AdminUserReportVo> dataHandle(List<AdminUserReportVo> data,String startDate,String endDate) throws Exception{
		startDate = "2018-06-30";
		endDate = "2018-07-01";
		String[] start = startDate.split("-");
		String[] end = endDate.split("-");
		
		/**
		 * 判断所选日期是否跨月
		 */
		if(Integer.parseInt(start[1])==Integer.parseInt(end[1])){
			//没有跨月
			return notMonthCross(data,startDate,endDate);
		}else{
			//跨月
			return monthCross(data,startDate,endDate);
		}
		
	}
	
	
	/**
	 * 所选日期跨月-数据处理
	 * @throws Exception 
	 */
	private List<AdminUserReportVo> monthCross(List<AdminUserReportVo> data,String startDate,String endDate) throws Exception{
		startDate = "2018-06-30";
		endDate = "2018-07-01";
		String[] start = startDate.split("-");
		String[] end = endDate.split("-");
		
		//获取2个日期相差的天数
		int x = CalendarCountUtil.getDays(startDate, endDate);
		//获取月份天数
		int mds = CalendarUtil.daysOfmonthInyear(Integer.parseInt(start[1]), 2018);
		int stday = Integer.parseInt(start[2]);//开始日期日数据
		int enday = Integer.parseInt(end[2]);//结束日期日数据
		int stmonth = Integer.parseInt(start[1]);//开始日期月数据
		int enmonth = Integer.parseInt(end[1]);//结束日期月数据
		int temp=1;
		String y="";
		String d="";
		int stmonth_temp=stmonth;
		
		
		List<AdminUserReportVo> list = new ArrayList<AdminUserReportVo>();
		AdminUserReportVo v1 = new AdminUserReportVo();
		String sdate = startDate;
		for(int m=0;m<data.size();m++){
			/**
			 * 当进行下一个用户的循环时重置相关数据
			 */
			if(m>0){
				stmonth_temp = stmonth;
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
				if(i==0){
					//判断是否为某月最后一天
					if(Integer.parseInt(start[2])==mds){
						//是最后一天
						stmonth_temp++;
						if(stmonth_temp<10 && stmonth_temp<=enmonth){
							y="0"+stmonth_temp;
						}
						if(stmonth_temp>10 && stmonth_temp<=enmonth){
							y=String.valueOf(stmonth_temp);
						}
						sdate="2018-"+y+"-"+"0"+temp;
					}
				}
				
				if(stmonth_temp==stmonth+1){
					if(temp<10 && temp<enday){
						
					}
				}
				
			}
		}
		return list;
	}
	
	/**
	 * 所选日期没有跨月-数据处理
	 */
    private List<AdminUserReportVo> notMonthCross(List<AdminUserReportVo> data,String startDate,String endDate){
    	startDate = "2018-06-08";
		endDate = "2018-06-10";
		String[] start = startDate.split("-");
		String[] end = endDate.split("-");
	
		int st = Integer.parseInt(start[2]);//开始日期日数据
		int en = Integer.parseInt(end[2]);//结束日期日数据
		
		int x = en - st;//相差天数
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
