package com.ability.emp.mobile.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.base.BaseDao;
import com.ability.emp.mobile.entity.MobileDropLetConfTypeEntity;

@Mapper
public interface MobileDropLetConfTypeDao<T> extends BaseDao<T> {
	
	
	
	public MobileDropLetConfTypeEntity selectDropLetConfigType(MobileDropLetConfTypeEntity me);

}
