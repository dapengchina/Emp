package com.ability.emp.mobile.entity;

import java.util.Date;

public class MobileWordRecordEntity {
	private String id;
	private String wordId;
	private String word;
	private String userId;
	private String isFail;
	private String isSel;
	private Date createDate;
	private Date updateDate;

	private String count;
	
	
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
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
