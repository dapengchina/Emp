package com.ability.emp.admin.server;

import java.util.List;

import com.ability.emp.admin.entity.AdminCourseApplyEntity;

public interface AdminCourseApplyListService {

    AdminCourseApplyEntity selectByPrimaryKey(String id);

    boolean update(AdminCourseApplyEntity record);
    
    List<AdminCourseApplyEntity> queryAll(AdminCourseApplyEntity record);
}