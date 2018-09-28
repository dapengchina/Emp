package com.ability.emp.mobile.entity;

import java.util.Date;

public class MobileUserEntity {
	
	
    private String id;//主键

    private String userName;//用户名

    private String phone;//电话

    private String openid;//唯一标识
    
	private String del;
	
	private String code;
    
    private String taskName;
    
    private String tutor;

    private String deparment;
    
    private String email;

    private Date registerdate;
    
    /**
     * 汉字转换
     * @return
     */
    private String isAppointName;
    
    private String courseid;
    
    
    
    
    
    
    

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public String getTutor() {
		return tutor;
	}

	public void setTutor(String tutor) {
		this.tutor = tutor;
	}

	public String getDeparment() {
		return deparment;
	}

	public void setDeparment(String deparment) {
		this.deparment = deparment;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegisterdate() {
		return registerdate;
	}

	public void setRegisterdate(Date registerdate) {
		this.registerdate = registerdate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getIsAppointName() {
		return isAppointName;
	}

	public void setIsAppointName(String isAppointName) {
		this.isAppointName = isAppointName;
	}
}