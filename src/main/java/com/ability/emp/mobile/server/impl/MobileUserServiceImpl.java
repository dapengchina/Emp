package com.ability.emp.mobile.server.impl;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ability.emp.constant.SysConstant;
import com.ability.emp.mobile.dao.MobileBearWordDao;
import com.ability.emp.mobile.dao.MobileCardListDropletDao;
import com.ability.emp.mobile.dao.MobileChoiceAudioDropletDao;
import com.ability.emp.mobile.dao.MobileChoiceTextDropLetDao;
import com.ability.emp.mobile.dao.MobileDictationDropLetDao;
import com.ability.emp.mobile.dao.MobileFillBlankDropLetDao;
import com.ability.emp.mobile.dao.MobileMatchDropLetDao;
import com.ability.emp.mobile.dao.MobileReadSpeakDropLetDao;
import com.ability.emp.mobile.dao.MobileSceCategoryDao;
import com.ability.emp.mobile.dao.MobileScenListDropLetDao;
import com.ability.emp.mobile.dao.MobileScenarioDropLetDao;
import com.ability.emp.mobile.dao.MobileSortDropLetDao;
import com.ability.emp.mobile.dao.MobileSubTaskDao;
import com.ability.emp.mobile.dao.MobileTaskDao;
import com.ability.emp.mobile.dao.MobileUserDao;
import com.ability.emp.mobile.dao.MobileUserTaskDao;
import com.ability.emp.mobile.dao.MobileVideoDropLetDao;
import com.ability.emp.mobile.dao.MobileWordDao;
import com.ability.emp.mobile.entity.MobileCardListDropletEntity;
import com.ability.emp.mobile.entity.MobileSceCategoryEntity;
import com.ability.emp.mobile.entity.MobileScenListDropLetEntity;
import com.ability.emp.mobile.entity.MobileSubTaskEntity;
import com.ability.emp.mobile.entity.MobileTaskEntity;
import com.ability.emp.mobile.entity.MobileUserEntity;
import com.ability.emp.mobile.entity.MobileUserTaskEntity;
import com.ability.emp.mobile.entity.MobileWordEntity;
import com.ability.emp.mobile.entity.MobileWordRecordEntity;
import com.ability.emp.mobile.server.MobileUserService;
import com.ability.emp.util.UUIDUtil;

@Service("MobileUserService") 
public class MobileUserServiceImpl implements MobileUserService{
	
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileUserDao mobileUserDao;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileUserTaskDao mobileUserTaskDao;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileSceCategoryDao mobileSceCategoryDao;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileSubTaskDao mobileSubTaskDao;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileTaskDao mobileTaskDao;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileScenListDropLetDao mobileScenListDropLetDao;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileCardListDropletDao mobileCardListDropletDao;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileScenarioDropLetDao mobileScenarioDropLetDao;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileChoiceTextDropLetDao mobileChoiceTextDropLetDao;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileFillBlankDropLetDao mobileFillBlankDropLetDao;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileChoiceAudioDropletDao mobileChoiceAudioDropletDao;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileReadSpeakDropLetDao mobileReadSpeakDropLetDao;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileSortDropLetDao mobileSortDropLetDao;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileMatchDropLetDao mobileMatchDropLetDao;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileVideoDropLetDao mobileVideoDropLetDao;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileDictationDropLetDao mobileDictationDropLetDao;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileWordDao mobileWordDao;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private MobileBearWordDao mobileBearWordDao;
	

	@Override
	public MobileUserEntity queryById(String id) {
		return (MobileUserEntity) mobileUserDao.queryById(id);
	}

