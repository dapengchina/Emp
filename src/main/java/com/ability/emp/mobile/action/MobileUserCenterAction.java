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

import com.ability.emp.mobile.entity.MobileUserCenterEntity;
import com.ability.emp.mobile.entity.vo.UserCenterVo;
import com.ability.emp.mobile.server.MobileHitCardService;
import com.ability.emp.mobile.server.MobileUserCenterService;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/userCenter")
public class MobileUserCenterAction {
	
	
	
	@Resource
	private MobileUserCenterService mobileUserCenterService;
	
	@Resource
	private MobileHitCardService mobileHitCardService;
	
    ObjectMapper objectMapper = new ObjectMapper();
	
	
	
	@RequestMapping("/getUserCenterData/{userid}")
	@ResponseBody
	public String getUserCenterData(@PathVariable("userid") String userid) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		List<UserCenterVo> list = new ArrayList<UserCenterVo>();
		MobileUserCenterEntity muce = new MobileUserCenterEntity();
		//获取分组数据
		List<MobileUserCenterEntity> groupList = mobileUserCenterService.getGroupList();
		for(int i=0;i<groupList.size();i++){
			UserCenterVo ucv = new UserCenterVo();
			ucv.setGroupType(groupList.get(i).getGrouptype());
			//根据组类型查询
			muce.setGrouptype(groupList.get(i).getGrouptype());
			List<MobileUserCenterEntity> userCenterList = mobileUserCenterService.getUserCenterList(muce);
		    ucv.setUserCenterList(userCenterList);
		    
		    list.add(ucv);
		}
		
		//获取用户累计打卡天数
		int hitcarddays = mobileHitCardService.count(userid);
		
		map.put("result", list);
		map.put("hitcarddays", hitcarddays);
		
		return objectMapper.writeValueAsString(map);
	}

}
