package com.ability.emp.admin.entity;

import java.util.Date;

public class AdminTaskEntity {
	
    private String id;

    private String paramid;
    
    private String tParamid;
    
    private String tParamName;

    private Date startDate;

    private Date endDate;
    
    private String taskname;
    
    private String del;
    
    
    /**
     * 转换后的日期
     * @return
     */
    private String startStringDate;
    
    private String endStringDate;
    
    private String paramValue;
    

    
    
    public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
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

    public String getParamid() {
        return paramid;
    }

    public void setParamid(String paramid) {
        this.paramid = paramid == null ? null : paramid.trim();
    }

    public String gettParamid() {
		return tParamid;
	}

	public String gettParamName() {
		return tParamName;
	}

	public void settParamName(String tParamName) {
		this.tParamName = tParamName;
	}

	public void settParamid(String tParamid) {
		this.tParamid = tParamid;
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