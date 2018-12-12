package com.ability.emp.admin.server;

import java.util.List;

import com.ability.emp.admin.entity.AdminScecategoryEntity;

public interface AdminScecategoryService {
	
	
	   public List<AdminScecategoryEntity> getCourse(AdminScecategoryEntity ase);
	   
	   public AdminScecategoryEntity getCourseByID(AdminScecategoryEntity ase);
	   
	   public boolean insert(AdminScecategoryEntity ase);
	   
	   public List<AdminScecategoryEntity> getIndexList(String ase);

}
