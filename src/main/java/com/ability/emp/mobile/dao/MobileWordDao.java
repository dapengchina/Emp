package com.ability.emp.mobile.dao;

import com.ability.emp.base.BaseDao;
import com.ability.emp.mobile.entity.MobileWordEntity;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MobileWordDao <T> extends BaseDao<T> {
	
	
	   MobileWordEntity mean(String id);
}
