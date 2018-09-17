package com.ability.emp.mobile.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.base.BaseDao;
import com.ability.emp.mobile.entity.MobileMatchDropLetEntity;



@Mapper
public interface MobileMatchDropLetDao<T> extends BaseDao<T> {
	
	
	
	public MobileMatchDropLetEntity selectMatchData(MobileMatchDropLetEntity me);

}
