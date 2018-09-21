package com.ability.emp.mobile.server.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileDictationDropLetDao;
import com.ability.emp.mobile.entity.MobileDictationDropLetEntity;
import com.ability.emp.mobile.server.MobileDictationDropLetService;



@Service("MobileDictationDropLetService")
public class MobileDictationDropLetServiceImpl implements MobileDictationDropLetService{

	
	
	@SuppressWarnings("rawtypes")
	@Resource 
	private MobileDictationDropLetDao mobileDictationDropLetDao;

	@Override
	public MobileDictationDropLetEntity getDictationData(MobileDictationDropLetEntity me) {
		return mobileDictationDropLetDao.selectDictationData(me);
	}
}
