package com.ability.emp.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.admin.entity.AdminCardListDropletEntity;
import com.ability.emp.base.BaseDao;



@Mapper
public interface AdminCardListDropletDao<T> extends BaseDao<T> {
	
	
	
	public List<AdminCardListDropletEntity> selectCardListDroplet(AdminCardListDropletEntity me);

}
