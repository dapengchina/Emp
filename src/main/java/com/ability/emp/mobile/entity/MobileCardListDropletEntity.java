package com.ability.emp.mobile.entity;

public class MobileCardListDropletEntity {
	
	
	
    private String id;

    private String dropletid;

    private String dropletconftypeid;
    
    private String reladropletcontypeid;

    private String reladropletid;

    private String cardicon;

    private String cardbackimag;

    private String cardname;

    private Integer index;

    private String currentscore;
    
    
    
    

    

	public String getReladropletcontypeid() {
		return reladropletcontypeid;
	}

	public void setReladropletcontypeid(String reladropletcontypeid) {
		this.reladropletcontypeid = reladropletcontypeid;
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

    public String getCardicon() {
        return cardicon;
    }

    public void setCardicon(String cardicon) {
        this.cardicon = cardicon == null ? null : cardicon.trim();
    }

    public String getCardbackimag() {
        return cardbackimag;
    }

    public void setCardbackimag(String cardbackimag) {
        this.cardbackimag = cardbackimag == null ? null : cardbackimag.trim();
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname == null ? null : cardname.trim();
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getCurrentscore() {
        return currentscore;
    }

    public void setCurrentscore(String currentscore) {
        this.currentscore = currentscore == null ? null : currentscore.trim();
    }
}