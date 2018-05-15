package com.ability.emp.mobile.server;

import java.util.List;
import com.ability.emp.mobile.entity.MobileTaskEntity;

public interface MobileTaskService {
	
	List<MobileTaskEntity> selectStudyCalendar(String id);
	
}
