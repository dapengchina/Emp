package com.ability.emp.mobile.server.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileScenarioDropLetDao;
import com.ability.emp.mobile.entity.MobileScenarioDropLetEntity;
import com.ability.emp.mobile.server.MobileScenarioDropLetService;


@Service("MobileScenarioDropLetService")
public class MobileScenarioDropLetServiceImpl implements MobileScenarioDropLetService{
	
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileScenarioDropLetDao mobileScenarioDropLetDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<MobileScenarioDropLetEntity> getScenarioData(MobileScenarioDropLetEntity me) {
		return mobileScenarioDropLetDao.selectScenarioData(me);
	}

}
