package com.ability.emp.mobile.entity;

public class MobileMatchDropLetEntity {
	
	
    private String id;

    private String dropletid;

    private String dropletconftypeid;

    private String reladropletid;

    private String reladropletconftypeid;

    private String matchtype;

    private String audio;

    private String image;

    private String ifimageright;

    private String ifsentenceright;

    private String ifanswersentenceright;

    private String answeraudio;

    private String ifansweraudioright;
    
    private String sentence;

    private String answersentence;
    
    
    
    

    public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public String getAnswersentence() {
		return answersentence;
	}

	public void setAnswersentence(String answersentence) {
		this.answersentence = answersentence;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDropletid() {
        return dropletid;
    }

    public void setDropletid(String dropletid) {
        this.dropletid = dropletid == null ? null : dropletid.trim();
    }

    public String getDropletconftypeid() {
        return dropletconftypeid;
    }

    public void setDropletconftypeid(String dropletconftypeid) {
        this.dropletconftypeid = dropletconftypeid == null ? null : dropletconftypeid.trim();
    }

    public String getReladropletid() {
        return reladropletid;
    }

    public void setReladropletid(String reladropletid) {
        this.reladropletid = reladropletid == null ? null : reladropletid.trim();
    }

    public String getReladropletconftypeid() {
        return reladropletconftypeid;
    }

    public void setReladropletconftypeid(String reladropletconftypeid) {
        this.reladropletconftypeid = reladropletconftypeid == null ? null : reladropletconftypeid.trim();
    }

    public String getMatchtype() {
        return matchtype;
    }

    public void setMatchtype(String matchtype) {
        this.matchtype = matchtype == null ? null : matchtype.trim();
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio == null ? null : audio.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getIfimageright() {
        return ifimageright;
    }

    public void setIfimageright(String ifimageright) {
        this.ifimageright = ifimageright == null ? null : ifimageright.trim();
    }

    public String getIfsentenceright() {
        return ifsentenceright;
    }

    public void setIfsentenceright(String ifsentenceright) {
        this.ifsentenceright = ifsentenceright == null ? null : ifsentenceright.trim();
    }

    public String getIfanswersentenceright() {
        return ifanswersentenceright;
    }

    public void setIfanswersentenceright(String ifanswersentenceright) {
        this.ifanswersentenceright = ifanswersentenceright == null ? null : ifanswersentenceright.trim();
    }

    public String getAnsweraudio() {
        return answeraudio;
    }

    public void setAnsweraudio(String answeraudio) {
        this.answeraudio = answeraudio == null ? null : answeraudio.trim();
    }

    public String getIfansweraudioright() {
        return ifansweraudioright;
    }

    public void setIfansweraudioright(String ifansweraudioright) {
        this.ifansweraudioright = ifansweraudioright == null ? null : ifansweraudioright.trim();
    }
}