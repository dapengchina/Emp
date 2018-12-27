package com.ability.emp.mobile.action;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.constant.SysConstant;
import com.ability.emp.mobile.entity.MobileHitCardEntity;
import com.ability.emp.mobile.entity.MobileTaskEntity;
import com.ability.emp.mobile.entity.MobileUserTaskEntity;
import com.ability.emp.mobile.entity.MobileWordEntity;
import com.ability.emp.mobile.entity.MobileWordRecordEntity;
import com.ability.emp.mobile.server.MobileBearWordService;
import com.ability.emp.mobile.server.MobileHitCardService;
import com.ability.emp.mobile.server.MobileSystemParamService;
import com.ability.emp.mobile.server.MobileTaskService;
import com.ability.emp.mobile.server.MobileUserService;
import com.ability.emp.mobile.server.MobileUserTaskService;
import com.ability.emp.mobile.server.MobileWordService;
import com.ability.emp.util.CalendarCountUtil;
import com.ability.emp.util.ExamUtil;
import com.ability.emp.util.WordExamUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 考试打卡
 * @author Devin
 *
 */
@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/wordexam")
public class MobileWordExamAction {
	
	
	@Resource
	private MobileBearWordService mobileBearWordService;
	
	@Resource
	private MobileUserService mobileUserService;
	
	@Resource
	private MobileTaskService mobileTaskService;
	
	@Resource
	private MobileSystemParamService mobileSystemParamService;
	
	@Resource
	private MobileWordService mobileWordService;
	
	@Resource
	private MobileHitCardService mobileHitCardService;
	
	@Resource
	private MobileUserTaskService mobileUserTaskService;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private Random rd = new Random();
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	
	
