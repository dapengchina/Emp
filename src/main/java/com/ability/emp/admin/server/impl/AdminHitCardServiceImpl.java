package com.ability.emp.admin.server.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.admin.dao.AdminHitCardDao;
import com.ability.emp.admin.entity.AdminHitCardEntity;
import com.ability.emp.admin.server.AdminHitCardService;



@Service("AdminHitCardService")
public class AdminHitCardServiceImpl implements AdminHitCardService{
	
	
	@SuppressWarnings("rawtypes")
	@Resource
	private AdminHitCardDao adminHitCardDao;

	@Override
	public int updateByTaskID(AdminHitCardEntity mhce) {
		return adminHitCardDao.updateByTaskID(mhce);
	}

	@Override
	public int updateByParam(AdminHitCardEntity mhce) {
		return adminHitCardDao.updateByParam(mhce);
	}

	
}
