package com.ability.emp.mobile.action;

import java.util.ArrayList;
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
import com.ability.emp.mobile.entity.MobileSystemParamEntity;
import com.ability.emp.mobile.entity.MobileTaskEntity;
import com.ability.emp.mobile.entity.MobileUserEntity;
import com.ability.emp.mobile.entity.MobileWordEntity;
import com.ability.emp.mobile.entity.MobileWordRecordEntity;
import com.ability.emp.mobile.server.MobileBearWordService;
import com.ability.emp.mobile.server.MobileHitCardService;
import com.ability.emp.mobile.server.MobileSystemParamService;
import com.ability.emp.mobile.server.MobileTaskService;
import com.ability.emp.mobile.server.MobileUserService;
import com.ability.emp.mobile.server.MobileWordService;
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
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	
	
	/**
	 * 返回数据
	 * @param id 用户ID
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping("/query/{id}")
	@ResponseBody
	public String query(@PathVariable("id") String id) throws JsonProcessingException{
		//获取用户任务ID
		MobileUserEntity mue = mobileUserService.queryById(id);
		//获取参数ID
		MobileTaskEntity mte = mobileTaskService.queryById(mue.getTaskid());
		//获取参数值
		MobileSystemParamEntity mspe = mobileSystemParamService.queryById(mte.getParamid());
		
		MobileWordRecordEntity mwre = new MobileWordRecordEntity();
		mwre.setIsFail(SysConstant.NOT_CHECKED);//未选中
		mwre.setUserId(id);//用户ID
		mwre.setCount(mspe.getChildValue());//任务量
		
		//随机返回指定任务量的单词
		List<MobileWordRecordEntity> list = mobileBearWordService.query(mwre);
		List<WordExamUtil> reslist = new ArrayList<WordExamUtil>();
		for(int i=0;i<list.size();i++){
			MobileWordEntity mwe = mobileWordService.queryWordById(list.get(i).getWordId());
			WordExamUtil weu = new WordExamUtil();
			String[] temp = new String[4];
			weu.setWordid(list.get(i).getWordId());
			weu.setWord(list.get(i).getWord());
			if(mwe!=null){
				weu.setPronounce(mwe.getSymbol());
				temp[0] = mwe.getInterpretation();
				temp[1] = mwe.getErrInterpretation1();
				temp[2] = mwe.getErrInterpretation2();
				temp[3] = mwe.getErrInterpretation3();
				weu.setOptions(temp);
				weu.setCorrect("0");
			}
			reslist.add(weu);
		}
		/*MobileWordRecordEntity mwre2 = new MobileWordRecordEntity();
		for(int i=0;i<list.size();i++){
			mwre2.setId(list.get(i).getId());
			mwre2.setIsSel(SysConstant.CHECKED);
			mobileBearWordService.update(mwre2);
		}*/
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total",reslist.size());
		map.put("rows", reslist);
		return objectMapper.writeValueAsString(map);
	}

}
