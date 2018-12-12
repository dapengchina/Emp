package com.ability.emp.admin.entity;

import java.util.Date;

public class AdminScecategoryEntity {
	
    private String id;

    private String fircatid;

    private String scecatname;

    private Integer index;

    private String icon;

    private String dropletid;

    private String dropletconftypeid;

    private String coursestate;
    
    private String coursetype;

    private Date courseenddate;
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFircatid() {
        return fircatid;
    }

    public void setFircatid(String fircatid) {
        this.fircatid = fircatid == null ? null : fircatid.trim();
    }

    public String getScecatname() {
        return scecatname;
    }

    public void setScecatname(String scecatname) {
        this.scecatname = scecatname == null ? null : scecatname.trim();
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
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

    public String getCoursestate() {
        return coursestate;
    }

    public void setCoursestate(String coursestate) {
        this.coursestate = coursestate == null ? null : coursestate.trim();
    }

    public Date getCourseenddate() {
        return courseenddate;
    }

    public void setCourseenddate(Date courseenddate) {
        this.courseenddate = courseenddate;
    }

	public String getCoursetype() {
		return coursetype;
	}

	public void setCoursetype(String coursetype) {
		this.coursetype = coursetype;
	}
}