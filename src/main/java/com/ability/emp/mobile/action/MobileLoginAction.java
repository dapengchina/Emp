package com.ability.emp.mobile.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.util.LoginUtil;
import com.ability.emp.mobile.entity.MobileLoginEntity;
import com.ability.emp.mobile.server.MobileUserService;
import com.ability.emp.util.AESEncryptionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 用户登录
 * @author Devin
 * @since 2018-3-23
 *
 */
@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/login")
public class MobileLoginAction {
	
	ObjectMapper objectMapper = new ObjectMapper();  
	
	@Resource
	private MobileUserService mobileUserService;
	
	@RequestMapping("/login")
	@ResponseBody
	public String add(@RequestBody MobileLoginEntity ule) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", "登录成功");
		String session_key = LoginUtil.resfresh(ule.getCode());
		
        /**
         * 解密数据
         */
        String result = AESEncryptionUtil.decrypt(ule.getEncrypteData(),session_key,ule.getIv());
        if (null != result && result.length() > 0) {
            map.put("status", 1);
            map.put("msg", "解密成功");

            JSONObject userInfoJSON = new JSONObject(result);
            Map<String,Object> userInfo = new HashMap<String,Object>();
            userInfo.put("openId", userInfoJSON.get("openId"));//唯一标识
            userInfo.put("nickName", userInfoJSON.get("nickName"));//用户昵称
            userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));//头像地址
            map.put("userInfo", userInfo);
            System.out.println("==========="+objectMapper.writeValueAsString(map));
        }
	    return objectMapper.writeValueAsString(map);
	}

}
