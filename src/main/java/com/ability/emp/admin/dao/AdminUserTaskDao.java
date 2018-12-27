package com.ability.emp.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.admin.entity.AdminUserTaskEntity;
import com.ability.emp.base.BaseDao;

@Mapper
public interface AdminUserTaskDao<T>  extends BaseDao<T> {
	
	
	public List<AdminUserTaskEntity> selectTask(AdminUserTaskEntity ae);
	
	public AdminUserTaskEntity selectUserTask(AdminUserTaskEntity ae);
	
	public int deleteUserTask(AdminUserTaskEntity ae);
	
	public int endTask(AdminUserTaskEntity ae);

}
