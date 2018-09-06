package com.ability.emp.mobile.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.base.BaseDao;
import com.ability.emp.mobile.entity.MobileFillBlankQuestionEntity;


@Mapper
public interface MobileFillBlankQuestionDao<T> extends BaseDao<T> {
	
	
	public List<MobileFillBlankQuestionEntity> selectFillBlankQuestion(MobileFillBlankQuestionEntity me);

}
