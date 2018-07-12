package com.ability.emp.admin.server.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.admin.dao.AdminThesauresPramDao;
import com.ability.emp.admin.entity.AdminThesauresPramEntity;
import com.ability.emp.admin.server.AdminThesauresPramService;


@Service("AdminThesauresPramService")
public class AdminThesauresPramServiceImpl implements AdminThesauresPramService {
	
	
	@SuppressWarnings("rawtypes")
	@Resource
	AdminThesauresPramDao adminThesauresPramDao;

	@SuppressWarnings("unchecked")
	@Override
	public int insert(AdminThesauresPramEntity record) {
		return adminThesauresPramDao.insert(record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminThesauresPramEntity> queryAll(AdminThesauresPramEntity record) {
		return adminThesauresPramDao.queryAll(record);
	}

	@Override
	public int update(AdminThesauresPramEntity record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AdminThesauresPramEntity getByID(String id) {
		return adminThesauresPramDao.getByID(id);
	}

}
