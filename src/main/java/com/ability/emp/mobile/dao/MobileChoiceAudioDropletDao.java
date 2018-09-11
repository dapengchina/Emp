package com.ability.emp.mobile.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.base.BaseDao;
import com.ability.emp.mobile.entity.MobileChoiceAudioDropletEntity;



@Mapper
public interface MobileChoiceAudioDropletDao<T> extends BaseDao<T> {
	
	
	
	public MobileChoiceAudioDropletEntity selectChoiceAudioDropletData(MobileChoiceAudioDropletEntity me);

}
