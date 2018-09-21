package com.ability.emp.mobile.server.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileVideoDropLetDao;
import com.ability.emp.mobile.entity.MobileVideoDropLetEntity;
import com.ability.emp.mobile.server.MobileVideoDropLetServer;



@Service("MobileVideoDropLetServer")
public class MobileVideoDropLetServerImpl implements MobileVideoDropLetServer{
	
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileVideoDropLetDao mobileVideoDropLetDao;

	@Override
	public MobileVideoDropLetEntity getVideoData(MobileVideoDropLetEntity me) {
		return mobileVideoDropLetDao.selectVideoData(me);
	}

}
