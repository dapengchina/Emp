package com.ability.emp.admin.server.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.admin.dao.AdminUserTaskDao;
import com.ability.emp.admin.entity.AdminUserTaskEntity;
import com.ability.emp.admin.server.AdminUserTaskService;


@Service("AdminUserTaskService")
public class AdminUserTaskServiceImpl implements AdminUserTaskService{
	
	
	
	@SuppressWarnings("rawtypes")
	@Resource
	private AdminUserTaskDao adminUserTaskDao;

	@SuppressWarnings("unchecked")
	@Override
	public int addUserTask(AdminUserTaskEntity ae) {
		return adminUserTaskDao.insert(ae);
	}

}
