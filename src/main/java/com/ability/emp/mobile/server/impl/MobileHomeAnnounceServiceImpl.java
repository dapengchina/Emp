package com.ability.emp.mobile.server.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileHomeAnnounceDao;
import com.ability.emp.mobile.entity.MobileHomeAnnounceEntity;
import com.ability.emp.mobile.server.MobileHomeAnnounceService;

@Service("MobileHomeAnnounceService")
public class MobileHomeAnnounceServiceImpl implements MobileHomeAnnounceService{
	
	@SuppressWarnings("rawtypes")
	@Resource 
	private MobileHomeAnnounceDao mobileHomeAnnounceDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public int insert(MobileHomeAnnounceEntity ase) {
		// TODO Auto-generated method stub
		return mobileHomeAnnounceDao.insert(ase);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int update(MobileHomeAnnounceEntity ase) {
		// TODO Auto-generated method stub
		return mobileHomeAnnounceDao.update(ase);
	}

	@Override
	public MobileHomeAnnounceEntity selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return mobileHomeAnnounceDao.selectByPrimaryKey(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MobileHomeAnnounceEntity> selectList(MobileHomeAnnounceEntity ase) {
		// TODO Auto-generated method stub
		return mobileHomeAnnounceDao.selectList(ase);
	}
	
}