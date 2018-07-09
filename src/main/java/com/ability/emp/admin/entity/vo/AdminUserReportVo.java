package com.ability.emp.admin.entity.vo;

import java.util.List;

public class AdminUserReportVo {
	
	
	private String id;//主键

    private String nickName;//昵称

    private String userName;//中文名

    private String phone;//电话
    
    private String date;//打卡日期
    
    @SuppressWarnings("rawtypes")
	private List dateList;//周一到周五日期集合
    
    
    
    
	@SuppressWarnings("rawtypes")
	public List getDateList() {
		return dateList;
	}

	public void setDateList(@SuppressWarnings("rawtypes") List dateList) {
		this.dateList = dateList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	

}
