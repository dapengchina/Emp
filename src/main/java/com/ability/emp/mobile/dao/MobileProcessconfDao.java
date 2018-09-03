package com.ability.emp.mobile.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.base.BaseDao;
import com.ability.emp.mobile.entity.MobileProcessconfEntity;


@Mapper
public interface MobileProcessconfDao<T> extends BaseDao<T> {
	
	
	
	public List<MobileProcessconfEntity> selectProcess(MobileProcessconfEntity me);

}
