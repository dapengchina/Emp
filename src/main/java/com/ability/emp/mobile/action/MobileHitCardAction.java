package com.ability.emp.mobile.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.mobile.entity.MobileHitCardEntity;
import com.ability.emp.mobile.server.MobileHitCardService;
import com.ability.emp.util.UUIDUtil;
import com.fasterxml.jackson.core.JsonProcessingException;




/**
 * 用户打卡
 * @author Devin
 *
 */
@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/hitcard")
public class MobileHitCardAction {
	
	
	
	
	@Resource
	private MobileHitCardService mobileHitCardService;
	
	
	@RequestMapping(value="/{hit}", method = RequestMethod.POST)
	@ResponseBody
	public String mean(@RequestBody MobileHitCardEntity mobileHitCardEntity) throws JsonProcessingException{
		   if(mobileHitCardEntity.getUserId()==null || "".equals(mobileHitCardEntity.getUserId())){
			   return "1";
		   }
		   SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   mobileHitCardEntity.setDate(Timestamp.valueOf(sf.format(new Date())));
		   mobileHitCardEntity.setId(UUIDUtil.generateUUID());
		   int i = mobileHitCardService.add(mobileHitCardEntity);
		   if(i>0){
			   //将选中并且考试未通过的单词,考试状态修改为通过
			   return "0";
		   }else{
			   return "1";
		   }
	}

}
