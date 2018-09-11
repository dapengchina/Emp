package com.ability.emp.mobile.entity.vo;

public class ReadSpeakDropLetVo {
	
	
	
	
	

    private String reladropletid;
    
    private String dropLetLink;

    private String reladropletconftypeid;

    private String readtype;

    private String image;

    private String audio;

    private Object sentence[];
    
    
    
    

	public Object[] getSentence() {
		return sentence;
	}

	public void setSentence(Object[] sentence) {
		this.sentence = sentence;
	}

	public String getReladropletid() {
		return reladropletid;
	}

	public void setReladropletid(String reladropletid) {
		this.reladropletid = reladropletid;
	}

	public String getDropLetLink() {
		return dropLetLink;
	}

	public void setDropLetLink(String dropLetLink) {
		this.dropLetLink = dropLetLink;
	}

	public String getReladropletconftypeid() {
		return reladropletconftypeid;
	}

	public void setReladropletconftypeid(String reladropletconftypeid) {
		this.reladropletconftypeid = reladropletconftypeid;
	}

	public String getReadtype() {
		return readtype;
	}

	public void setReadtype(String readtype) {
		this.readtype = readtype;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAudio() {
		return audio;
	}

	public void setAudio(String audio) {
		this.audio = audio;
	}

}
