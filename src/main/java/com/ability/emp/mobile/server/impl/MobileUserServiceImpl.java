package com.ability.emp.mobile.server.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ability.emp.constant.SysConstant;
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
import com.ability.emp.mobile.entity.MobileCardListDropletEntity;
import com.ability.emp.mobile.entity.MobileChoiceAudioDropletEntity;
import com.ability.emp.mobile.entity.MobileChoiceTextDropLetEntity;
import com.ability.emp.mobile.entity.MobileDictationDropLetEntity;
import com.ability.emp.mobile.entity.MobileFillBlankDropLetEntity;
import com.ability.emp.mobile.entity.MobileMatchDropLetEntity;
import com.ability.emp.mobile.entity.MobileReadSpeakDropLetEntity;
import com.ability.emp.mobile.entity.MobileSceCategoryEntity;
import com.ability.emp.mobile.entity.MobileScenListDropLetEntity;
import com.ability.emp.mobile.entity.MobileScenarioDropLetEntity;
import com.ability.emp.mobile.entity.MobileSortDropLetEntity;
import com.ability.emp.mobile.entity.MobileSubTaskEntity;
import com.ability.emp.mobile.entity.MobileTaskEntity;
import com.ability.emp.mobile.entity.MobileUserEntity;
import com.ability.emp.mobile.entity.MobileUserTaskEntity;
import com.ability.emp.mobile.entity.MobileVideoDropLetEntity;
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
		
		int i = mobileUserDao.insert(mue);
		if(i>0){
			String[] courseid = mue.getCourseid().split(",");
			for(int k=0;k<courseid.length;k++){
				//根据课程ID查询课程
				course.setId(courseid[k]);
				MobileSceCategoryEntity recourse = mobileSceCategoryDao.selectCourseByID(course);
				//根据课程ID查询任务状态
				task.setCourseid(courseid[k]);
				List<MobileTaskEntity> taskList = mobileTaskDao.selectTaskList(task);
				if(taskList!=null && taskList.size()>0){
					
				}else{
					//保存任务
					task.setId(UUIDUtil.generateUUID());
					task.setCourseid(courseid[k]);
					task.setTaskname(recourse.getScecatname());
					task.setTaskstate(SysConstant.TASK_STATE0);//未结束
					//task.setThesaures_Type(thesaures_Type);
					mobileTaskDao.insert(task);
					//保存用户任务
					userTask.setId(UUIDUtil.generateUUID());
					userTask.setUserid(mue.getId());
					userTask.setTaskid(task.getId());
					userTask.setCompletepercent(SysConstant.COMPLETE_PERCENT_INIT);
					mobileUserTaskDao.insert(userTask);
					//保存subtask
					subtask.setId(UUIDUtil.generateUUID());
					subtask.setDropletid(recourse.getDropletid());
					subtask.setDropletconftypeid(recourse.getDropletconftypeid());
					subtask.setName(recourse.getScecatname());
					subtask.setTaskid(task.getId());
					mobileSubTaskDao.insert(subtask);
					handleSubTask(courseid[k],recourse,task.getId());
				}
			}
		}else{
			
		}
	}

	@Override
	public MobileUserEntity getUser(MobileUserEntity mue) {
		return mobileUserDao.selectUser(mue);
	}
	
	@SuppressWarnings("unchecked")
	private void handleSubTask(String courseid,MobileSceCategoryEntity recourse,String taskid){
		MobileSubTaskEntity subtask = new MobileSubTaskEntity();//子任务
		MobileSceCategoryEntity course = new MobileSceCategoryEntity();//课程
		MobileScenListDropLetEntity scenListDropLet = new MobileScenListDropLetEntity();
		MobileCardListDropletEntity cardListDropLet = new MobileCardListDropletEntity();
		MobileScenarioDropLetEntity scenarioDropLet = new MobileScenarioDropLetEntity();
		MobileChoiceTextDropLetEntity choiceTextDropLet = new MobileChoiceTextDropLetEntity();
		MobileFillBlankDropLetEntity fillBlankDropLet = new MobileFillBlankDropLetEntity();
		MobileChoiceAudioDropletEntity choiceAudioDropLet = new MobileChoiceAudioDropletEntity();
		MobileReadSpeakDropLetEntity readSpeakDropLet = new MobileReadSpeakDropLetEntity();
		MobileSortDropLetEntity sortDropLet = new MobileSortDropLetEntity();
		MobileMatchDropLetEntity matchDropLet = new MobileMatchDropLetEntity();
		MobileVideoDropLetEntity videoDropLet = new MobileVideoDropLetEntity();
		MobileDictationDropLetEntity dictationDropLet = new MobileDictationDropLetEntity();
		//根据课程ID判断需要查询哪个表,用以保存到子任务
		if(courseid.equals(SysConstant.DROPLET_ID2)){
			//ScenListDropLet
			scenListDropLet.setDropletid(recourse.getDropletid());
			scenListDropLet.setDropletconftypeid(recourse.getDropletconftypeid());
			List<MobileScenListDropLetEntity> scenList = mobileScenListDropLetDao.selectScenList(scenListDropLet);
			//保存subtask
			subtask.setId(UUIDUtil.generateUUID());
			subtask.setDropletid(recourse.getDropletid());
			subtask.setDropletconftypeid(recourse.getDropletconftypeid());
			subtask.setName(recourse.getScecatname());
			subtask.setTaskid(taskid);
			mobileSubTaskDao.insert(subtask);
			for(int i=0;i<scenList.size();i++){
				//根据课程ID查询课程
				course.setId(scenList.get(i).getDropletid());
				MobileSceCategoryEntity retcourse = mobileSceCategoryDao.selectCourseByID(course);
				handleSubTask(scenList.get(i).getDropletid(),retcourse,taskid);
			}
		}
		if(courseid.equals(SysConstant.DROPLET_ID3)){
			//CardListDroplet
			cardListDropLet.setDropletid(recourse.getDropletid());
			cardListDropLet.setDropletconftypeid(recourse.getDropletconftypeid());
			List<MobileCardListDropletEntity> cardList = mobileCardListDropletDao.selectCardListDroplet(cardListDropLet);
			//保存subtask
			subtask.setId(UUIDUtil.generateUUID());
			subtask.setDropletid(recourse.getDropletid());
			subtask.setDropletconftypeid(recourse.getDropletconftypeid());
			subtask.setName(recourse.getScecatname());
			subtask.setTaskid(taskid);
			mobileSubTaskDao.insert(subtask);
			for(int i=0;i<cardList.size();i++){
				//根据课程ID查询课程
				course.setId(cardList.get(i).getDropletid());
				MobileSceCategoryEntity retcourse = mobileSceCategoryDao.selectCourseByID(course);
				handleSubTask(cardList.get(i).getDropletid(),retcourse,taskid);
			}
		}
		if(courseid.equals(SysConstant.DROPLET_ID4)){
			//ScenarioDropLet
			scenarioDropLet.setDropletid(recourse.getDropletid());
			scenarioDropLet.setDropletconftypeid(recourse.getDropletconftypeid());
			List<MobileScenarioDropLetEntity> scenario = mobileScenarioDropLetDao.selectScenarioData(scenarioDropLet);
			//保存subtask
			subtask.setId(UUIDUtil.generateUUID());
			subtask.setDropletid(recourse.getDropletid());
			subtask.setDropletconftypeid(recourse.getDropletconftypeid());
			subtask.setName(recourse.getScecatname());
			subtask.setTaskid(taskid);
			mobileSubTaskDao.insert(subtask);
			for(int i=0;i<scenario.size();i++){
				//根据课程ID查询课程
				course.setId(scenario.get(i).getDropletid());
				MobileSceCategoryEntity retcourse = mobileSceCategoryDao.selectCourseByID(course);
				handleSubTask(scenario.get(i).getDropletid(),retcourse,taskid);
			}
		}
		if(courseid.equals(SysConstant.DROPLET_ID5)){
			//TextChoiceDropLet
			choiceTextDropLet.setDropletid(recourse.getDropletid());
			choiceTextDropLet.setDropletconftypeid(recourse.getDropletconftypeid());
			MobileChoiceTextDropLetEntity choiceText = mobileChoiceTextDropLetDao.selectChoiceDropLetData(choiceTextDropLet);
			//保存subtask
			subtask.setId(UUIDUtil.generateUUID());
			subtask.setDropletid(recourse.getDropletid());
			subtask.setDropletconftypeid(recourse.getDropletconftypeid());
			subtask.setName(recourse.getScecatname());
			subtask.setTaskid(taskid);
			mobileSubTaskDao.insert(subtask);
			//根据课程ID查询课程
			course.setId(choiceText.getDropletid());
			MobileSceCategoryEntity retcourse = mobileSceCategoryDao.selectCourseByID(course);
			handleSubTask(choiceText.getDropletid(),retcourse,taskid);
		}
		if(courseid.equals(SysConstant.DROPLET_ID6)){
			//FillBlankDropLet
			fillBlankDropLet.setDropletid(recourse.getDropletid());
			fillBlankDropLet.setDropletconftypeid(recourse.getDropletconftypeid());
			MobileFillBlankDropLetEntity fillBlank = mobileFillBlankDropLetDao.selectFillBlankData(fillBlankDropLet);
			//保存subtask
			subtask.setId(UUIDUtil.generateUUID());
			subtask.setDropletid(recourse.getDropletid());
			subtask.setDropletconftypeid(recourse.getDropletconftypeid());
			subtask.setName(recourse.getScecatname());
			subtask.setTaskid(taskid);
			mobileSubTaskDao.insert(subtask);
			//根据课程ID查询课程
			course.setId(fillBlank.getDropletid());
			MobileSceCategoryEntity retcourse = mobileSceCategoryDao.selectCourseByID(course);
			handleSubTask(fillBlank.getDropletid(),retcourse,taskid);
		}
		if(courseid.equals(SysConstant.DROPLET_ID7)){
			//SuccessDropLet
		}
		if(courseid.equals(SysConstant.DROPLET_ID8)){
			//AudioChoiceDropLet
			choiceAudioDropLet.setDropletid(recourse.getDropletid());
			choiceAudioDropLet.setDropletconftypeid(recourse.getDropletconftypeid());
			MobileChoiceAudioDropletEntity choiceAudio = mobileChoiceAudioDropletDao.selectChoiceAudioDropletData(choiceAudioDropLet);
			//保存subtask
			subtask.setId(UUIDUtil.generateUUID());
			subtask.setDropletid(recourse.getDropletid());
			subtask.setDropletconftypeid(recourse.getDropletconftypeid());
			subtask.setName(recourse.getScecatname());
			subtask.setTaskid(taskid);
			mobileSubTaskDao.insert(subtask);
			//根据课程ID查询课程
			course.setId(choiceAudio.getDropletid());
			MobileSceCategoryEntity retcourse = mobileSceCategoryDao.selectCourseByID(course);
			handleSubTask(choiceAudio.getDropletid(),retcourse,taskid);
		}
		if(courseid.equals(SysConstant.DROPLET_ID9)){
			//ReadSpeakDropLet
			readSpeakDropLet.setDropletid(recourse.getDropletid());
			readSpeakDropLet.setDropletconftypeid(recourse.getDropletconftypeid());
			MobileReadSpeakDropLetEntity readSpeak = mobileReadSpeakDropLetDao.selectReadSpeakData(readSpeakDropLet);
			//保存subtask
			subtask.setId(UUIDUtil.generateUUID());
			subtask.setDropletid(recourse.getDropletid());
			subtask.setDropletconftypeid(recourse.getDropletconftypeid());
			subtask.setName(recourse.getScecatname());
			subtask.setTaskid(taskid);
			mobileSubTaskDao.insert(subtask);
			//根据课程ID查询课程
			course.setId(readSpeak.getDropletid());
			MobileSceCategoryEntity retcourse = mobileSceCategoryDao.selectCourseByID(course);
			handleSubTask(readSpeak.getDropletid(),retcourse,taskid);
		}
		if(courseid.equals(SysConstant.DROPLET_ID10)){
			//SortDropLet
			sortDropLet.setDropletid(recourse.getDropletid());
			sortDropLet.setDropletconftypeid(recourse.getDropletconftypeid());
			List<MobileSortDropLetEntity> sortDropList = mobileSortDropLetDao.selectSortData(sortDropLet);
			//保存subtask
			subtask.setId(UUIDUtil.generateUUID());
			subtask.setDropletid(recourse.getDropletid());
			subtask.setDropletconftypeid(recourse.getDropletconftypeid());
			subtask.setName(recourse.getScecatname());
			subtask.setTaskid(taskid);
			mobileSubTaskDao.insert(subtask);
			for(int i=0;i<sortDropList.size();i++){
				//根据课程ID查询课程
				course.setId(sortDropList.get(i).getDropletid());
				MobileSceCategoryEntity retcourse = mobileSceCategoryDao.selectCourseByID(course);
				handleSubTask(sortDropList.get(i).getDropletid(),retcourse,taskid);
			}
		}
		if(courseid.equals(SysConstant.DROPLET_ID11)){
			//MatchDropLet
			matchDropLet.setDropletid(recourse.getDropletid());
			matchDropLet.setDropletconftypeid(recourse.getDropletconftypeid());
			MobileMatchDropLetEntity match = mobileMatchDropLetDao.selectMatchData(matchDropLet);
			//保存subtask
			subtask.setId(UUIDUtil.generateUUID());
			subtask.setDropletid(recourse.getDropletid());
			subtask.setDropletconftypeid(recourse.getDropletconftypeid());
			subtask.setName(recourse.getScecatname());
			subtask.setTaskid(taskid);
			mobileSubTaskDao.insert(subtask);
			//根据课程ID查询课程
			course.setId(match.getDropletid());
			MobileSceCategoryEntity retcourse = mobileSceCategoryDao.selectCourseByID(course);
			handleSubTask(match.getDropletid(),retcourse,taskid);
		}
		if(courseid.equals(SysConstant.DROPLET_ID12)){
			//VideoDropLet
			videoDropLet.setDropletid(recourse.getDropletid());
			videoDropLet.setDropletconftypeid(recourse.getDropletconftypeid());
			MobileVideoDropLetEntity video = mobileVideoDropLetDao.selectVideoData(videoDropLet);
			//保存subtask
			subtask.setId(UUIDUtil.generateUUID());
			subtask.setDropletid(recourse.getDropletid());
			subtask.setDropletconftypeid(recourse.getDropletconftypeid());
			subtask.setName(recourse.getScecatname());
			subtask.setTaskid(taskid);
			mobileSubTaskDao.insert(subtask);
			//根据课程ID查询课程
			course.setId(video.getDropletid());
			MobileSceCategoryEntity retcourse = mobileSceCategoryDao.selectCourseByID(course);
			handleSubTask(video.getDropletid(),retcourse,taskid);
		}
		if(courseid.equals(SysConstant.DROPLET_ID13)){
			//DictationDropLet
			dictationDropLet.setDropletid(recourse.getDropletid());
			dictationDropLet.setDropletconftypeid(recourse.getDropletconftypeid());
			MobileDictationDropLetEntity dictation = mobileDictationDropLetDao.selectDictationData(dictationDropLet);
			//保存subtask
			subtask.setId(UUIDUtil.generateUUID());
			subtask.setDropletid(recourse.getDropletid());
			subtask.setDropletconftypeid(recourse.getDropletconftypeid());
			subtask.setName(recourse.getScecatname());
			subtask.setTaskid(taskid);
			mobileSubTaskDao.insert(subtask);
			//根据课程ID查询课程
			course.setId(dictation.getDropletid());
			MobileSceCategoryEntity retcourse = mobileSceCategoryDao.selectCourseByID(course);
			handleSubTask(dictation.getDropletid(),retcourse,taskid);
		}
	}
	
}
