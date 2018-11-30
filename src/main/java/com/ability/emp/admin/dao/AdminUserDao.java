package com.ability.emp.admin.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ability.emp.admin.entity.AdminUserEntity;
import com.ability.emp.base.BaseDao;

@Mapper
public interface AdminUserDao<T> extends BaseDao<T>{
   
    List<T> queryAll(AdminUserEntity adminUserEntity);  
    
    String verifyUserAppoint(@Param("id")String id);
    
    AdminUserEntity queryUserById(String id);
}