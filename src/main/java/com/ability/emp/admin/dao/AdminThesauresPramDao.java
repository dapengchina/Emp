package com.ability.emp.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.admin.entity.AdminThesauresPramEntity;
import com.ability.emp.base.BaseDao;
@Mapper
public interface AdminThesauresPramDao<T> extends BaseDao<T> {
    int deleteByPrimaryKey(String key);

    int insert(AdminThesauresPramEntity record);

    int insertSelective(AdminThesauresPramEntity record);

    AdminThesauresPramEntity selectByPrimaryKey(String key);

    int updateByPrimaryKey(AdminThesauresPramEntity record);
    
    List<AdminThesauresPramEntity> queryAll(AdminThesauresPramEntity record);
}