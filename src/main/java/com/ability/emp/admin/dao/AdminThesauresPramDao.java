package com.ability.emp.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.admin.entity.AdminThesauresPramEntity;
import com.ability.emp.base.BaseDao;
@Mapper
public interface AdminThesauresPramDao<T> extends BaseDao<T> {
   
    List<AdminThesauresPramEntity> queryAll(AdminThesauresPramEntity record);
    
    AdminThesauresPramEntity getByID(String id);
}