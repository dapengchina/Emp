package com.ability.emp.admin.dao;


import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.admin.entity.AdminHitCardEntity;
import com.ability.emp.base.BaseDao;

@Mapper
public interface AdminHitCardDao<T> extends BaseDao<T>{
	   
	   int updateByTaskID(AdminHitCardEntity mhce);
}
