package com.ability.emp.mobile.entity;

import java.util.Date;

public class MobileWordRecordEntity {
	private String id;
	private String wordId;
	private String word;
	private String userId;
	private String isFail;
	private String isSel;
	private String isPass;
	private Date createDate;
	private Date updateDate;

	
	
	//表外字段
	private int count;
	private String smpoly;
	private String means;
	
	
	
	
	
	
	public String getIsPass() {
		return isPass;
	}
	public void setIsPass(String isPass) {
		this.isPass = isPass;
	}
	public String getSmpoly() {
		return smpoly;
	}
	public void setSmpoly(String smpoly) {
		this.smpoly = smpoly;
	}
	public String getMeans() {
		return means;
	}
	public void setMeans(String means) {
		this.means = means;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
