package com.ability.emp.admin.dao;

import com.ability.emp.admin.entity.AdminWordRecordEntity;
import com.ability.emp.base.BaseDao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminWordRecordDao <T> extends BaseDao<T> {
	List<AdminWordRecordEntity> selectWord(String id);
}
