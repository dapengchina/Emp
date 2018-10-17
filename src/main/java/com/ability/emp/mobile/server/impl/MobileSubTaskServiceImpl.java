package com.ability.emp.mobile.server.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileSubTaskDao;
import com.ability.emp.mobile.entity.MobileSubTaskEntity;
import com.ability.emp.mobile.server.MobileSubTaskService;


@Service("MobileSubTaskService")
public class MobileSubTaskServiceImpl implements MobileSubTaskService{
	
	
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileSubTaskDao mobileSubTaskDao;

	@SuppressWarnings("unchecked")
	@Override
	public int saveScore(MobileSubTaskEntity mste) {
		return mobileSubTaskDao.update(mste);
	}

	@Override
	public MobileSubTaskEntity getSubTask(MobileSubTaskEntity mste) {
		return mobileSubTaskDao.selectSubTask(mste);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MobileSubTaskEntity> getSubTaskList(MobileSubTaskEntity mste) {
		return mobileSubTaskDao.selectSubTaskList(mste);
	}

}
