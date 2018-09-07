package com.ability.emp.mobile.entity.vo;

import java.util.List;

import com.ability.emp.mobile.entity.bean.FillBlankAnswerBean;
import com.ability.emp.mobile.entity.bean.FillBlankQuestionBean;

public class FillBlankDropLetVo {
	
	

    private String dropletid;
    
    private String dropLetLink;

    private String dropletconftypeid;

    private String reladropletid;

    private String reladropletconftypeid;

    private String image;
    
    private int[] emptyposition;
    
    private List<FillBlankQuestionBean> quest;
    
    private List<FillBlankAnswerBean> answer;
    
    
    
    

	

	public int[] getEmptyposition() {
		return emptyposition;
	}

	public void setEmptyposition(int[] emptyposition) {
		this.emptyposition = emptyposition;
	}

	public List<FillBlankQuestionBean> getQuest() {
		return quest;
	}

	public void setQuest(List<FillBlankQuestionBean> quest) {
		this.quest = quest;
	}

	public List<FillBlankAnswerBean> getAnswer() {
		return answer;
	}

	public void setAnswer(List<FillBlankAnswerBean> answer) {
		this.answer = answer;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDropletid() {
		return dropletid;
	}

	public void setDropletid(String dropletid) {
		this.dropletid = dropletid;
	}

	public String getDropLetLink() {
		return dropLetLink;
	}

	public void setDropLetLink(String dropLetLink) {
		this.dropLetLink = dropLetLink;
	}

	public String getDropletconftypeid() {
		return dropletconftypeid;
	}

	public void setDropletconftypeid(String dropletconftypeid) {
		this.dropletconftypeid = dropletconftypeid;
	}

	public String getReladropletid() {
		return reladropletid;
	}

	public void setReladropletid(String reladropletid) {
		this.reladropletid = reladropletid;
	}

	public String getReladropletconftypeid() {
		return reladropletconftypeid;
	}

	public void setReladropletconftypeid(String reladropletconftypeid) {
		this.reladropletconftypeid = reladropletconftypeid;
	}

	
    

}
