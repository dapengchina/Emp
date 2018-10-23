package com.ability.emp.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.admin.entity.AdminScenListDropLetEntity;
import com.ability.emp.base.BaseDao;


@Mapper
public interface AdminScenListDropLetDao<T> extends BaseDao<T> {
	
	
	
	public List<AdminScenListDropLetEntity> selectScenList(AdminScenListDropLetEntity me);

    public AdminScenListDropLetEntity selectScen(AdminScenListDropLetEntity me);
	
}
