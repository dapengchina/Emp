package com.ability.emp.admin.entity.vo;

import java.util.List;

public class AdminWordVo {
	
	
	   @SuppressWarnings("rawtypes")
	   private List wordids;
	   
	   private String thesaurusType;
	   
	   
	   
	   
	   

	   public String getThesaurusType() {
		  return thesaurusType;
	   }

	   public void setThesaurusType(String thesaurusType) {
		  this.thesaurusType = thesaurusType;
	   }

	   @SuppressWarnings("rawtypes")
	   public List getWordids() {
		  return wordids;
	   }

	   @SuppressWarnings("rawtypes")
	   public void setWordids(List wordids) {
		 this.wordids = wordids;
	   }
	   

}
