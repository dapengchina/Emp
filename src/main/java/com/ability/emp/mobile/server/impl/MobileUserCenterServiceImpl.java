package com.ability.emp.mobile.server.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileUserCenterDao;
import com.ability.emp.mobile.entity.MobileUserCenterEntity;
import com.ability.emp.mobile.server.MobileUserCenterService;


@Service("MobileUserCenterService") 
public class MobileUserCenterServiceImpl implements MobileUserCenterService{
	
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileUserCenterDao mobileUserCenterDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<MobileUserCenterEntity> getUserCenterList(MobileUserCenterEntity muce) {
		return mobileUserCenterDao.selectUserCenterList(muce);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MobileUserCenterEntity> getGroupList() {
		return mobileUserCenterDao.selectGroupList();
	}

}
