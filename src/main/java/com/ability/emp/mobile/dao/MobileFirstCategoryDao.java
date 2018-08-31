package com.ability.emp.mobile.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.base.BaseDao;
import com.ability.emp.mobile.entity.MobileFirstCategoryEntity;


@Mapper
public interface MobileFirstCategoryDao<T> extends BaseDao<T> {
	
	
	
	   public List<MobileFirstCategoryEntity> getIndexData(MobileFirstCategoryEntity me);

}
