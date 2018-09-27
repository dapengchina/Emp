package com.ability.emp.admin.entity;

public class AdminUserTaskEntity {
	
    private String id;

    private String userid;

    private String completepercent;

    private String taskid;
    
    
    
    

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

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid == null ? null : taskid.trim();
    }
}