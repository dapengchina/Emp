package com.ability.emp.mobile.server;

import java.util.List;

import com.ability.emp.mobile.entity.MobileWordRecordEntity;

public interface MobileBearWordService {
	
	
	   List<MobileWordRecordEntity> query(MobileWordRecordEntity mwre);
	   
	   int update(MobileWordRecordEntity mwre);
	   
	   List<MobileWordRecordEntity> queryAll(MobileWordRecordEntity mwre);

}
