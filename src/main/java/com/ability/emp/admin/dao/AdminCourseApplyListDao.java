package com.ability.emp.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ability.emp.admin.entity.AdminCourseApplyEntity;
import com.ability.emp.base.BaseDao;

@Mapper
public interface AdminCourseApplyListDao <T>  extends BaseDao<T> {

    AdminCourseApplyEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AdminCourseApplyEntity record);
    
    List<AdminCourseApplyEntity> queryAll(AdminCourseApplyEntity record);
}