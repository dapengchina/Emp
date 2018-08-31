package com.ability.emp.mobile.server.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileFirstCategoryDao;
import com.ability.emp.mobile.entity.MobileFirstCategoryEntity;
import com.ability.emp.mobile.server.MobileFirstCategoryService;


@Service("MobileFirstCategoryService")
public class MobileFirstCategoryServiceImpl implements MobileFirstCategoryService{

	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileFirstCategoryDao mobileFirstCategoryDao;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MobileFirstCategoryEntity> getIndexData(MobileFirstCategoryEntity me) {
		return mobileFirstCategoryDao.getIndexData(me);
	}

}
