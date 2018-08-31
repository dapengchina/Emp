package com.ability.emp.mobile.server.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileDropLetDao;
import com.ability.emp.mobile.entity.MobileDropLetEntity;
import com.ability.emp.mobile.server.MobileDropLetService;


@Service("MobileDropLetService")
public class MobileDropLetServiceImpl implements MobileDropLetService{
	
	
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileDropLetDao mobileDropLetDao;

	@Override
	public MobileDropLetEntity getDropLetByID(MobileDropLetEntity me) {
		return mobileDropLetDao.selectByID(me);
	}

}
