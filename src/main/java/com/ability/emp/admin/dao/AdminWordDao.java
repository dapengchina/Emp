package com.ability.emp.admin.dao;

import com.ability.emp.admin.entity.AdminWordEntity;
import com.ability.emp.admin.entity.vo.AdminWordVo;
import com.ability.emp.base.BaseDao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminWordDao <T> extends BaseDao<T> {
	List<AdminWordEntity> queryWordById(Map<String,Object> param);
	
	List<AdminWordEntity> queryWordAll(AdminWordEntity awe);
	
	String selectIption(String id);
	
	int updateBatch(AdminWordVo adminWordVo);
}
