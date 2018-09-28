package com.ability.emp.mobile.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.constant.SysConstant;
import com.ability.emp.mobile.entity.MobileUserEntity;
import com.ability.emp.mobile.entity.MobileUserTaskEntity;
import com.ability.emp.mobile.server.MobileUserService;
import com.ability.emp.mobile.server.MobileUserTaskService;
import com.ability.emp.util.UUIDUtil;
import com.fasterxml.jackson.databind.ObjectMapper;



@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/register")
public class MobileRegisterAction {
	
	
	
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@SuppressWarnings("unused")
	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Resource
	private MobileUserService mobileUserService;
	
	@Resource
	private MobileUserTaskService mobileUserTaskService;
	
	
	@RequestMapping("/register")
	@ResponseBody
	public String register(@RequestBody MobileUserEntity mue) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		//查询此手机号是否被注册
		MobileUserEntity me = mobileUserService.getUser(mue);
		if(me!=null){
			map.put("result", "此手机号已被注册");
			map.put("code", 2);
			return objectMapper.writeValueAsString(map);
		}
		//校验通过注册
		mue.setRegisterdate(new Date());//注册时间
		mue.setId(UUIDUtil.generateUUID());//主键
		mue.setDel(SysConstant.NO_DEL);//默认未删除
		String i = mobileUserService.registerUser(mue);
		if(!i.equals("0")){
			//开始保存任务
			String[] courseid = mue.getCourseid().split(",");
			for(int k=0;k<courseid.length;k++){
				MobileUserTaskEntity userTask = new MobileUserTaskEntity();
				userTask.setId(UUIDUtil.generateUUID());
				userTask.setUserid(mue.getId());
				mobileUserTaskService.addUserTask(userTask);
			}
			
//			if(j>0){
//				map.put("result", "注册成功");
//				map.put("code", 3);
//			}else{
//				map.put("result", "任务保存失败");
//				map.put("code", 4);
//			}
			map.put("result", "注册成功");
			map.put("code", 1);
		}else{
			map.put("result", "注册失败");
			map.put("code", 0);
		}
		return objectMapper.writeValueAsString(map);
	}

}
