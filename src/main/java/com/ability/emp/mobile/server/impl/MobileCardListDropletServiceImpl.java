package com.ability.emp.mobile.server.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileCardListDropletDao;
import com.ability.emp.mobile.entity.MobileCardListDropletEntity;
import com.ability.emp.mobile.server.MobileCardListDropletService;

@Service("MobileCardListDropletService")
public class MobileCardListDropletServiceImpl implements MobileCardListDropletService{
	
	
	
	@SuppressWarnings("rawtypes")
	@Resource 
	private MobileCardListDropletDao mobileCardListDropletDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<MobileCardListDropletEntity> getCardListDropletData(MobileCardListDropletEntity me) {
		return mobileCardListDropletDao.selectCardListDroplet(me);
	}

}
