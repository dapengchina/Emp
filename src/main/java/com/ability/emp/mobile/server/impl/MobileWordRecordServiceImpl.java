package com.ability.emp.mobile.server.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ability.emp.mobile.dao.MobileWordDao;
import com.ability.emp.mobile.dao.MobileWordRecordDao;
import com.ability.emp.mobile.entity.MobileWordRecordEntity;
import com.ability.emp.mobile.server.MobileWordRecordService;


@Service("MobileWordRecordService") 
public class MobileWordRecordServiceImpl implements MobileWordRecordService {

	@SuppressWarnings("rawtypes")
	@Resource
	private MobileWordRecordDao wordRecordDao;
	@Resource
	private MobileWordDao wordDao;

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> fallibleWord(String userId) {
		List<MobileWordRecordEntity> wordRecord = wordRecordDao.selectWord(userId);
		Map<String, String> map = new HashMap<String, String>();
		String wordId;
		String wordName;
		String wordIption;
		for (int i = 0; i < wordRecord.size(); i++) {
			wordId = wordRecord.get(i).getWordId(); 
			wordName = wordRecord.get(i).getWord();
			wordIption = wordDao.selectIption(wordId);
			map.put(wordName, wordIption);
		}
		return map;
	}
}
