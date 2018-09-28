package com.ability.emp.mobile.server;

import java.util.List;

import com.ability.emp.mobile.entity.MobileUserTaskEntity;

public interface MobileUserTaskService {
	
	
	
	
	   public int addUserTask(MobileUserTaskEntity me);
	   
	   public List<MobileUserTaskEntity> getUserTask(MobileUserTaskEntity me);

}
