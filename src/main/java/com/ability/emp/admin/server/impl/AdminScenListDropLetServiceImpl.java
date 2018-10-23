package com.ability.emp.admin.server.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.admin.dao.AdminScenListDropLetDao;
import com.ability.emp.admin.entity.AdminScenListDropLetEntity;
import com.ability.emp.admin.server.AdminScenListDropLetService;




@Service("AdminScenListDropLetService")
public class AdminScenListDropLetServiceImpl implements AdminScenListDropLetService{

	
	@SuppressWarnings("rawtypes")
	@Resource
	private AdminScenListDropLetDao adminScenListDropLetDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AdminScenListDropLetEntity> getScenListDropletData(AdminScenListDropLetEntity me) {
		return adminScenListDropLetDao.selectScenList(me);
	}

	@Override
	public AdminScenListDropLetEntity getScen(AdminScenListDropLetEntity me) {
		return adminScenListDropLetDao.selectScen(me);
	}
	
	
	

}
