package com.ability.emp.mobile.entity;

import java.util.Date;

public class MobileUserTaskEntity {
	
    private String id;

    private String userid;

    private String courseid;

    private Date coursestartdate;

    private Date courseenddate;

    private String completepercent;
    
    

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

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid == null ? null : courseid.trim();
    }

    public Date getCoursestartdate() {
        return coursestartdate;
    }

    public void setCoursestartdate(Date coursestartdate) {
        this.coursestartdate = coursestartdate;
    }

    public Date getCourseenddate() {
        return courseenddate;
    }

    public void setCourseenddate(Date courseenddate) {
        this.courseenddate = courseenddate;
    }

    public String getCompletepercent() {
        return completepercent;
    }

    public void setCompletepercent(String completepercent) {
        this.completepercent = completepercent == null ? null : completepercent.trim();
    }
}