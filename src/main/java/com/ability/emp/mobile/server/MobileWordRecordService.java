package com.ability.emp.mobile.server;

import java.util.List;
import java.util.Map;

import com.ability.emp.mobile.entity.MobileWordRecordEntity;

public interface MobileWordRecordService {

	Map<String, String> fallibleWord(String userId);
	
	
	List<MobileWordRecordEntity> queryEasyMistake(MobileWordRecordEntity mwre);
	
	int update(MobileWordRecordEntity mwre);
}
