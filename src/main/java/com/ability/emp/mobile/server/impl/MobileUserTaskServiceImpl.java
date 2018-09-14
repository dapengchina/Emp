package com.ability.emp.mobile.server.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileUserTaskDao;
import com.ability.emp.mobile.server.MobileUserTaskService;


@Service("MobileUserTaskService")
public class MobileUserTaskServiceImpl implements MobileUserTaskService{
	
	
	
	   @Resource
	   private MobileUserTaskDao mobileUserTaskDao;

}
