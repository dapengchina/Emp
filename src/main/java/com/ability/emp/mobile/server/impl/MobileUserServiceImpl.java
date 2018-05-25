package com.ability.emp.mobile.server.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileUserDao;
import com.ability.emp.mobile.entity.MobileUserEntity;
import com.ability.emp.mobile.server.MobileUserService;

@Service("MobileUserService") 
public class MobileUserServiceImpl implements MobileUserService{
	
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileUserDao mobileUserDao;

	@Override
	public MobileUserEntity queryById(String id) {
		return (MobileUserEntity) mobileUserDao.queryById(id);
	}

	@Override
	public MobileUserEntity login2(MobileUserEntity mue) {
		return mobileUserDao.login2(mue);
	}
	
	
	
}
