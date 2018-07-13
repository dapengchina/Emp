package com.ability.emp.mobile.server;

import java.util.List;

import com.ability.emp.mobile.entity.MobileWordEntity;

public interface MobileWordService {
	
	   MobileWordEntity mean(String id);
	   
	   MobileWordEntity queryWordById(String id);
	   
	   List<MobileWordEntity> queryWordAll(MobileWordEntity entity);
}
