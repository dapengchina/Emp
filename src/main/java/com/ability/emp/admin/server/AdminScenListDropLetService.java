package com.ability.emp.admin.server;

import java.util.List;

import com.ability.emp.admin.entity.AdminScenListDropLetEntity;

public interface AdminScenListDropLetService {
	
	
	
	public List<AdminScenListDropLetEntity> getScenListDropletData(AdminScenListDropLetEntity me);
	
	public AdminScenListDropLetEntity getScen(AdminScenListDropLetEntity me);

}
