package com.ability.emp.mobile.server.impl;

import com.ability.emp.mobile.server.MobileCourseApplyService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileCourseApplyDao;
import com.ability.emp.mobile.entity.MobileCourseApplyEntity;

@Service("MobileCourseApplyService")
public class MobileCourseApplyServiceImpl implements MobileCourseApplyService{
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileCourseApplyDao mobileCourseApplyDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<MobileCourseApplyEntity> selectByCourseInfo(MobileCourseApplyEntity info) {
		return mobileCourseApplyDao.selectByCourseInfo(info);
	}

	@Override
	public boolean insert(MobileCourseApplyEntity course) {
		if(mobileCourseApplyDao.insertSelective(course)>0){
			return true;
		}
		return false;
	}
}