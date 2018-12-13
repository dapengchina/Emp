package com.ability.emp.mobile.server;

import java.util.List;

import com.ability.emp.mobile.entity.MobileUserCenterEntity;

public interface MobileUserCenterService {
	
	
	
	   public List<MobileUserCenterEntity> getUserCenterList(MobileUserCenterEntity muce);
	   
	   public List<MobileUserCenterEntity> getGroupList();

}
