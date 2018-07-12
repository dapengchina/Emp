package com.ability.emp.admin.server;

import java.util.List;

import com.ability.emp.admin.entity.AdminThesauresPramEntity;

public interface AdminThesauresPramService {

	int insert(AdminThesauresPramEntity record);

	int update(AdminThesauresPramEntity record);

	List<AdminThesauresPramEntity> queryAll(AdminThesauresPramEntity record);
	
	AdminThesauresPramEntity getByID(String id);
}
