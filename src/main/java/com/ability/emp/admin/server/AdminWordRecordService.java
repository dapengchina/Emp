package com.ability.emp.admin.server;

import java.util.List;
import java.util.Map;
import com.ability.emp.admin.entity.AdminWordRecordEntity;

public interface AdminWordRecordService {
	
	List<AdminWordRecordEntity> queryAll();
	
	Integer count(Map<String,Object> map);
	
	Integer update(AdminWordRecordEntity wordRecordEntiy); 
	
	Integer insert(AdminWordRecordEntity wordRecordEntiy);
	
	List<AdminWordRecordEntity> getWordRecord(AdminWordRecordEntity awee);
	
}
