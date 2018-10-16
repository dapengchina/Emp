package com.ability.emp.mobile.server;

import com.ability.emp.mobile.entity.MobileSubTaskEntity;

public interface MobileSubTaskService {
	
	
	public int saveScore(MobileSubTaskEntity mste);
	
	public MobileSubTaskEntity getSubTask(MobileSubTaskEntity mste);

}
