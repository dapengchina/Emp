package com.ability.emp.mobile.server.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileHitCardDao;
import com.ability.emp.mobile.entity.MobileHitCardEntity;
import com.ability.emp.mobile.server.MobileHitCardService;


@Service("MobileHitCardService")
public class MobileHitCardServiceImpl implements MobileHitCardService{
	
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileHitCardDao mobileHitCardDao;

	@Override
	public int count(String id) {
		return mobileHitCardDao.count(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MobileHitCardEntity> queryByUserID(MobileHitCardEntity mhce) {
		return mobileHitCardDao.queryByUserID(mhce);
	}

}
