package com.ability.emp.mobile.dao;

import com.ability.emp.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MobileWordDao <T> extends BaseDao<T> {
	
	String selectIption(String id);
	
}
