package com.ability.emp.mobile.entity.vo;

import java.util.List;

import com.ability.emp.mobile.entity.MobileUserCenterEntity;

public class UserCenterVo {
	
	
	
	private String groupType;
	   
	private List<MobileUserCenterEntity> userCenterList;
	   
	   

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public List<MobileUserCenterEntity> getUserCenterList() {
		return userCenterList;
	}

	public void setUserCenterList(List<MobileUserCenterEntity> userCenterList) {
		this.userCenterList = userCenterList;
	}
	   
	   
	   

}
