package com.ability.emp.admin.entity;

import java.util.Date;

public class AdminTaskEntity {
	
    private String id;
    
    private String thesauresType;
    
    private String thesaures_Type;
    
    private String tasktype;
    
    private String tasktypename;

    private String courseid;
    
    private Date startDate;
    
    private Date start_Date;

    private Date endDate;
    
    private Date end_Date;
    
    private String taskname;
    
    
    
    
    /**
     * 转换后的日期
     * @return
     */
    private String startStringDate;
    
    private String endStringDate;

    private String thesauresTypeName;
    
    
    
    
    
    
    public String getTasktypename() {
		return tasktypename;
	}

	public void setTasktypename(String tasktypename) {
		this.tasktypename = tasktypename;
	}

	public String getThesaures_Type() {
		return thesaures_Type;
	}

	public void setThesaures_Type(String thesaures_Type) {
		this.thesaures_Type = thesaures_Type;
	}

	public Date getStart_Date() {
		return start_Date;
	}

	public void setStart_Date(Date start_Date) {
		this.start_Date = start_Date;
	}

	public Date getEnd_Date() {
		return end_Date;
	}

	public void setEnd_Date(Date end_Date) {
		this.end_Date = end_Date;
	}

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