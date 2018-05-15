package com.ability.emp.mobile.server;

import java.util.List;

import com.ability.emp.mobile.entity.MobileHitCardEntity;

public interface MobileHitCardService {

	
	   int count(String id);
	   
	   List<MobileHitCardEntity> queryByUserID(MobileHitCardEntity mhce);
}
