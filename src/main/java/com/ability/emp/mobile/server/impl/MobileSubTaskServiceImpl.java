package com.ability.emp.mobile.server.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileSubTaskDao;
import com.ability.emp.mobile.server.MobileSubTaskService;


@Service("MobileSubTaskService")
public class MobileSubTaskServiceImpl implements MobileSubTaskService{
	
	
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileSubTaskDao mobileSubTaskDao;

}
