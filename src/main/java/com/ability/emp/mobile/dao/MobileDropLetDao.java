package com.ability.emp.mobile.dao;



import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.base.BaseDao;
import com.ability.emp.mobile.entity.MobileDropLetEntity;

@Mapper
public interface MobileDropLetDao<T> extends BaseDao<T> {
	
	
	public MobileDropLetEntity selectByID(MobileDropLetEntity me);

}
