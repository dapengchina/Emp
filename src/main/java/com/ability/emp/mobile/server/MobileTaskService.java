package com.ability.emp.mobile.server;

import com.ability.emp.mobile.entity.MobileTaskEntity;
import java.util.List;


public interface MobileTaskService {
	
	List<MobileTaskEntity> selectStudyCalendar(String id);
	
	MobileTaskEntity queryById(String id);
	
	MobileTaskEntity getTask(MobileTaskEntity mte);
	
}
