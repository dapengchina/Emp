package com.ability.emp.mobile.entity.vo;

public class DictationVo {
	
	
	
	
	

    private String reladropletid;
    
    private String dropletlink;

    private String reladropletcontypeid;

    private String image;

    private String audio;
    
    private Object question[];

    private String anwser;
    
    
    
    

	public Object[] getQuestion() {
		return question;
	}

	public void setQuestion(Object[] question) {
		this.question = question;
	}

	public String getReladropletid() {
		return reladropletid;
	}

	public void setReladropletid(String reladropletid) {
		this.reladropletid = reladropletid;
	}

	public String getDropletlink() {
		return dropletlink;
	}

	public void setDropletlink(String dropletlink) {
		this.dropletlink = dropletlink;
	}

	public String getReladropletcontypeid() {
		return reladropletcontypeid;
	}

	public void setReladropletcontypeid(String reladropletcontypeid) {
		this.reladropletcontypeid = reladropletcontypeid;
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

	public String getAnwser() {
		return anwser;
	}

	public void setAnwser(String anwser) {
		this.anwser = anwser;
	}
    
    
    
    

}
