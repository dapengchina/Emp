package com.ability.emp.mobile.server.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileMatchDropLetDao;
import com.ability.emp.mobile.entity.MobileMatchDropLetEntity;
import com.ability.emp.mobile.server.MobileMatchDropLetService;


@Service("MobileMatchDropLetService")
public class MobileMatchDropLetServiceImpl implements MobileMatchDropLetService{
	
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileMatchDropLetDao mobileMatchDropLetDao;

	@Override
	public MobileMatchDropLetEntity getMatchData(MobileMatchDropLetEntity me) {
		return mobileMatchDropLetDao.selectMatchData(me);
	}

}
