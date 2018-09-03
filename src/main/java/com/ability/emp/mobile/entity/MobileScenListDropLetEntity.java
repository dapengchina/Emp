package com.ability.emp.mobile.entity;

public class MobileScenListDropLetEntity {
	
	
	
    private String id;

    private String dropletid;

    private String dropletconftypeid;
    
    private String predropletcontypeid;

    private String reladropletid;

    private String scenicon;

    private String scenname;

    private Integer index;

    private String compperc;

    private String averagescore;

    private String currentpoint;

    private String totalpoint;
    
    
    
    
    

    

	public String getPredropletcontypeid() {
		return predropletcontypeid;
	}

	public void setPredropletcontypeid(String predropletcontypeid) {
		this.predropletcontypeid = predropletcontypeid;
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

    public String getScenicon() {
        return scenicon;
    }

    public void setScenicon(String scenicon) {
        this.scenicon = scenicon == null ? null : scenicon.trim();
    }

    public String getScenname() {
        return scenname;
    }

    public void setScenname(String scenname) {
        this.scenname = scenname == null ? null : scenname.trim();
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getCompperc() {
        return compperc;
    }

    public void setCompperc(String compperc) {
        this.compperc = compperc == null ? null : compperc.trim();
    }

    public String getAveragescore() {
        return averagescore;
    }

    public void setAveragescore(String averagescore) {
        this.averagescore = averagescore == null ? null : averagescore.trim();
    }

    public String getCurrentpoint() {
        return currentpoint;
    }

    public void setCurrentpoint(String currentpoint) {
        this.currentpoint = currentpoint == null ? null : currentpoint.trim();
    }

    public String getTotalpoint() {
        return totalpoint;
    }

    public void setTotalpoint(String totalpoint) {
        this.totalpoint = totalpoint == null ? null : totalpoint.trim();
    }
}