package com.ability.emp.mobile.dao;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.ability.emp.base.BaseDao;
import com.ability.emp.mobile.entity.MobileTaskEntity;

@Mapper
public interface MobileTaskDao<T> extends BaseDao<T>{
	
	List<MobileTaskEntity> selectStudyCalendar(String id);
	
}