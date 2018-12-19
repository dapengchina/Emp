package com.ability.emp.mobile.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.base.BaseDao;
import com.ability.emp.mobile.entity.MobileHomeAnnounceEntity;

@Mapper
public interface MobileHomeAnnounceDao<T> extends BaseDao<T>{
	
	MobileHomeAnnounceEntity selectByPrimaryKey(String id);
	
	List<MobileHomeAnnounceEntity> selectList(MobileHomeAnnounceEntity ase);
}