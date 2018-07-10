package com.ability.emp.admin.server;

import java.util.List;

import com.ability.emp.admin.entity.vo.AdminUserReportVo;

public interface AdminUserReportService {
	
	   
	   List<AdminUserReportVo> getHitCardRecord(AdminUserReportVo aue);
	   
	   List<AdminUserReportVo> getUser(AdminUserReportVo aue);
	   
	   

}
