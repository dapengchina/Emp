package com.ability.emp.mobile.entity;



public class MobileUserTaskEntity {
	
    private String id;

    private String userid;
    
    private String taskid;

    private String completepercent;
    
    
    
    

    public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getCompletepercent() {
        return completepercent;
    }

    public void setCompletepercent(String completepercent) {
        this.completepercent = completepercent == null ? null : completepercent.trim();
    }
}