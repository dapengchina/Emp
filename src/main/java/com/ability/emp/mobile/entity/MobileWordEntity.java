package com.ability.emp.mobile.entity;

public class MobileWordEntity {	
	private String id;//主键
	private String word;//单词
	private String interpretation;
	private String sentence;
	private String mp3Name;
	private String video;
	private String img;
	private String errInterpretation1;
	private String errInterpretation2;
	private String errInterpretation3;
	public String getErrInterpretation1() {
		return errInterpretation1;
	}
	public void setErrInterpretation1(String errInterpretation1) {
		this.errInterpretation1 = errInterpretation1;
	}
	public String getErrInterpretation2() {
		return errInterpretation2;
	}
	public void setErrInterpretation2(String errInterpretation2) {
		this.errInterpretation2 = errInterpretation2;
	}
	public String getErrInterpretation3() {
		return errInterpretation3;
	}
	public void setErrInterpretation3(String errInterpretation3) {
		this.errInterpretation3 = errInterpretation3;
	}
	private String del;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getInterpretation() {
		return interpretation;
	}
	public void setInterpretation(String interpretation) {
		this.interpretation = interpretation;
	}
	public String getSentence() {
		return sentence;
	}
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
	public String getMp3Name() {
		return mp3Name;
	}
	public void setMp3Name(String mp3Name) {
		this.mp3Name = mp3Name;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}
}
