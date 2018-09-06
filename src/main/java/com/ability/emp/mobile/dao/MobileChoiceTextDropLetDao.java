package com.ability.emp.mobile.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.base.BaseDao;
import com.ability.emp.mobile.entity.MobileChoiceTextDropLetEntity;



@Mapper
public interface MobileChoiceTextDropLetDao<T> extends BaseDao<T> {
	
	
	
	public MobileChoiceTextDropLetEntity selectChoiceDropLetData(MobileChoiceTextDropLetEntity me);

}
