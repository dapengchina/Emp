package com.ability.emp.mobile.entity;

public class MobileSystemParamEntity {
    private String id;
    private String parentName;
    private String parentValue;
    private String childName;
	private String childValue;
    private String del;
    
    @Override
	public String toString() {
		return "AdminSystemParamEntity [id=" + id + ", parentName=" + parentName + ", parentValue=" + parentValue
				+ ", childName=" + childName + ", childValue=" + childValue + ", del=" + del + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getParentValue() {
		return parentValue;
	}
	public void setParentValue(String parentValue) {
		this.parentValue = parentValue;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public String getChildValue() {
		return childValue;
	}
	public void setChildValue(String childValue) {
		this.childValue = childValue;
	}
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}
}
