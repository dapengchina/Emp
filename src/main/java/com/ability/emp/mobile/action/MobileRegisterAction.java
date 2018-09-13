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
import com.ability.emp.mobile.server.MobileUserService;
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
		mue.setIsAppoint(SysConstant.NOT_ASSIGNED);//默认未指派
		mue.setDel(SysConstant.NO_DEL);//默认未删除
		int i = mobileUserService.registerUser(mue);
		if(i>0){
			map.put("result", "注册成功");
			map.put("code", 1);
		}else{
			map.put("result", "注册失败");
			map.put("code", 0);
		}
		return objectMapper.writeValueAsString(map);
	}

}
