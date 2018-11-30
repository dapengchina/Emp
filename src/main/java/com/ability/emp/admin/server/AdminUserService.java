package com.ability.emp.admin.server;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.ability.emp.admin.entity.AdminUserEntity;

public interface AdminUserService {
	
	List<AdminUserEntity> queryAll(AdminUserEntity adminUserEntity);
	
	@SuppressWarnings({ "rawtypes" })
	List importUser(String fileName,MultipartFile mfile);
	
	void taskAppoint(String ids[], String taskid);
	
	String findTaskName(String taskId);
	
	String verifyUserAppoint(HttpServletRequest req);
	
	AdminUserEntity queryUserById(String id);
	
}
