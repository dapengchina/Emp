package com.ability.emp.mobile.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.base.BaseDao;
import com.ability.emp.mobile.entity.MobileHitCardEntity;

@Mapper
public interface MobileHitCardDao<T> extends BaseDao<T>{
       
	   int count(String id);
	   
	   List<MobileHitCardEntity> queryByUserID(MobileHitCardEntity mhce);
}
