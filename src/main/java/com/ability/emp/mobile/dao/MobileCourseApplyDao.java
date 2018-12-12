package com.ability.emp.mobile.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.base.BaseDao;
import com.ability.emp.mobile.entity.MobileCourseApplyEntity;

@Mapper
public interface MobileCourseApplyDao<T> extends BaseDao<T>{
	
	List<MobileCourseApplyEntity> selectByCourseInfo(MobileCourseApplyEntity info); 
	
	int insertSelective(MobileCourseApplyEntity course);
}