package com.ability.emp.mobile.server.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileBearWordDao;
import com.ability.emp.mobile.entity.MobileWordRecordEntity;
import com.ability.emp.mobile.server.MobileBearWordService;

@Service("MobileBearWordService")
public class MobileBearWordServiceImpl implements MobileBearWordService{
	
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileBearWordDao mobileBearWordDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<MobileWordRecordEntity> query(MobileWordRecordEntity mwre) {
		return mobileBearWordDao.query(mwre);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int update(MobileWordRecordEntity mwre) {
		return mobileBearWordDao.update(mwre);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MobileWordRecordEntity> queryAll(MobileWordRecordEntity mwre) {
		return mobileBearWordDao.queryAll(mwre);
	}

}
