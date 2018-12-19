package com.ability.emp.mobile.server;

import java.util.List;

import com.ability.emp.mobile.entity.MobileHomeAnnounceEntity;

public interface MobileHomeAnnounceService {
	
	int insert(MobileHomeAnnounceEntity ase);
	
	int update(MobileHomeAnnounceEntity ase);
	
	MobileHomeAnnounceEntity selectByPrimaryKey(String id);
	
	List<MobileHomeAnnounceEntity> selectList(MobileHomeAnnounceEntity ase);
}