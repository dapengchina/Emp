package com.ability.emp.mobile.server.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileAdminDao;
import com.ability.emp.mobile.entity.MobileAdminEntity;
import com.ability.emp.mobile.server.MobileAdminService;


@Service("MobileAdminService")
public class MobileAdminServiceImpl implements MobileAdminService{
	
	
	  @SuppressWarnings("rawtypes")
	  @Resource
	  private MobileAdminDao mobileAdminDao;

	  @SuppressWarnings("unchecked")
	  @Override
	  public List<MobileAdminEntity> getTutor(MobileAdminEntity me) {
		return mobileAdminDao.selectTutor(me);
	  }

}
