package com.ability.emp.admin.server.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.admin.dao.AdminCourseApplyListDao;
import com.ability.emp.admin.entity.AdminCourseApplyEntity;
import com.ability.emp.admin.server.AdminCourseApplyListService;

@Service("AdminCourseApplyListService")
public class AdminCourseApplyListServiceImpl implements AdminCourseApplyListService{
	
	@SuppressWarnings("rawtypes")
	@Resource
	private AdminCourseApplyListDao adminCourseApplyListDao;

	
	@Override
	public boolean update(AdminCourseApplyEntity record){
		if(adminCourseApplyListDao.updateByPrimaryKeySelective(record)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public AdminCourseApplyEntity selectByPrimaryKey(String id){
		return adminCourseApplyListDao.selectByPrimaryKey(id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AdminCourseApplyEntity> queryAll(AdminCourseApplyEntity record){
		return adminCourseApplyListDao.queryAll(record);
	}


}