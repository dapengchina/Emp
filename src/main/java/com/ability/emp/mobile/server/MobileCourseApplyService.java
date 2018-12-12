package com.ability.emp.mobile.server;

import java.util.List;

import com.ability.emp.mobile.entity.MobileCourseApplyEntity;

public interface MobileCourseApplyService{
	
	List<MobileCourseApplyEntity> selectByCourseInfo(MobileCourseApplyEntity info); 
	
	boolean insert(MobileCourseApplyEntity course);
	
}