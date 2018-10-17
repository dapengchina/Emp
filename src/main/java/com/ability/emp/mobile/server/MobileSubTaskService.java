package com.ability.emp.mobile.server;

import java.util.List;

import com.ability.emp.mobile.entity.MobileSubTaskEntity;

public interface MobileSubTaskService {
	
	
	public int saveScore(MobileSubTaskEntity mste);
	
	public MobileSubTaskEntity getSubTask(MobileSubTaskEntity mste);
	
	public List<MobileSubTaskEntity> getSubTaskList(MobileSubTaskEntity mste);

}
