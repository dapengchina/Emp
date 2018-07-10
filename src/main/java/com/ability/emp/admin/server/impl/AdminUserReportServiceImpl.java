package com.ability.emp.admin.server.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.admin.dao.AdminUserReportDao;
import com.ability.emp.admin.entity.vo.AdminUserReportVo;
import com.ability.emp.admin.server.AdminUserReportService;


@Service("AdminUserReportService")
public class AdminUserReportServiceImpl implements AdminUserReportService{
	
	
	@SuppressWarnings("rawtypes")
	@Resource
	private AdminUserReportDao adminUserReportDao;


	@SuppressWarnings("unchecked")
	@Override
	public List<AdminUserReportVo> getHitCardRecord(AdminUserReportVo aue) {
		return adminUserReportDao.getHitCardRecord(aue);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminUserReportVo> getUser(AdminUserReportVo aue) {
		return adminUserReportDao.getUser(aue);
	}

}
