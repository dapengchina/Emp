package com.ability.emp.mobile.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.base.BaseDao;
import com.ability.emp.mobile.entity.MobileUserCenterEntity;


@Mapper
public interface MobileUserCenterDao<T> extends BaseDao<T> {
	
	
	public List<MobileUserCenterEntity> selectUserCenterList(MobileUserCenterEntity muce);
	
	public List<MobileUserCenterEntity> selectGroupList();

}
