package com.ability.emp.mobile.dao;

import org.apache.ibatis.annotations.Mapper;
import com.ability.emp.base.BaseDao;
import com.ability.emp.mobile.entity.MobileUserEntity;

@Mapper
public interface MobileUserDao<T> extends BaseDao<T>{

	MobileUserEntity login2(MobileUserEntity mue);
	
	MobileUserEntity selectUser(MobileUserEntity mue);
}