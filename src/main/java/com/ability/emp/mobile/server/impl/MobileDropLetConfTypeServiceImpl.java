package com.ability.emp.mobile.server.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileDropLetConfTypeDao;
import com.ability.emp.mobile.entity.MobileDropLetConfTypeEntity;
import com.ability.emp.mobile.server.MobileDropLetConfTypeService;


@Service("MobileDropLetConfTypeService")
public class MobileDropLetConfTypeServiceImpl implements MobileDropLetConfTypeService{

	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileDropLetConfTypeDao mobileDropLetConfTypeDao;
	
	
	@Override
	public MobileDropLetConfTypeEntity getDropLetConfigType(MobileDropLetConfTypeEntity me) {
		return mobileDropLetConfTypeDao.selectDropLetConfigType(me);
	}

}
