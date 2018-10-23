package com.ability.emp.admin.server.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.admin.dao.AdminUserSubTaskDao;
import com.ability.emp.admin.entity.AdminUserSubTaskEntity;
import com.ability.emp.admin.server.AdminUserSubTaskService;


@Service("AdminUserSubTaskService")
public class AdminUserSubTaskServiceImpl implements AdminUserSubTaskService{
	
	
	@SuppressWarnings("rawtypes")
	@Resource
	private AdminUserSubTaskDao adminUserSubTaskDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminUserSubTaskEntity> getUserSubTask(AdminUserSubTaskEntity auste) {
		return adminUserSubTaskDao.selectUserSubTask(auste);
	}

}