	/**
	 * 返回数据
	 * @param id 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/query/{id}")
	@ResponseBody
	public String query(@PathVariable("id") String id) throws Exception{
	   //获取用户任务
	   MobileUserTaskEntity userTask = new MobileUserTaskEntity();
	   userTask.setUserid(id);
	   List<MobileUserTaskEntity> userTaskList = mobileUserTaskService.getUserTask(userTask);
				
	   int taskcount = 0;
	   for(int i=0;i<userTaskList.size();i++){
			//查询用户未完成的任务
		    if(userTaskList.get(i).getCompletepercent().equals(SysConstant.COMPLETE_PERCENT_INIT)){
				MobileTaskEntity task = mobileTaskService.queryById(userTaskList.get(i).getTaskid());
				/**
				 * 如果是背单词任务,则计算每天背单词量
				 */
				if(task.getCourseid().equals(SysConstant.TASK_TYPE1)){
					//如果是用户自己在手机端选择的背单词,则调用默认每天任务量
					if(task.getStartDate()==null && task.getEndDate()==null){
						taskcount = SysConstant.DEFAULT_WORD_COUNT;
					    break;
					}
					//如果是导师在后台给用户指派的背单词,则根据开始,结束日期计算每天任务量
					if(task.getStartDate()!=null && task.getEndDate()!=null){
						taskcount = calculateTaskCount(sdf.format(task.getStartDate()),sdf.format(task.getEndDate()),task.getThesauresType());
					    break;
					}
				}
			}
		}
		
		MobileHitCardEntity mhce = new MobileHitCardEntity();
		mhce.setUserId(id);
		mhce.setStringDate(sdf.format(new Date()));
		List<MobileHitCardEntity> ifhit = mobileHitCardService.queryByUserID(mhce);
		if(ifhit!=null && ifhit.size()>=1){
			    int random_temp = 0;
			    MobileWordRecordEntity mwre = new MobileWordRecordEntity();
			    mwre.setStringPassDate(sdf.format(new Date()));;//当天考试通过日期
			    mwre.setIsPass(SysConstant.PASS);//考试通过
			    mwre.setUserId(id);//用户ID
			    mwre.setCount(taskcount);//任务量
				
				List<MobileWordRecordEntity> list = mobileBearWordService.query(mwre);
				List<WordExamUtil> reslist = new ArrayList<WordExamUtil>();
				for(int i=0;i<list.size();i++){
					//生成0到4之间的随机数
					random_temp = rd.nextInt(4);
					MobileWordEntity mwe = mobileWordService.queryWordById(list.get(i).getWordId());
					WordExamUtil weu = new WordExamUtil();
					String[] temp = new String[4];
					weu.setWordid(list.get(i).getWordId());
					weu.setWord(list.get(i).getWord());
					weu.setId(list.get(i).getId());
					weu.setTaskid(list.get(i).getTaskid());
					if(mwe!=null){
						weu.setPronounce(mwe.getSymbol());
						
						if(random_temp==0){
							temp[random_temp] = mwe.getInterpretation();
							temp[1] = mwe.getErrInterpretation1();
							temp[2] = mwe.getErrInterpretation2();
							temp[3] = mwe.getErrInterpretation3();
						}
						if(random_temp==1){
							temp[random_temp] = mwe.getInterpretation();
							temp[0] = mwe.getErrInterpretation1();
							temp[2] = mwe.getErrInterpretation2();
							temp[3] = mwe.getErrInterpretation3();
						}
						if(random_temp==2){
							temp[random_temp] = mwe.getInterpretation();
							temp[0] = mwe.getErrInterpretation1();
							temp[1] = mwe.getErrInterpretation2();
							temp[3] = mwe.getErrInterpretation3();
						}
						if(random_temp==3){
							temp[random_temp] = mwe.getInterpretation();
							temp[0] = mwe.getErrInterpretation1();
							temp[1] = mwe.getErrInterpretation2();
							temp[2] = mwe.getErrInterpretation3();
						}
						
						weu.setOptions(temp);
						weu.setCorrect(String.valueOf(random_temp));
					}
					reslist.add(weu);
				}
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("total",reslist.size());
				map.put("rows", reslist);
				return objectMapper.writeValueAsString(map);
		}else{
			int random_temp = 0;
			//当天未打卡
			/***
			 * 获取选中,考试未通过的单词
			 */
			MobileWordRecordEntity mwre = new MobileWordRecordEntity();
			mwre.setIsSel(SysConstant.CHECKED);//选中
			mwre.setIsPass(SysConstant.NO_PASS);//考试未通过
			mwre.setUserId(id);//用户ID
			mwre.setCount(taskcount);//任务量
			
			List<MobileWordRecordEntity> list = mobileBearWordService.query(mwre);
			List<WordExamUtil> reslist = new ArrayList<WordExamUtil>();
			for(int i=0;i<list.size();i++){
				//生成0到4之间的随机数
				random_temp = rd.nextInt(4);
				MobileWordEntity mwe = mobileWordService.queryWordById(list.get(i).getWordId());
				WordExamUtil weu = new WordExamUtil();
				String[] temp = new String[4];
				weu.setWordid(list.get(i).getWordId());
				weu.setWord(list.get(i).getWord());
				weu.setId(list.get(i).getId());
				weu.setTaskid(list.get(i).getTaskid());
				if(mwe!=null){
					weu.setPronounce(mwe.getSymbol());
					
					if(random_temp==0){
						temp[random_temp] = mwe.getInterpretation();
						temp[1] = mwe.getErrInterpretation1();
						temp[2] = mwe.getErrInterpretation2();
						temp[3] = mwe.getErrInterpretation3();
					}
					if(random_temp==1){
						temp[random_temp] = mwe.getInterpretation();
						temp[0] = mwe.getErrInterpretation1();
						temp[2] = mwe.getErrInterpretation2();
						temp[3] = mwe.getErrInterpretation3();
					}
					if(random_temp==2){
						temp[random_temp] = mwe.getInterpretation();
						temp[0] = mwe.getErrInterpretation1();
						temp[1] = mwe.getErrInterpretation2();
						temp[3] = mwe.getErrInterpretation3();
					}
					if(random_temp==3){
						temp[random_temp] = mwe.getInterpretation();
						temp[0] = mwe.getErrInterpretation1();
						temp[1] = mwe.getErrInterpretation2();
						temp[2] = mwe.getErrInterpretation3();
					}
					
					weu.setOptions(temp);
					weu.setCorrect(String.valueOf(random_temp));
				}
				reslist.add(weu);
			}
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("total",reslist.size());
			map.put("rows", reslist);
			return objectMapper.writeValueAsString(map);
		}
		
		
		
		
	}
	
	
	/**
	 * 将考试通过的单词考试状态修改为:考试通过
	 * @param ids 用户单词记录表主键
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value="/{examPass}", method = RequestMethod.POST)
	@ResponseBody
	public String examPass(@RequestBody ExamUtil examUtil) throws JsonProcessingException{
//		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
//		MobileHitCardEntity mhce = new MobileHitCardEntity();
//		mhce.setUserId(id);
//		mhce.setStringDate(sf.format(new Date()));
//		List<MobileHitCardEntity> ifhit = mobileHitCardService.queryByUserID(mhce);
		
		
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String[] temp = null;
		if(examUtil.getIds()!=null && !"".equals(examUtil.getIds())){
			temp = examUtil.getIds().split(",");
		}
		if(temp!=null){
			MobileWordRecordEntity mwre = new MobileWordRecordEntity();
			for(int i=0;i<temp.length;i++){
				mwre.setId(temp[i]);
				mwre.setIsPass(SysConstant.PASS);
				mwre.setExamPassDate(Timestamp.valueOf(sf.format(new Date())));
				//mwre.setIsFail(SysConstant.RIGHT);
				mobileBearWordService.update(mwre);
			}
		}
		
		return "";
	}
	
	/**
	 * 判断用户是否完成全部单词的考试
	 * @param id 用户ID
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping("/isAllPass/{id}")
	@ResponseBody
	public boolean isAllPass(@PathVariable("id") String id) throws JsonProcessingException{
			MobileWordRecordEntity mwre = new MobileWordRecordEntity();
			mwre.setUserId(id);
			mwre.setIsPass(SysConstant.NO_PASS);
			List<MobileWordRecordEntity> list = mobileBearWordService.queryAll(mwre);
			if(list!=null && list.size()==0){
				return false;
			}
			if(list==null){
				return false;
			}
		    return true;
	}
	
	@RequestMapping("/delEasyErrorWord/{id}")
	@ResponseBody
	public boolean delEasyErrorWord(@PathVariable("id") String id) throws JsonProcessingException{
		MobileWordRecordEntity mwre = new MobileWordRecordEntity();
		mwre.setId(id);
		mwre.setIsFail(SysConstant.RIGHT);
		int i = mobileBearWordService.update(mwre);
		if(i>0){
			return true;
		}else{
			return false;
		}
	}
	
	private int calculateTaskCount(String startDate,String endDate,String thesaure) throws Exception {
		try{
			//根据词库ID获取词库数量
			MobileWordEntity m = new MobileWordEntity();
			m.setThesaurusType(thesaure);
			List<MobileWordEntity> list = mobileWordService.queryWordAll(m);
			if(list==null || list.size()==0){
				return 0;
			}
			//计算2个日期相差的天数
			int x = CalendarCountUtil.getDays(startDate, endDate);
			if(x==0){
				return 0;
			}
			
			//计算每天的任务量
			int days = x+1;
			double c = list.size()/days;
			if(c<0.5){
				return 0;
			}
			
			BigDecimal b = new BigDecimal(c);
			int taskcount = b.setScale(2,RoundingMode.HALF_UP).intValue();
			return taskcount;
		}catch(Exception e){
			return 0;
		}
	}
	
	
}
