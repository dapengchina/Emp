package com.ability.emp.admin.entity;

import java.util.Date;

public class AdminTaskEntity {
	
    private String id;
    
    private String thesauresType;
    
    private String tasktype;

    private String courseid;
    
    private Date startDate;

    private Date endDate;
    
    private String taskname;
    
    
    
    
    /**
     * 转换后的日期
     * @return
     */
    private String startStringDate;
    
    private String endStringDate;

    private String thesauresTypeName;
    
    
    
    
    
    
    public String getThesauresTypeName() {
		return thesauresTypeName;
	}

	public void setThesauresTypeName(String thesauresTypeName) {
		this.thesauresTypeName = thesauresTypeName;
	}

	public String getTasktype() {
		return tasktype;
	}

	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public String getThesauresType() {
		return thesauresType;
	}

	public void setThesauresType(String thesauresType) {
		this.thesauresType = thesauresType;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getStartStringDate() {
		return startStringDate;
	}

	public void setStartStringDate(String startStringDate) {
		this.startStringDate = startStringDate;
	}

	public String getEndStringDate() {
		return endStringDate;
	}

	public void setEndStringDate(String endStringDate) {
		this.endStringDate = endStringDate;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

	public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    
}