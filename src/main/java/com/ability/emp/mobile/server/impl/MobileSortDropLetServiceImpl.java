package com.ability.emp.mobile.server.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ability.emp.mobile.dao.MobileSortDropLetDao;
import com.ability.emp.mobile.entity.MobileSortDropLetEntity;
import com.ability.emp.mobile.server.MobileSortDropLetService;


@Service("MobileSortDropLetService")
public class MobileSortDropLetServiceImpl implements MobileSortDropLetService{

	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileSortDropLetDao mobileSortDropLetDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MobileSortDropLetEntity> getSortData(MobileSortDropLetEntity me) {
		return mobileSortDropLetDao.selectSortData(me);
	}

}
