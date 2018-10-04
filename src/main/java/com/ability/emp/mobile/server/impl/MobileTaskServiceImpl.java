package com.ability.emp.mobile.server.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileTaskDao;
import com.ability.emp.mobile.entity.MobileTaskEntity;
import com.ability.emp.mobile.server.MobileTaskService;

import java.util.List;

@Service("MobileTaskService") 
public class MobileTaskServiceImpl implements MobileTaskService{
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileTaskDao mobileTaskDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<MobileTaskEntity> selectStudyCalendar(String id) {
		return mobileTaskDao.selectStudyCalendar(id);
	}
	
	@Override
	public MobileTaskEntity queryById(String id) {
		return (MobileTaskEntity) mobileTaskDao.queryById(id);
	}

	@Override
	public MobileTaskEntity getTask(MobileTaskEntity mte) {
		return mobileTaskDao.selectTask(mte);
	}

}
