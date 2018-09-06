package com.ability.emp.mobile.entity;

public class MobileFillBlankQuestionEntity {
	
	
	
    private String id;

    private String fillblankdataid;

    private String tit;

    private String correct;

    private Integer num;

    private String ifem;
    
    
    

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

    public String getTit() {
        return tit;
    }

    public void setTit(String tit) {
        this.tit = tit == null ? null : tit.trim();
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct == null ? null : correct.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getIfem() {
        return ifem;
    }

    public void setIfem(String ifem) {
        this.ifem = ifem == null ? null : ifem.trim();
    }
}