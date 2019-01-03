package com.ability.emp.admin.server;


import com.ability.emp.admin.entity.AdminHitCardEntity;

public interface AdminHitCardService {
	
	int updateByTaskID(AdminHitCardEntity mhce);
	
	int updateByParam(AdminHitCardEntity mhce);
}
