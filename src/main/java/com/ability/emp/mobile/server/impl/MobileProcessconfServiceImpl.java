package com.ability.emp.mobile.server.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileProcessconfDao;
import com.ability.emp.mobile.entity.MobileProcessconfEntity;
import com.ability.emp.mobile.server.MobileProcessconfService;


@Service("MobileProcessconfService")
public class MobileProcessconfServiceImpl implements MobileProcessconfService{
	
	
	
	@SuppressWarnings("rawtypes")
	@Resource 
	private MobileProcessconfDao mobileProcessconfDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<MobileProcessconfEntity> getProcessconf(MobileProcessconfEntity me) {
		return mobileProcessconfDao.selectProcess(me);
	}

}
