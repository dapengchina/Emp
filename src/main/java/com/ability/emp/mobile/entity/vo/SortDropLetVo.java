package com.ability.emp.mobile.entity.vo;

import java.util.List;

import com.ability.emp.mobile.entity.bean.SortDropLetBean;

public class SortDropLetVo {
	
	
	
	

    private String reladropletid;
    
    private String dropLetLink;

    private String reladropletconftypeid;

    private List<SortDropLetBean> sentence;
    
    
    
    

	public String getReladropletid() {
		return reladropletid;
	}

	public void setReladropletid(String reladropletid) {
		this.reladropletid = reladropletid;
	}

	public String getDropLetLink() {
		return dropLetLink;
	}

	public void setDropLetLink(String dropLetLink) {
		this.dropLetLink = dropLetLink;
	}

	public String getReladropletconftypeid() {
		return reladropletconftypeid;
	}

	public void setReladropletconftypeid(String reladropletconftypeid) {
		this.reladropletconftypeid = reladropletconftypeid;
	}

	public List<SortDropLetBean> getSentence() {
		return sentence;
	}

	public void setSentence(List<SortDropLetBean> sentence) {
		this.sentence = sentence;
	}

	
    

}
