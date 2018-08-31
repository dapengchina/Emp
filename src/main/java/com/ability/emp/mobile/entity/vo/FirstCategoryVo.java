package com.ability.emp.mobile.entity.vo;

import java.util.List;

public class FirstCategoryVo {
	
	
	 
	   private String firCatName;
	   
	   
	   
	   private List<SceCategoryBean> sceCategoryListBean;
	   
	   
	   
	   
	   

	public List<SceCategoryBean> getSceCategoryListBean() {
		return sceCategoryListBean;
	}

	public void setSceCategoryListBean(List<SceCategoryBean> sceCategoryListBean) {
		this.sceCategoryListBean = sceCategoryListBean;
	}

	public String getFirCatName() {
		return firCatName;
	}

	public void setFirCatName(String firCatName) {
		this.firCatName = firCatName;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	private String index;

}
