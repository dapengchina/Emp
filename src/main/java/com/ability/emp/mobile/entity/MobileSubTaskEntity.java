package com.ability.emp.mobile.entity;

public class MobileSubTaskEntity {
	
	
    private String id;

    private String taskid;
    
    private String userid;
    
    private Integer index;

    private String name;

    private String dropletid;

    private String dropletconftypeid;

    private String score;

    private String passscore;

    private String ifpass;
    
    private String remark;
    
    private String state;
    
    
    
    

    public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid == null ? null : taskid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDropletid() {
        return dropletid;
    }

    public void setDropletid(String dropletid) {
        this.dropletid = dropletid == null ? null : dropletid.trim();
    }

    public String getDropletconftypeid() {
        return dropletconftypeid;
    }

    public void setDropletconftypeid(String dropletconftypeid) {
        this.dropletconftypeid = dropletconftypeid == null ? null : dropletconftypeid.trim();
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    public String getPassscore() {
        return passscore;
    }

    public void setPassscore(String passscore) {
        this.passscore = passscore == null ? null : passscore.trim();
    }

    public String getIfpass() {
        return ifpass;
    }

    public void setIfpass(String ifpass) {
        this.ifpass = ifpass == null ? null : ifpass.trim();
    }
}