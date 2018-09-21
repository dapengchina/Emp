package com.ability.emp.mobile.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.base.BaseDao;
import com.ability.emp.mobile.entity.MobileDictationDropLetEntity;


@Mapper
public interface MobileDictationDropLetDao<T> extends BaseDao<T> {
	
	
	
	public MobileDictationDropLetEntity selectDictationData(MobileDictationDropLetEntity me);

}
