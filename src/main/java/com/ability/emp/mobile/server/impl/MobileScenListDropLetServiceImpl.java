package com.ability.emp.mobile.server.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileScenListDropLetDao;
import com.ability.emp.mobile.entity.MobileScenListDropLetEntity;
import com.ability.emp.mobile.server.MobileScenListDropLetService;



@Service("MobileScenListDropLetService")
public class MobileScenListDropLetServiceImpl implements MobileScenListDropLetService{

	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileScenListDropLetDao mobileScenListDropLetDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MobileScenListDropLetEntity> getScenListDropletData(MobileScenListDropLetEntity me) {
		return mobileScenListDropLetDao.selectScenList(me);
	}
	
	
	

}
