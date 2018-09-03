package com.ability.emp.mobile.entity;

public class MobileDropLetButtonEntity {
	
	
	
    private String id;

    private String icon;

    private String buttonfunc;

    private String buttonname;

    private String buttonlink;
    
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getButtonfunc() {
        return buttonfunc;
    }

    public void setButtonfunc(String buttonfunc) {
        this.buttonfunc = buttonfunc == null ? null : buttonfunc.trim();
    }

    public String getButtonname() {
        return buttonname;
    }

    public void setButtonname(String buttonname) {
        this.buttonname = buttonname == null ? null : buttonname.trim();
    }

    public String getButtonlink() {
        return buttonlink;
    }

    public void setButtonlink(String buttonlink) {
        this.buttonlink = buttonlink == null ? null : buttonlink.trim();
    }
}