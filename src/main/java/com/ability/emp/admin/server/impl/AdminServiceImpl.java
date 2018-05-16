package com.ability.emp.admin.server.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.admin.dao.AdminDao;
import com.ability.emp.admin.entity.AdminEntity;
import com.ability.emp.admin.server.AdminService;
import com.ability.emp.util.EncryptionUtil;
import com.ability.emp.util.UUIDUtil;

@Service("AdminService") 
public class AdminServiceImpl implements AdminService{
	
	@SuppressWarnings("rawtypes")
	@Resource
	private AdminDao adminDao;
	
/*	@SuppressWarnings("unchecked")
	@Override
	public List<AdminEntity> queryAll() {
		return adminDao.queryAll();
	}*/

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminEntity> queryAll(AdminEntity adminEntity) {
		return adminDao.queryAll(adminEntity);
	}


	@Override
	public AdminEntity login(String name, String pwd) {
		return adminDao.login(name, pwd);
	}
	
	@SuppressWarnings("unchecked")
	public Integer insert(String adminName, String adminPwd) {
		adminPwd = EncryptionUtil.Md5Encrypt(adminPwd);
		AdminEntity adminEntity = new AdminEntity();
		adminEntity.setId(UUIDUtil.generateUUID());
		adminEntity.setAdminName(adminName);
		adminEntity.setAdminPwd(adminPwd);
		adminEntity.setStatus("0");
		return adminDao.insert(adminEntity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer verifieName(String adminName) {
		Map<String, Object> map = new HashMap<>();
		map.put("adminName", adminName);
		return adminDao.verifieName(map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer update(AdminEntity adminEntity) {
		Map<String, Object> map = new HashMap<>();
		map.put("id",adminEntity.getId());
		map.put("status", adminEntity.getStatus());
		return adminDao.update(map);
	}
}
