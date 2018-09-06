package com.ability.emp.mobile.entity;

public class MobileFillBlankAnswerEntity {
	
    private String id;

    private String fillblankdataid;

    private Integer subnum;

    private String tit;
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFillblankdataid() {
        return fillblankdataid;
    }

    public void setFillblankdataid(String fillblankdataid) {
        this.fillblankdataid = fillblankdataid == null ? null : fillblankdataid.trim();
    }

    public Integer getSubnum() {
        return subnum;
    }

    public void setSubnum(Integer subnum) {
        this.subnum = subnum;
    }

    public String getTit() {
        return tit;
    }

    public void setTit(String tit) {
        this.tit = tit == null ? null : tit.trim();
    }
}