	@Override
	public MobileUserEntity login2(MobileUserEntity mue) {
		return mobileUserDao.login2(mue);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int update(MobileUserEntity mue) {
		return mobileUserDao.update(mue);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void registerUser(MobileUserEntity mue) {
		MobileSceCategoryEntity course = new MobileSceCategoryEntity();//课程
		MobileUserTaskEntity userTask = new MobileUserTaskEntity();//用户任务
		MobileSubTaskEntity subtask = new MobileSubTaskEntity();//子任务
		MobileTaskEntity task = new MobileTaskEntity();//任务
		Calendar calender = Calendar.getInstance();
        calender.setTime(new Date());
        calender.add(Calendar.MONTH, SysConstant.OFF_LINE_DEADLINE);
		
		int i = mobileUserDao.insert(mue);
		if(i>0){
			String[] courseid = mue.getCourseid().split(",");
			if(!"".equals(mue.getCourseid()) && mue.getCourseid()!=null){
				for(int k=0;k<courseid.length;k++){
					//根据课程ID查询课程
					course.setId(courseid[k]);
					MobileSceCategoryEntity recourse = mobileSceCategoryDao.selectCourseByID(course);
					/**
					 * 课程不是背单词
					 */
					if(courseid[k].equals(SysConstant.TASK_TYPE0)){
						//课程ID
						task.setCourseid(courseid[k]);
						//任务状态(未结束)
						task.setTaskstate(SysConstant.TASK_STATE0);
						List<MobileTaskEntity> taskList = mobileTaskDao.selectTaskList(task);
						if(taskList!=null && taskList.size()>0){
							//如果没有结束,则将此任务保存到用户下
							executeSave(task,userTask,subtask,recourse,courseid[k],calender,mue.getId(),taskList.get(0).getId(),true);
						}else{
							executeSave(task,userTask,subtask,recourse,courseid[k],calender,mue.getId(),null,false);
						}
					}
					/**
					 * 如果用户选择的是背单词,则单独处理
					 */
					if(courseid[k].equals(SysConstant.TASK_TYPE1)){
						handleSweedWord(mue.getId(),task,recourse,courseid[k]);
					}
				}
			}
		}
	}

	@Override
	public MobileUserEntity getUser(MobileUserEntity mue) {
		return mobileUserDao.selectUser(mue);
	}
	
	@SuppressWarnings("unchecked")
	private void executeSave(
			MobileTaskEntity task,
			MobileUserTaskEntity userTask,
			MobileSubTaskEntity subtask,
			MobileSceCategoryEntity recourse,
			String courseid,
			Calendar calender,
			String userid,
			String taskid,
			boolean flag
			){
		if(flag==false){
			//保存任务
			task.setId(UUIDUtil.generateUUID());
			task.setCourseid(courseid);
			task.setTaskname(recourse.getScecatname());
			task.setTaskstate(SysConstant.TASK_STATE0);//未结束
			//在线课程没有开始日期和结束日期(背单词除外)
			if(recourse.getCoursestate().equals(SysConstant.OFF_LINE)){
				task.setStartDate(new Date());//任务开始日期
				task.setEndDate(calender.getTime());//任务结束日期,自动默认2个月
			}
			task.setThesauresType("-1");
			mobileTaskDao.insert(task);
		}
		//保存用户任务
		userTask.setId(UUIDUtil.generateUUID());
		userTask.setUserid(userid);
		userTask.setTaskid(flag==false?task.getId():taskid);
		userTask.setCompletepercent(SysConstant.COMPLETE_PERCENT_INIT);
		mobileUserTaskDao.insert(userTask);
		//保存subtask
//		subtask.setId(UUIDUtil.generateUUID());
//		subtask.setDropletid(recourse.getDropletid());
//		subtask.setDropletconftypeid(recourse.getDropletconftypeid());
//		subtask.setName(recourse.getScecatname());
//		subtask.setTaskid(flag==false?task.getId():taskid);
//		subtask.setUserid(userid);
//		subtask.setIfpass(SysConstant.CARD_STUDY_NOPASS);
		//subtask.setIndex(recourse.getIndex());
		//mobileSubTaskDao.insert(subtask);
		if(
				recourse.getDropletid()!=null && 
				recourse.getDropletconftypeid()!=null &&
				!"".equals(recourse.getDropletid()) &&
				!"".equals(recourse.getDropletconftypeid())
		  ){
			handleSubTask(recourse.getDropletid(),recourse.getDropletid(),recourse.getDropletconftypeid(),flag==false?task.getId():taskid,userid);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void handleSubTask(
			String courseid,
			String dropLetID,
			String dropLetConfTypeID,
			String taskid,
			String userid
			){
		
		MobileSubTaskEntity subtask = new MobileSubTaskEntity();//子任务
		MobileScenListDropLetEntity scenListDropLet = new MobileScenListDropLetEntity();
		MobileCardListDropletEntity cardListDropLet = new MobileCardListDropletEntity();
		
		//根据课程ID判断需要查询哪个表,用以保存到子任务
		if(courseid.equals(SysConstant.DROPLET_ID2)){
			//ScenListDropLet
			scenListDropLet.setDropletid(dropLetID);
			scenListDropLet.setDropletconftypeid(dropLetConfTypeID);
			List<MobileScenListDropLetEntity> scenList = mobileScenListDropLetDao.selectScenList(scenListDropLet);
			
			for(int i=0;i<scenList.size();i++){
				//保存subtask
				subtask.setId(UUIDUtil.generateUUID());
				subtask.setDropletid(scenList.get(i).getDropletid());
				subtask.setDropletconftypeid(scenList.get(i).getDropletconftypeid());
				subtask.setName(scenList.get(i).getScenname());
				subtask.setTaskid(taskid);
				subtask.setUserid(userid);
				subtask.setIndex(scenList.get(i).getIndex());
				subtask.setIfpass(SysConstant.CARD_STUDY_NOPASS);
				mobileSubTaskDao.insert(subtask);
			}
			for(int j=0;j<scenList.size();j++){
				if(scenList.get(j).getReladropletid()!=null && !"".equals(scenList.get(j).getReladropletid())){
					handleSubTask(scenList.get(j).getReladropletid(),scenList.get(j).getReladropletid(),scenList.get(j).getReladropletcontypeid(),taskid,userid);
				}
			}
		}
		if(courseid.equals(SysConstant.DROPLET_ID3)){
			//CardListDroplet
			cardListDropLet.setDropletid(dropLetID);
			cardListDropLet.setDropletconftypeid(dropLetConfTypeID);
			List<MobileCardListDropletEntity> cardList = mobileCardListDropletDao.selectCardListDroplet(cardListDropLet);
			
			for(int i=0;i<cardList.size();i++){
				//保存subtask
				subtask.setId(UUIDUtil.generateUUID());
				subtask.setDropletid(cardList.get(i).getDropletid());
				subtask.setDropletconftypeid(cardList.get(i).getDropletconftypeid());
				subtask.setName(cardList.get(i).getCardname());
				subtask.setTaskid(taskid);
				subtask.setUserid(userid);
				subtask.setIndex(cardList.get(i).getIndex());
				subtask.setIfpass(SysConstant.CARD_STUDY_NOPASS);
				mobileSubTaskDao.insert(subtask);
			}
			for(int j=0;j<cardList.size();j++){
				if(cardList.get(j).getReladropletid()!=null && !"".equals(cardList.get(j).getReladropletid())){
					handleSubTask(cardList.get(j).getReladropletid(),cardList.get(j).getReladropletid(),cardList.get(j).getReladropletcontypeid(),taskid,userid);
				}
			}
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private void handleSweedWord(
			String userid,
			MobileTaskEntity task,
			MobileSceCategoryEntity recourse,
			String courseid
			){
		//保存任务(先根据3个条件(未结束,背单词,所有词库)查询,是否存在此任务,如果存在,则不保存)
		MobileTaskEntity mte = new MobileTaskEntity();
		mte.setCourseid(courseid);
		mte.setTaskstate(SysConstant.TASK_STATE0);
		mte.setThesauresType(SysConstant.THESAURES_TYPE3);
		MobileTaskEntity retask = mobileTaskDao.selectTask(mte);
		if(retask==null){
			task.setId(UUIDUtil.generateUUID());
			task.setCourseid(courseid);
			task.setTaskname(recourse.getScecatname());
			task.setTaskstate(SysConstant.TASK_STATE0);//未结束
			task.setThesauresType(SysConstant.THESAURES_TYPE3);//所有词库
			mobileTaskDao.insert(task);
		}
		
		//保存用户任务
		MobileUserTaskEntity userTask = new MobileUserTaskEntity();
		
		userTask.setUserid(userid);
		userTask.setTaskid(retask!=null?retask.getId():task.getId());
		//根据任务ID和用户ID查询用户任务表,如果有数据则不保存
		MobileUserTaskEntity ut = mobileUserTaskDao.selectOneUserTask(userTask);
		if(ut==null){
			userTask.setId(UUIDUtil.generateUUID());
			userTask.setCompletepercent(SysConstant.COMPLETE_PERCENT_INIT);
			mobileUserTaskDao.insert(userTask);
		}
		
						
		//保存用户单词
		List<MobileWordEntity> list = null;
		MobileWordEntity ae = new MobileWordEntity();
		list = mobileWordDao.queryWordAll(ae);
		
		MobileWordRecordEntity temp = new MobileWordRecordEntity();
		temp.setUserId(userid);
		temp.setThesaurus(SysConstant.THESAURES_TYPE3);//所有词库
		temp.setIsPass(SysConstant.NO_PASS);//未通过考试
		List<MobileWordRecordEntity> tempList = mobileBearWordDao.selectWordRecord(temp);
		if(tempList!=null && tempList.size()>0){
			return;
		}
		
		MobileWordRecordEntity wordRecordEntiy = new MobileWordRecordEntity();
		for (int j = 0; j < list.size(); j++) {
			wordRecordEntiy.setUserId(userid);
			wordRecordEntiy.setWord(list.get(j).getWord());
			wordRecordEntiy.setWordId(list.get(j).getId());
			wordRecordEntiy.setId(UUIDUtil.generateUUID());
			wordRecordEntiy.setThesaurus(SysConstant.THESAURES_TYPE3);//所有词库
						
			mobileBearWordDao.insert(wordRecordEntiy);
		}
		
		
		
	}
}
