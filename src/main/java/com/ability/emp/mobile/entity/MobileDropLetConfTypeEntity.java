package com.ability.emp.mobile.entity;

public class MobileDropLetConfTypeEntity {
	
	
    private String id;

    private String dropletid;

    private String dropletconftype;

    private String firstbuttonid;

    private String secbuttonid;

    private String thirbuttonid;

    private String fourbuttonid;
    
    

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

    public String getDropletconftype() {
        return dropletconftype;
    }

    public void setDropletconftype(String dropletconftype) {
        this.dropletconftype = dropletconftype == null ? null : dropletconftype.trim();
    }

    public String getFirstbuttonid() {
        return firstbuttonid;
    }

    public void setFirstbuttonid(String firstbuttonid) {
        this.firstbuttonid = firstbuttonid == null ? null : firstbuttonid.trim();
    }

    public String getSecbuttonid() {
        return secbuttonid;
    }

    public void setSecbuttonid(String secbuttonid) {
        this.secbuttonid = secbuttonid == null ? null : secbuttonid.trim();
    }

    public String getThirbuttonid() {
        return thirbuttonid;
    }

    public void setThirbuttonid(String thirbuttonid) {
        this.thirbuttonid = thirbuttonid == null ? null : thirbuttonid.trim();
    }

    public String getFourbuttonid() {
        return fourbuttonid;
    }

    public void setFourbuttonid(String fourbuttonid) {
        this.fourbuttonid = fourbuttonid == null ? null : fourbuttonid.trim();
    }
}