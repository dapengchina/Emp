package com.ability.emp.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.admin.entity.AdminSubTaskEntity;
import com.ability.emp.base.BaseDao;



@Mapper
public interface AdminSubTaskDao<T> extends BaseDao<T> {
	
	
	public AdminSubTaskEntity selectSubTask(AdminSubTaskEntity mste);
	
	public List<AdminSubTaskEntity> selectSubTaskList(AdminSubTaskEntity mste);

}
