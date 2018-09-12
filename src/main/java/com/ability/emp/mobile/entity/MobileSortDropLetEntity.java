package com.ability.emp.mobile.entity;

public class MobileSortDropLetEntity {
	
	
    private String id;

    private String dropletid;

    private String dropletconftypeid;

    private String reladropletid;

    private String reladropletconftypeid;

    private Integer key;

    private Integer num;

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

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence == null ? null : sentence.trim();
    }
}