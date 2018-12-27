package com.ability.emp.admin.server.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ability.emp.admin.dao.AdminTaskDao;
import com.ability.emp.admin.dao.AdminUserTaskDao;
import com.ability.emp.admin.dao.AdminWordRecordDao;
import com.ability.emp.admin.entity.AdminTaskEntity;
import com.ability.emp.admin.entity.AdminUserTaskEntity;
import com.ability.emp.admin.entity.AdminWordRecordEntity;
import com.ability.emp.admin.server.AdminUserTaskService;
import com.ability.emp.constant.SysConstant;


@Service("AdminUserTaskService")
public class AdminUserTaskServiceImpl implements AdminUserTaskService{
	
	
	
	@SuppressWarnings("rawtypes")
	@Resource
	private AdminUserTaskDao adminUserTaskDao;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private AdminTaskDao adminTaskDao;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private AdminWordRecordDao adminWordRecordDao;

	@SuppressWarnings("unchecked")
	@Override
	public int addUserTask(AdminUserTaskEntity ae) {
		return adminUserTaskDao.insert(ae);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminUserTaskEntity> getUserTaskByUserId(AdminUserTaskEntity ae) {
		return adminUserTaskDao.selectTask(ae);
	}

	@Override
	public AdminUserTaskEntity getUserTask(AdminUserTaskEntity ae) {
		return adminUserTaskDao.selectUserTask(ae);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminUserTaskEntity> getTask(AdminUserTaskEntity ae) {
		return adminUserTaskDao.selectTask(ae);
	}

	@Override
	//事务
	@Transactional
	public int deleteUserTask(AdminUserTaskEntity ae) {
		try{
			//根据任务ID查询任务
			AdminTaskEntity task = new AdminTaskEntity();
			task.setId(ae.getTaskid());
			AdminTaskEntity returnTask = adminTaskDao.selectTask(task);
			//根据词库类型和用户ID删除用户单词记录表
			if(returnTask.getThesaures_Type().equals(SysConstant.TASK_TYPE1)){
				AdminWordRecordEntity userWordRecord = new AdminWordRecordEntity();
				userWordRecord.setThesaurus(returnTask.getThesaures_Type());
				userWordRecord.setUserId(ae.getUserid());
				adminWordRecordDao.deleteUserWordRecord(userWordRecord);
			}
			adminUserTaskDao.deleteUserTask(ae);
			
			return 1;
		}catch(Exception e){
			return -1;
		}
	}

	@Override
	public int endTask(AdminUserTaskEntity ae) {
		return adminUserTaskDao.endTask(ae);
	}

}
