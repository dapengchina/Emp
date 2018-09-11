package com.ability.emp.mobile.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.base.BaseDao;
import com.ability.emp.mobile.entity.MobileReadSpeakDropLetEntity;



@Mapper
public interface MobileReadSpeakDropLetDao<T> extends BaseDao<T> {
	
	
	
	
	public MobileReadSpeakDropLetEntity selectReadSpeakData(MobileReadSpeakDropLetEntity me);

}
