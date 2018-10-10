package com.ability.emp.mobile.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.base.BaseDao;
import com.ability.emp.mobile.entity.MobileAdminEntity;


@Mapper
public interface MobileAdminDao<T> extends BaseDao<T> {
	
	
	public List<MobileAdminEntity> selectTutor(MobileAdminEntity me);

}
