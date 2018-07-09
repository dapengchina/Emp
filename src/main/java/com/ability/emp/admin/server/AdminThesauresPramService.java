package com.ability.emp.admin.server;

import java.util.List;

import com.ability.emp.admin.entity.AdminThesauresPramEntity;

public interface AdminThesauresPramService {
	int deleteByPrimaryKey(String key);

	int insert(AdminThesauresPramEntity record);

	int insertSelective(AdminThesauresPramEntity record);

	AdminThesauresPramEntity selectByPrimaryKey(String key);

	int updateByPrimaryKey(AdminThesauresPramEntity record);

	List<AdminThesauresPramEntity> queryAll(AdminThesauresPramEntity record);
}
