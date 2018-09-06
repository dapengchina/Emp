package com.ability.emp.mobile.server.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileFillBlankDropLetDao;
import com.ability.emp.mobile.entity.MobileFillBlankDropLetEntity;
import com.ability.emp.mobile.server.MobileFillBlankDropLetService;


@Service("MobileFillBlankDropLetService")
public class MobileFillBlankDropLetServiceImpl implements MobileFillBlankDropLetService{

	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileFillBlankDropLetDao mobileFillBlankDropLetDao;
	
	
	@Override
	public MobileFillBlankDropLetEntity getFillBlankData(MobileFillBlankDropLetEntity me) {
		return mobileFillBlankDropLetDao.selectFillBlankData(me);
	}

}
