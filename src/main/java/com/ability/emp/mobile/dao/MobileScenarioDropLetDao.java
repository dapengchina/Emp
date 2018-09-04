package com.ability.emp.mobile.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.base.BaseDao;
import com.ability.emp.mobile.entity.MobileScenarioDropLetEntity;


@Mapper
public interface MobileScenarioDropLetDao<T> extends BaseDao<T> {
	
	
	public List<MobileScenarioDropLetEntity> selectScenarioData(MobileScenarioDropLetEntity me);

}
