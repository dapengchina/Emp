package com.ability.emp.mobile.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.base.BaseDao;
import com.ability.emp.mobile.entity.MobileDropLetButtonEntity;


@Mapper
public interface MobileDropLetButtonDao<T> extends BaseDao<T> {
	
	
	public MobileDropLetButtonEntity selectButtonByID(MobileDropLetButtonEntity me);

}
