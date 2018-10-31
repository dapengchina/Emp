package com.ability.emp.mobile.entity.vo;

public class CourseVo {
	
	
	private String courseID;

    private String scecatname;

    private String icon;

    private String coursestate;
    
    private String coursestatename;

    private Integer courselimit;
    
    private boolean selected;
    
    
    
    

	public String getCoursestatename() {
		return coursestatename;
	}

	public void setCoursestatename(String coursestatename) {
		this.coursestatename = coursestatename;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getScecatname() {
		return scecatname;
	}

	public void setScecatname(String scecatname) {
		this.scecatname = scecatname;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getCoursestate() {
		return coursestate;
	}

	public void setCoursestate(String coursestate) {
		this.coursestate = coursestate;
	}

	public Integer getCourselimit() {
		return courselimit;
	}

	public void setCourselimit(Integer courselimit) {
		this.courselimit = courselimit;
	}
    
    
    
    

}
