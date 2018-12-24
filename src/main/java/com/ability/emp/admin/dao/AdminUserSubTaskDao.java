package com.ability.emp.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.admin.entity.AdminUserSubTaskEntity;
import com.ability.emp.base.BaseDao;


@Mapper
public interface AdminUserSubTaskDao<T> extends BaseDao<T> {
	
	
	
	public List<AdminUserSubTaskEntity> selectUserSubTask(AdminUserSubTaskEntity auste);

	public int endTask(AdminUserSubTaskEntity auste);
}
