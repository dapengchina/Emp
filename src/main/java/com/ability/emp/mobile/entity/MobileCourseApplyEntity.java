package com.ability.emp.mobile.entity;


import java.util.Date;

public class MobileCourseApplyEntity {
    private String id;

    private String coursefirstleveltype;
    
    private String courseFirstTypeName;

    private String coursename;

    private String coursestate;
    
    private String coursestatename;

    private String coursedescribe;

    private String approvestate;

    private Date approvedate;
    
    private String approvestringdate;

    private Date applydate;

    private String applyuserid;
    
    private String applyUserName;

    private String approveuserid;
    
    
    

    public String getCoursestatename() {
		return coursestatename;
	}

	public void setCoursestatename(String coursestatename) {
		this.coursestatename = coursestatename;
	}

	public String getApprovestringdate() {
		return approvestringdate;
	}

	public void setApprovestringdate(String approvestringdate) {
		this.approvestringdate = approvestringdate;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCoursefirstleveltype() {
        return coursefirstleveltype;
    }

    public void setCoursefirstleveltype(String coursefirstleveltype) {
        this.coursefirstleveltype = coursefirstleveltype == null ? null : coursefirstleveltype.trim();
    }
    
    public String getCourseFirstTypeName() {
        return courseFirstTypeName;
    }

    public void setCourseFirstTypeName(String courseFirstTypeName) {
        this.courseFirstTypeName = courseFirstTypeName == null ? null : courseFirstTypeName.trim();
    }
    
    

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename == null ? null : coursename.trim();
    }

    public String getCoursestate() {
        return coursestate;
    }

    public void setCoursestate(String coursestate) {
        this.coursestate = coursestate == null ? null : coursestate.trim();
    }
    
    public String getCoursestateName() {
        return coursestatename;
    }

    public void setCoursestateName(String coursestatename) {
        this.coursestatename = coursestatename == null ? null : coursestatename.trim();
    }
    

    public String getCoursedescribe() {
        return coursedescribe;
    }

    public void setCoursedescribe(String coursedescribe) {
        this.coursedescribe = coursedescribe == null ? null : coursedescribe.trim();
    }

    public String getApprovestate() {
        return approvestate;
    }

    public void setApprovestate(String approvestate) {
        this.approvestate = approvestate == null ? null : approvestate.trim();
    }

    public Date getApprovedate() {
        return approvedate;
    }

    public void setApprovedate(Date approvedate) {
        this.approvedate = approvedate;
    }

    public Date getApplydate() {
        return applydate;
    }

    public void setApplydate(Date applydate) {
        this.applydate = applydate;
    }

    public String getApplyuserid() {
        return applyuserid;
    }

    public void setApplyuserid(String applyuserid) {
        this.applyuserid = applyuserid == null ? null : applyuserid.trim();
    }
    
    public String getApplyUserName(){
    	return applyUserName;
    }
    
    public void setApplyUserName(String applyUserName){
    	this.applyUserName = applyUserName == null ? null : applyUserName;
    }

    public String getApproveuserid() {
        return approveuserid;
    }

    public void setApproveuserid(String approveuserid) {
        this.approveuserid = approveuserid == null ? null : approveuserid.trim();
    }
}