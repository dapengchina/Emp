package com.ability.emp.mobile.server.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileHitCardDao;
import com.ability.emp.mobile.entity.MobileHitCardEntity;
import com.ability.emp.mobile.server.MobileStudyCalendarService;

@Service("MobileStudyCalendarService")
public class MobileStudyCalendarServiceImpl implements MobileStudyCalendarService{

	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileHitCardDao mobileHitCardDao;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MobileHitCardEntity> studyCalendar(MobileHitCardEntity mhce) {
		return mobileHitCardDao.studyCalendar(mhce);
	}

}
