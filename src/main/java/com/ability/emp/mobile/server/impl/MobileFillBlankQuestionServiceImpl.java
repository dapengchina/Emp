package com.ability.emp.mobile.server.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileFillBlankQuestionDao;
import com.ability.emp.mobile.entity.MobileFillBlankQuestionEntity;
import com.ability.emp.mobile.server.MobileFillBlankQuestionService;



@Service("MobileFillBlankQuestionService")
public class MobileFillBlankQuestionServiceImpl implements MobileFillBlankQuestionService{

	
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileFillBlankQuestionDao mobileFillBlankQuestionDao;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MobileFillBlankQuestionEntity> getFillBlankQuestion(MobileFillBlankQuestionEntity me) {
		return mobileFillBlankQuestionDao.selectFillBlankQuestion(me);
	}

}
