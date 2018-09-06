package com.ability.emp.mobile.server.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileChoiceTextDropLetDao;
import com.ability.emp.mobile.entity.MobileChoiceTextDropLetEntity;
import com.ability.emp.mobile.server.MobileChoiceTextDropLetService;



@Service("MobileChoiceTextDropLetService")
public class MobileChoiceTextDropLetServiceImpl implements MobileChoiceTextDropLetService{

	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileChoiceTextDropLetDao mobileChoiceTextDropLetDao;
	
	
	
	@Override
	public MobileChoiceTextDropLetEntity getChoiceDropLetData(MobileChoiceTextDropLetEntity me) {
		return mobileChoiceTextDropLetDao.selectChoiceDropLetData(me);
	}

}
