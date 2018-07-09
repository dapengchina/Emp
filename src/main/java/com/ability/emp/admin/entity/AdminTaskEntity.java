package com.ability.emp.admin.entity;

import java.util.Date;

public class AdminTaskEntity {
	
    private String id;

    private String paramid;
    
    private String thesauresType;
    
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
    
    private String tParamName;
    

    
    
    
    
    public String getThesauresType() {
		return thesauresType;
	}

	public void setThesauresType(String thesauresType) {
		this.thesauresType = thesauresType;
	}

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

	public String gettParamName() {
		return tParamName;
	}

	public void settParamName(String tParamName) {
		this.tParamName = tParamName;
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