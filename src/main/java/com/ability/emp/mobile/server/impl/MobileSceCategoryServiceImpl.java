package com.ability.emp.mobile.server.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileSceCategoryDao;
import com.ability.emp.mobile.entity.MobileFirstCategoryEntity;
import com.ability.emp.mobile.entity.MobileSceCategoryEntity;
import com.ability.emp.mobile.server.MobileSceCategoryService;



@Service("MobileSceCategoryService")
public class MobileSceCategoryServiceImpl implements MobileSceCategoryService{
	
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileSceCategoryDao mobileSceCategoryDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<MobileSceCategoryEntity> getScenListDropLetData(MobileSceCategoryEntity me) {
		return mobileSceCategoryDao.getScenListDropLetData(me);
	}

}
