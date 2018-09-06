package com.ability.emp.mobile.entity;

public class MobileFillBlankDropLetEntity {
    private String id;

    private String dropletid;

    private String dropletconftypeid;

    private String reladropletid;

    private String reladropletconftypeid;

    private String image;
    
    
    

    public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

   
}