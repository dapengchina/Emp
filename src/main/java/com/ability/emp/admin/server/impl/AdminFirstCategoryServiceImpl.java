package com.ability.emp.admin.server.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.admin.dao.AdminFirstCategoryDao;
import com.ability.emp.admin.entity.AdminFirstCategoryEntity;
import com.ability.emp.admin.server.AdminFirstCategoryService;

@Service("AdminFirstCategoryService")
public class AdminFirstCategoryServiceImpl implements AdminFirstCategoryService{

	@SuppressWarnings("rawtypes")
	@Resource
	private AdminFirstCategoryDao adminFirstCategoryDao;
	
	@Override
	public AdminFirstCategoryEntity getIndexData(String id) {
		return adminFirstCategoryDao.getIndexData(id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AdminFirstCategoryEntity> queryCourseType(){
		return adminFirstCategoryDao.queryCourseType();
	};	
	
}