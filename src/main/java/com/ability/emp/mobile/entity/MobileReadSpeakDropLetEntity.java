package com.ability.emp.mobile.entity;

public class MobileReadSpeakDropLetEntity {
	
    private String id;

    private String dropletid;

    private String dropletconftypeid;

    private String reladropletid;

    private String reladropletconftypeid;

    private String readtype;

    private String image;

    private String audio;

    private String sentence;

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

    public String getReadtype() {
        return readtype;
    }

    public void setReadtype(String readtype) {
        this.readtype = readtype == null ? null : readtype.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio == null ? null : audio.trim();
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence == null ? null : sentence.trim();
    }
}