package com.ability.emp.mobile.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.constant.SysConstant;
import com.ability.emp.mobile.entity.MobileHitCardEntity;
import com.ability.emp.mobile.entity.MobileTaskEntity;
import com.ability.emp.mobile.entity.MobileUserTaskEntity;
import com.ability.emp.mobile.entity.MobileWordRecordEntity;
import com.ability.emp.mobile.server.MobileBearWordService;
import com.ability.emp.mobile.server.MobileHitCardService;
import com.ability.emp.mobile.server.MobileTaskService;
import com.ability.emp.mobile.server.MobileUserService;
import com.ability.emp.mobile.server.MobileUserTaskService;
import com.fasterxml.jackson.databind.ObjectMapper;



@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/checkTask")
public class MobileCheckTaskExpireAction {
	
	
	
	@Resource
	private MobileUserService mobileUserService;
	
	@Resource
	private MobileTaskService mobileTaskService;
	
	@Resource
	private MobileUserTaskService mobileUserTaskService;
	
	@Resource
	private MobileBearWordService mobileBearWordService;
	
	@Resource
	private MobileHitCardService mobileHitCardService;
	
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	
	
	/**
	 * 返回数据
	 * @param id 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/whetherExpire/{id}")
	@ResponseBody
	public String query(@PathVariable("id") String id) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//获取用户任务
		MobileUserTaskEntity userTask = new MobileUserTaskEntity();
		userTask.setUserid(id);
		List<MobileUserTaskEntity> userTaskList = mobileUserTaskService.getUserTask(userTask);
				
		
		for(int i=0;i<userTaskList.size();i++){
			//查询用户未完成的任务
			if(userTaskList.get(i).getCompletepercent().equals(SysConstant.COMPLETE_PERCENT_INIT)){
				MobileTaskEntity task = mobileTaskService.queryById(userTaskList.get(i).getTaskid());
				/**
				 * 如果是背单词任务
				 */
				if(task.getCourseid().equals(SysConstant.TASK_TYPE1)){
					//如果是用户自己在手机端选择的背单词,则不返回结束日期
					if(task.getEndDate()==null){
						map.put("flag", true);
						break;
					}
					//如果是导师在后台给用户指派的背单词,则返回结束日期
					if(task.getEndDate()!=null){
						map.put("endDate", sdf.format(task.getEndDate()));
						map.put("flag", false);
						break;
					}
				}
			}
		}
		
		//获取用户完成单词数量
		MobileWordRecordEntity mwre = new MobileWordRecordEntity();
		mwre.setUserId(id);//用户ID
		mwre.setIsPass(SysConstant.PASS);//考试通过
		int count = mobileBearWordService.queryDoneCount(mwre);
		map.put("totaldone", count);
		
		//获取用户打卡状态
		MobileHitCardEntity mhce = new MobileHitCardEntity();
		mhce.setUserId(id);
		mhce.setStringDate(sdf.format(new Date()));
		mhce.setState(SysConstant.TASK_STATE0);
		List<MobileHitCardEntity> list = mobileHitCardService.queryByUserID(mhce);
		if(list!=null && list.size()>=1){
			map.put("taskstatus", 1);
		}else{
			map.put("taskstatus", 0);
		}
		
		
		return objectMapper.writeValueAsString(map);
	}

}
