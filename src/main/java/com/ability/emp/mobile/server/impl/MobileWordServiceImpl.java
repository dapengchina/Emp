package com.ability.emp.mobile.server.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileWordDao;
import com.ability.emp.mobile.entity.MobileWordEntity;
import com.ability.emp.mobile.server.MobileWordService;

@Service("MobileWordService")
public class MobileWordServiceImpl implements MobileWordService {

	@SuppressWarnings("rawtypes")
	@Resource
	private MobileWordDao mobileWordDao;

	@Override
	public MobileWordEntity mean(String id) {
		return mobileWordDao.mean(id);
	}

	@Override
	public MobileWordEntity queryWordById(String id) {
		return (MobileWordEntity) mobileWordDao.queryWordById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MobileWordEntity> queryWordAll(MobileWordEntity entity) {
		return mobileWordDao.queryWordAll(entity);
	}

	
}
