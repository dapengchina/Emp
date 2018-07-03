package com.ability.emp.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.poi.ss.formula.functions.T;

import com.ability.emp.admin.entity.vo.AdminUserReportVo;
import com.ability.emp.base.BaseDao;

@SuppressWarnings("hiding")
@Mapper
public interface AdminUserReportDao<T> extends BaseDao<T>{
	
	
	
	List<AdminUserReportVo> queryAll(AdminUserReportVo aue);
	
	List<AdminUserReportVo> queryHitCardRecord(AdminUserReportVo aue);

}
