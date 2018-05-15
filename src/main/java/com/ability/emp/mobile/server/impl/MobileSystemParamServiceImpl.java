package com.ability.emp.mobile.server.impl;



import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileSystemParamDao;
import com.ability.emp.mobile.entity.MobileSystemParamEntity;
import com.ability.emp.mobile.server.MobileSystemParamService;

@Service("MobileSystemParamService")
public class MobileSystemParamServiceImpl implements MobileSystemParamService {
	
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileSystemParamDao mobileSystemParamDao;

	@Override
	public MobileSystemParamEntity queryById(String id) {
		return (MobileSystemParamEntity) mobileSystemParamDao.queryById(id);
	}
	
	
}
