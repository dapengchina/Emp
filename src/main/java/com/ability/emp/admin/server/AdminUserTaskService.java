package com.ability.emp.admin.server;

import java.util.List;

import com.ability.emp.admin.entity.AdminUserTaskEntity;

public interface AdminUserTaskService {
	
	
	   public int addUserTask(AdminUserTaskEntity ae);
	   
	   public List<AdminUserTaskEntity> getUserTaskByUserId(AdminUserTaskEntity ae);
	   
	   public AdminUserTaskEntity getUserTask(AdminUserTaskEntity ae);
	   
	   public List<AdminUserTaskEntity> getTask(AdminUserTaskEntity ae);
	   
	   public int deleteUserTask(AdminUserTaskEntity ae);

}
