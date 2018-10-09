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
					handleSubTask(recourse.getDropletid(),recourse.getDropletid(),recourse.getDropletconftypeid(),task.getId());
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
	private void handleSubTask(String courseid,String dropLetID,String dropLetConfTypeID,String taskid){
		
		MobileSubTaskEntity subtask = new MobileSubTaskEntity();//子任务
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
				mobileSubTaskDao.insert(subtask);
				
			}
			for(int j=0;j<scenList.size();j++){
				if(scenList.get(j).getReladropletid()!=null && !"".equals(scenList.get(j).getReladropletid())){
					handleSubTask(scenList.get(j).getReladropletid(),scenList.get(j).getReladropletid(),scenList.get(j).getReladropletcontypeid(),taskid);
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
				mobileSubTaskDao.insert(subtask);
			}
			for(int j=0;j<cardList.size();j++){
				if(cardList.get(j).getReladropletid()!=null && !"".equals(cardList.get(j).getReladropletid())){
					handleSubTask(cardList.get(j).getReladropletid(),cardList.get(j).getReladropletid(),cardList.get(j).getReladropletcontypeid(),taskid);
				}
			}
		}
		if(courseid.equals(SysConstant.DROPLET_ID4)){
			//ScenarioDropLet
			scenarioDropLet.setDropletid(dropLetID);
			scenarioDropLet.setDropletconftypeid(dropLetConfTypeID);
			List<MobileScenarioDropLetEntity> scenario = mobileScenarioDropLetDao.selectScenarioData(scenarioDropLet);
			
			for(int i=0;i<scenario.size();i++){
				//保存subtask
				subtask.setId(UUIDUtil.generateUUID());
				subtask.setDropletid(scenario.get(i).getDropletid());
				subtask.setDropletconftypeid(scenario.get(i).getDropletconftypeid());
				subtask.setTaskid(taskid);
				mobileSubTaskDao.insert(subtask);
			}
			for(int j=0;j<scenario.size();j++){
				if(scenario.get(j).getReladropletid()!=null && !"".equals(scenario.get(j).getReladropletid())){
					handleSubTask(scenario.get(j).getReladropletid(),scenario.get(j).getReladropletid(),scenario.get(j).getReladropletconftypeid(),taskid);
				}
			}
		}
		if(courseid.equals(SysConstant.DROPLET_ID5)){
			//TextChoiceDropLet
			choiceTextDropLet.setDropletid(dropLetID);
			choiceTextDropLet.setDropletconftypeid(dropLetConfTypeID);
			MobileChoiceTextDropLetEntity choiceText = mobileChoiceTextDropLetDao.selectChoiceDropLetData(choiceTextDropLet);
			
			//保存subtask
			subtask.setId(UUIDUtil.generateUUID());
			subtask.setDropletid(choiceText.getDropletid());
			subtask.setDropletconftypeid(choiceText.getDropletconftypeid());
			subtask.setTaskid(taskid);
			mobileSubTaskDao.insert(subtask);
			handleSubTask(choiceText.getReladropletid(),choiceText.getReladropletid(),choiceText.getReladropletconftypeid(),taskid);
		}
		if(courseid.equals(SysConstant.DROPLET_ID6)){
			//FillBlankDropLet
			fillBlankDropLet.setDropletid(dropLetID);
			fillBlankDropLet.setDropletconftypeid(dropLetConfTypeID);
			MobileFillBlankDropLetEntity fillBlank = mobileFillBlankDropLetDao.selectFillBlankData(fillBlankDropLet);
			
			//保存subtask
			subtask.setId(UUIDUtil.generateUUID());
			subtask.setDropletid(fillBlank.getDropletid());
			subtask.setDropletconftypeid(fillBlank.getDropletconftypeid());
			subtask.setTaskid(taskid);
			mobileSubTaskDao.insert(subtask);
			handleSubTask(fillBlank.getReladropletid(),fillBlank.getReladropletid(),fillBlank.getReladropletconftypeid(),taskid);
		}
		if(courseid.equals(SysConstant.DROPLET_ID7)){
			//SuccessDropLet
		}
		if(courseid.equals(SysConstant.DROPLET_ID8)){
			//AudioChoiceDropLet
			choiceAudioDropLet.setDropletid(dropLetID);
			choiceAudioDropLet.setDropletconftypeid(dropLetConfTypeID);
			MobileChoiceAudioDropletEntity choiceAudio = mobileChoiceAudioDropletDao.selectChoiceAudioDropletData(choiceAudioDropLet);
			
			//保存subtask
			subtask.setId(UUIDUtil.generateUUID());
			subtask.setDropletid(choiceAudio.getDropletid());
			subtask.setDropletconftypeid(choiceAudio.getDropletconftypeid());
			subtask.setTaskid(taskid);
			mobileSubTaskDao.insert(subtask);
			
			handleSubTask(choiceAudio.getReladropletid(),choiceAudio.getReladropletid(),choiceAudio.getReladropletconftypeid(),taskid);
		}
		if(courseid.equals(SysConstant.DROPLET_ID9)){
			//ReadSpeakDropLet
			readSpeakDropLet.setDropletid(dropLetID);
			readSpeakDropLet.setDropletconftypeid(dropLetConfTypeID);
			MobileReadSpeakDropLetEntity readSpeak = mobileReadSpeakDropLetDao.selectReadSpeakData(readSpeakDropLet);
			
			//保存subtask
			subtask.setId(UUIDUtil.generateUUID());
			subtask.setDropletid(readSpeak.getDropletid());
			subtask.setDropletconftypeid(readSpeak.getDropletconftypeid());
			subtask.setTaskid(taskid);
			mobileSubTaskDao.insert(subtask);
			
			handleSubTask(readSpeak.getReladropletid(),readSpeak.getReladropletid(),readSpeak.getReladropletconftypeid(),taskid);
		}
		if(courseid.equals(SysConstant.DROPLET_ID10)){
			//SortDropLet
			sortDropLet.setDropletid(dropLetID);
			sortDropLet.setDropletconftypeid(dropLetConfTypeID);
			List<MobileSortDropLetEntity> sortDropList = mobileSortDropLetDao.selectSortData(sortDropLet);
			
			for(int i=0;i<sortDropList.size();i++){
				//保存subtask
				subtask.setId(UUIDUtil.generateUUID());
				subtask.setDropletid(sortDropList.get(i).getDropletid());
				subtask.setDropletconftypeid(sortDropList.get(i).getDropletconftypeid());
				subtask.setTaskid(taskid);
				mobileSubTaskDao.insert(subtask);
			}
			for(int j=0;j<sortDropList.size();j++){
				if(sortDropList.get(j).getReladropletid()!=null && !"".equals(sortDropList.get(j).getReladropletid())){
					handleSubTask(sortDropList.get(j).getReladropletid(),sortDropList.get(j).getReladropletid(),sortDropList.get(j).getReladropletconftypeid(),taskid);
				}
			}
		}
		if(courseid.equals(SysConstant.DROPLET_ID11)){
			//MatchDropLet
			matchDropLet.setDropletid(dropLetID);
			matchDropLet.setDropletconftypeid(dropLetConfTypeID);
			MobileMatchDropLetEntity match = mobileMatchDropLetDao.selectMatchData(matchDropLet);
			
			//保存subtask
			subtask.setId(UUIDUtil.generateUUID());
			subtask.setDropletid(match.getDropletid());
			subtask.setDropletconftypeid(match.getDropletconftypeid());
			subtask.setTaskid(taskid);
			mobileSubTaskDao.insert(subtask);
			
			handleSubTask(match.getReladropletid(),match.getReladropletid(),match.getReladropletconftypeid(),taskid);
		}
		if(courseid.equals(SysConstant.DROPLET_ID12)){
			//VideoDropLet
			videoDropLet.setDropletid(dropLetID);
			videoDropLet.setDropletconftypeid(dropLetConfTypeID);
			MobileVideoDropLetEntity video = mobileVideoDropLetDao.selectVideoData(videoDropLet);
			
			//保存subtask
			subtask.setId(UUIDUtil.generateUUID());
			subtask.setDropletid(video.getDropletid());
			subtask.setDropletconftypeid(video.getDropletconftypeid());
			subtask.setTaskid(taskid);
			mobileSubTaskDao.insert(subtask);
			
			handleSubTask(video.getReladropletid(),video.getReladropletid(),video.getDropletconftypeid(),taskid);
		}
		if(courseid.equals(SysConstant.DROPLET_ID13)){
			//DictationDropLet
			dictationDropLet.setDropletid(dropLetID);
			dictationDropLet.setDropletconftypeid(dropLetConfTypeID);
			MobileDictationDropLetEntity dictation = mobileDictationDropLetDao.selectDictationData(dictationDropLet);
			
			//保存subtask
			subtask.setId(UUIDUtil.generateUUID());
			subtask.setDropletid(dictation.getDropletid());
			subtask.setDropletconftypeid(dictation.getDropletconftypeid());
			subtask.setTaskid(taskid);
			mobileSubTaskDao.insert(subtask);
			
			handleSubTask(dictation.getReladropletid(),dictation.getReladropletid(),dictation.getDropletconftypeid(),taskid);
		}
	}
	
}
