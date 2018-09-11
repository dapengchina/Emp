package com.ability.emp.mobile.server.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileChoiceAudioDropletDao;
import com.ability.emp.mobile.entity.MobileChoiceAudioDropletEntity;
import com.ability.emp.mobile.server.MobileChoiceAudioDropletService;


@Service("MobileChoiceAudioDropletService")
public class MobileChoiceAudioDropletServiceImpl implements MobileChoiceAudioDropletService{

	
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileChoiceAudioDropletDao mobileChoiceAudioDropletDao;
	
	
	@Override
	public MobileChoiceAudioDropletEntity getChoiceAudioDropletData(MobileChoiceAudioDropletEntity me) {
		return mobileChoiceAudioDropletDao.selectChoiceAudioDropletData(me);
	}

}
