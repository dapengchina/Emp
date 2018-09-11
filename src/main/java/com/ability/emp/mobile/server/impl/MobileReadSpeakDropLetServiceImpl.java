package com.ability.emp.mobile.server.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileReadSpeakDropLetDao;
import com.ability.emp.mobile.entity.MobileReadSpeakDropLetEntity;
import com.ability.emp.mobile.server.MobileReadSpeakDropLetService;



@Service("MobileReadSpeakDropLetService")
public class MobileReadSpeakDropLetServiceImpl implements MobileReadSpeakDropLetService{

	
	
	  @SuppressWarnings("rawtypes")
	  @Resource
	  private MobileReadSpeakDropLetDao mobileReadSpeakDropLetDao;

	  @Override
	  public MobileReadSpeakDropLetEntity getReadSpeakData(MobileReadSpeakDropLetEntity me) {
		return mobileReadSpeakDropLetDao.selectReadSpeakData(me);
	  }
	
}
