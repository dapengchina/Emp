package com.ability.emp.mobile.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.poi.ss.formula.functions.T;

import com.ability.emp.base.BaseDao;
import com.ability.emp.mobile.entity.MobileWordRecordEntity;

@SuppressWarnings("hiding")
@Mapper
public interface MobileBearWordDao<T> extends BaseDao<T>{
	
	
	   List<MobileWordRecordEntity> query(MobileWordRecordEntity mwre);
	   
	   List<MobileWordRecordEntity> queryEasyMistake(MobileWordRecordEntity mwre);
	   
	   List<MobileWordRecordEntity> queryAll(MobileWordRecordEntity mwre);
	   
	   int queryDoneCount(MobileWordRecordEntity mwre);
	   
	   List<MobileWordRecordEntity> selectWordRecord(MobileWordRecordEntity mwre);

}
