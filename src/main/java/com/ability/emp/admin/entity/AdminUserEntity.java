package com.ability.emp.admin.entity;

public class AdminUserEntity {
    private String id;//主键

    private String nickName;//昵称

    private String userName;//中文名

    private String phone;//电话

    private String openid;//唯一标识
    
    private String taskid;//任务ID

    private String isAppoint;//是否被指派任务
    
	private String del;
	
	private String code;
	
	private String deparment;//部门

    private String tutor;//导师
    
    
    
    /**
     * 表外字段
     * @return
     */
    private String taskName;
    
    /**
     * 汉字转换
     * @return
     */
    private String isAppointName;
    
    
    
    
    

	public String getDeparment() {
		return deparment;
	}

	public void setDeparment(String deparment) {
		this.deparment = deparment;
	}

	public String getTutor() {
		return tutor;
	}

	public void setTutor(String tutor) {
		this.tutor = tutor;
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

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getIsAppoint() {
		return isAppoint;
	}

	public void setIsAppoint(String isAppoint) {
		this.isAppoint = isAppoint;
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