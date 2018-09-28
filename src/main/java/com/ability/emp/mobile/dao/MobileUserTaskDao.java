package com.ability.emp.mobile.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.base.BaseDao;
import com.ability.emp.mobile.entity.MobileUserTaskEntity;


@Mapper
public interface MobileUserTaskDao<T> extends BaseDao<T> {
	
	public List<MobileUserTaskEntity> selectUserTask(MobileUserTaskEntity me);

}
