package com.ability.emp.admin.entity;

public class AdminThesauresPramEntity {
	private String id;
	private String tParamName;
	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String gettParamName() {
		return tParamName;
	}

	public void settParamName(String tParamName) {
		this.tParamName = tParamName == null ? null : tParamName.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	@Override
	public String toString() {
		return "AdminThesauresPramEntity [id=" + id + ", tParamName=" + tParamName + ", content=" + content + "]";
	}

	public AdminThesauresPramEntity(String id, String tParamName, String content) {
		super();
		this.id = id;
		this.tParamName = tParamName;
		this.content = content;
	}

	public AdminThesauresPramEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}