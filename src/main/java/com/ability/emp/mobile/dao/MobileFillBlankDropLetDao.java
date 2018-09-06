package com.ability.emp.mobile.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.base.BaseDao;
import com.ability.emp.mobile.entity.MobileFillBlankDropLetEntity;


@Mapper
public interface MobileFillBlankDropLetDao<T> extends BaseDao<T> {
	
	
	public MobileFillBlankDropLetEntity selectFillBlankData(MobileFillBlankDropLetEntity me);

}
