package com.ability.emp.admin.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.admin.entity.AdminScecategoryEntity;
import com.ability.emp.base.BaseDao;


@Mapper
public interface AdminScecategoryDao<T>  extends BaseDao<T> {
	
	
	public List<AdminScecategoryEntity> selectCourse(AdminScecategoryEntity ase);
	
	public AdminScecategoryEntity selectCourseByID(AdminScecategoryEntity ase);

}
