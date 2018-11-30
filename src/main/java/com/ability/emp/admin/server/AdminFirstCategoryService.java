package com.ability.emp.admin.server;

import java.util.List;

import com.ability.emp.admin.entity.AdminFirstCategoryEntity;

public interface AdminFirstCategoryService{
	
	AdminFirstCategoryEntity getIndexData(String id);
	
	List<AdminFirstCategoryEntity> queryCourseType();
}