package com.ability.emp.mobile.server.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileFillBlankAnswerDao;
import com.ability.emp.mobile.entity.MobileFillBlankAnswerEntity;
import com.ability.emp.mobile.server.MobileFillBlankAnswerService;



@Service("MobileFillBlankAnswerService")
public class MobileFillBlankAnswerServiceImpl implements MobileFillBlankAnswerService{

	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileFillBlankAnswerDao mobileFillBlankAnswerDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MobileFillBlankAnswerEntity> getFillBlankAnswer(MobileFillBlankAnswerEntity me) {
		return mobileFillBlankAnswerDao.selectFillBlankAnswer(me);
	}

}
