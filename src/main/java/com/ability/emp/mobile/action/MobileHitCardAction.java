package com.ability.emp.mobile.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.constant.SysConstant;
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
	
	
	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
	
	
	@Resource
	private MobileHitCardService mobileHitCardService;
	
	
	@RequestMapping(value="/{hit}", method = RequestMethod.POST)
	@ResponseBody
	public String hitCard(@RequestBody MobileHitCardEntity mobileHitCardEntity) throws JsonProcessingException{
		   if(mobileHitCardEntity.getUserId()==null || "".equals(mobileHitCardEntity.getUserId())){
			   return "1";
		   }
		   //根据用户ID查询用户当天是否已经打卡
		   MobileHitCardEntity mhce = new MobileHitCardEntity();
		   mhce.setUserId(mobileHitCardEntity.getUserId());
		   mhce.setStringDate(sf2.format(new Date()));
		   mhce.setState(SysConstant.TASK_STATE0);
		   List<MobileHitCardEntity> hitCardList = mobileHitCardService.queryByUserID(mhce);
		   //用户当天已经打卡
		   if(hitCardList!=null && hitCardList.size()>0){
			   return "1";
		   }
		   
		   mobileHitCardEntity.setDate(Timestamp.valueOf(sf.format(new Date())));
		   mobileHitCardEntity.setId(UUIDUtil.generateUUID());
		   mobileHitCardEntity.setState(SysConstant.TASK_STATE0);
		   int i = mobileHitCardService.add(mobileHitCardEntity);
		   if(i>0){
			   return "0";
		   }else{
			   return "1";
		   }
	}

}
