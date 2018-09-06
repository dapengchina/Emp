package com.ability.emp.mobile.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.base.BaseDao;
import com.ability.emp.mobile.entity.MobileFillBlankAnswerEntity;


@Mapper
public interface MobileFillBlankAnswerDao<T> extends BaseDao<T> {
	
	
	public List<MobileFillBlankAnswerEntity> selectFillBlankAnswer(MobileFillBlankAnswerEntity me);

}
