package com.ability.emp.admin.dao;


import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.ability.emp.admin.entity.AdminTaskEntity;
import com.ability.emp.base.BaseDao;


@Mapper
public interface AdminTaskDao<T> extends BaseDao<T>{
	
	List<AdminTaskEntity> queryAll(AdminTaskEntity ate);
	
	String findTaskName(AdminTaskEntity adminTaskEntity);
	
	AdminTaskEntity queryTaskById(Map<String,Object> param);
	
	List<AdminTaskEntity> selectStudyCalendar(String id);
	
	AdminTaskEntity selectTask(AdminTaskEntity ate);
	
}