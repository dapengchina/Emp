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
	
	@Override
	public int deleteByPrimaryKey(String key) {
		return adminThesauresPramDao.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(AdminThesauresPramEntity record) {
		return adminThesauresPramDao.insert(record);
	}

	@Override
	public int insertSelective(AdminThesauresPramEntity record) {
		return adminThesauresPramDao.insertSelective(record);
	}

	@Override
	public AdminThesauresPramEntity selectByPrimaryKey(String key) {
		return adminThesauresPramDao.selectByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(AdminThesauresPramEntity record) {
		return adminThesauresPramDao.updateByPrimaryKey(record);
	}

	@Override
	public List<AdminThesauresPramEntity> queryAll() {
		return adminThesauresPramDao.queryAll();
	}

}
