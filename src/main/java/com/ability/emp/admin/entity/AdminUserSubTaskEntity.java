package com.ability.emp.admin.entity;

public class AdminUserSubTaskEntity {
	
	
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
    
    private String ifpassname;

    private String remark;
    
    
    /**
     * 表外字段
     * @return
     */
    private String averageScore;
    
    private double completePercent;
    
    private String star;
    
    private String totalPoint;
    
    private String currentPoint;
    
    
    
    
    

    public String getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(String totalPoint) {
		this.totalPoint = totalPoint;
	}

	public String getCurrentPoint() {
		return currentPoint;
	}

	public void setCurrentPoint(String currentPoint) {
		this.currentPoint = currentPoint;
	}

	public String getIfpassname() {
		return ifpassname;
	}

	public void setIfpassname(String ifpassname) {
		this.ifpassname = ifpassname;
	}

	public double getCompletePercent() {
		return completePercent;
	}

	public void setCompletePercent(double completePercent) {
		this.completePercent = completePercent;
	}

	public String getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(String averageScore) {
		this.averageScore = averageScore;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}