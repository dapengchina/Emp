package com.ability.emp.admin.entity;

import java.util.Date;

public class AdminWordRecordEntity {
	
	private String id;
	private String wordId;
	private String word;
	private String userId;
	private String isFail;
	private String isSel;
	private String isPass;
	private Date updateDate;
	
	
	
	
	
	
	public String getIsPass() {
		return isPass;
	}
	public void setIsPass(String isPass) {
		this.isPass = isPass;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWordId() {
		return wordId;
	}
	public void setWordId(String wordId) {
		this.wordId = wordId;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIsFail() {
		return isFail;
	}
	public void setIsFail(String isFail) {
		this.isFail = isFail;
	}
	public String getIsSel() {
		return isSel;
	}
	public void setIsSel(String isSel) {
		this.isSel = isSel;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
