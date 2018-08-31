package com.ability.emp.mobile.entity;

import java.util.List;

import com.ability.emp.mobile.entity.vo.SceCategoryBean;

public class MobileFirstCategoryEntity {
	
	
	
	    private String id;

        private String fircatname;

        private Integer index;

        
        
        //表外属性
	   
	    private List<MobileSceCategoryEntity> sceCategoryListBean;
	   
	   
	   

	   

	    public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getFircatname() {
			return fircatname;
		}

		public void setFircatname(String fircatname) {
			this.fircatname = fircatname;
		}

		public Integer getIndex() {
			return index;
		}

		public void setIndex(Integer index) {
			this.index = index;
		}

		 

		public List<MobileSceCategoryEntity> getSceCategoryListBean() {
			return sceCategoryListBean;
		}

		public void setSceCategoryListBean(List<MobileSceCategoryEntity> sceCategoryListBean) {
			this.sceCategoryListBean = sceCategoryListBean;
		}

	    
	   
	   
	   
}
