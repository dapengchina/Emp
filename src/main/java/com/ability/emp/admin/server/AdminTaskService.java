package com.ability.emp.admin.server;

import java.util.List;
import com.ability.emp.admin.entity.AdminTaskEntity;

public interface AdminTaskService {
	
	List<AdminTaskEntity> queryAll(AdminTaskEntity ate);
	
	int insert(AdminTaskEntity taskEntiy);
	
	int update(AdminTaskEntity taskEntiy); 
	
	int delete(String id);
	
	AdminTaskEntity queryTaskById(String id);
	
	List<AdminTaskEntity> selectStudyCalendar(String id);
	
	AdminTaskEntity getTask(AdminTaskEntity ate);
	
}
