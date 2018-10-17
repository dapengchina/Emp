package com.ability.emp.mobile.entity;

public class MobileScenListDropLetEntity {
	
	
	
    private String id;

    private String dropletid;

    private String dropletconftypeid;
    
    private String reladropletcontypeid;

    private String reladropletid;

    private String scenicon;

    private String scenname;

    private Integer index;


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

    
}