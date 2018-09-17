package com.ability.emp.mobile.server.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileUserTaskDao;
import com.ability.emp.mobile.entity.MobileUserTaskEntity;
import com.ability.emp.mobile.server.MobileUserTaskService;


@Service("MobileUserTaskService")
public class MobileUserTaskServiceImpl implements MobileUserTaskService{
	
	
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileUserTaskDao mobileUserTaskDao;

	@SuppressWarnings("unchecked")
	@Override
	public int addUserTask(MobileUserTaskEntity me) {
		return mobileUserTaskDao.insert(me);
	}

}
