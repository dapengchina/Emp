package com.ability.emp.admin.entity;

public class AdminThesauresPramEntity {
	private String id;
	private String name;
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	@Override
	public String toString() {
		return "AdminThesauresPramEntity [id=" + id + ", name=" + name + ", remark=" + remark + "]";
	}

	public AdminThesauresPramEntity(String id, String tParamName, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.remark = remark;
	}

	public AdminThesauresPramEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}