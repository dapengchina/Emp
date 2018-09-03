package com.ability.emp.mobile.server.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileDropLetButtonDao;
import com.ability.emp.mobile.entity.MobileDropLetButtonEntity;
import com.ability.emp.mobile.server.MobileDropLetButtonService;



@Service("MobileDropLetButtonService")
public class MobileDropLetButtonServiceImpl implements MobileDropLetButtonService{
	
	
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileDropLetButtonDao mobileDropLetButtonDao;

	@Override
	public MobileDropLetButtonEntity getButtonByID(MobileDropLetButtonEntity me) {
		return mobileDropLetButtonDao.selectButtonByID(me);
	}

}
