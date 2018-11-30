package com.ability.emp.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.admin.entity.AdminFirstCategoryEntity;
import com.ability.emp.base.BaseDao;


@Mapper
public interface AdminFirstCategoryDao<T> extends BaseDao<T> {
	
	AdminFirstCategoryEntity getIndexData(String id);
	
	List<AdminFirstCategoryEntity> queryCourseType();

}
