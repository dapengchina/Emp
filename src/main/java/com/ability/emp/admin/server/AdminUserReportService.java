package com.ability.emp.admin.server;

import java.util.List;

import com.ability.emp.admin.entity.AdminUserEntity;
import com.ability.emp.admin.entity.vo.AdminUserReportVo;

public interface AdminUserReportService {
	
	
	
	
	   List<AdminUserReportVo> queryAll(AdminUserReportVo aue);
	   
	   List<AdminUserReportVo> queryHitCardRecord(AdminUserReportVo aue);
	   
	   

}
