package com.ability.emp.mobile.entity;

public class MobileDropLetEntity {
	
	
	
    private String id;

    private String dropletlink;

    private String desc;

    private String dropletname;
    
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDropletlink() {
        return dropletlink;
    }

    public void setDropletlink(String dropletlink) {
        this.dropletlink = dropletlink == null ? null : dropletlink.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getDropletname() {
        return dropletname;
    }

    public void setDropletname(String dropletname) {
        this.dropletname = dropletname == null ? null : dropletname.trim();
    }
}