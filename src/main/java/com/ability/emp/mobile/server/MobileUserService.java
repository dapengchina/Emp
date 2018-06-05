package com.ability.emp.mobile.server;

import com.ability.emp.mobile.entity.MobileUserEntity;

public interface MobileUserService {
	
	
	   MobileUserEntity queryById(String id);
	   
	   MobileUserEntity login2(MobileUserEntity mue);
	   
	   int update(MobileUserEntity mue);
}
