package com.ability.emp.mobile.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.base.BaseDao;
import com.ability.emp.mobile.entity.MobileVideoDropLetEntity;



@Mapper
public interface MobileVideoDropLetDao<T> extends BaseDao<T> {
	
	
	
	public MobileVideoDropLetEntity selectVideoData(MobileVideoDropLetEntity me);

}
