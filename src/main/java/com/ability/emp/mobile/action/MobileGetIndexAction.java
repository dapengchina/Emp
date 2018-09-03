package com.ability.emp.mobile.action;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.constant.SysConstant;
import com.ability.emp.mobile.entity.MobileDropLetEntity;
import com.ability.emp.mobile.entity.MobileFirstCategoryEntity;
import com.ability.emp.mobile.entity.MobileSceCategoryEntity;
import com.ability.emp.mobile.entity.bean.SceCategoryBean;
import com.ability.emp.mobile.entity.vo.FirstCategoryVo;
import com.ability.emp.mobile.server.MobileDropLetService;
import com.ability.emp.mobile.server.MobileFirstCategoryService;
import com.ability.emp.mobile.server.MobileSceCategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;



@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/getIndexData")
public class MobileGetIndexAction {
	
	
	
	
	ObjectMapper objectMapper = new ObjectMapper();  
	
	@Resource
	private MobileFirstCategoryService mobileFirstCategoryService;
	
	@Resource
	private MobileSceCategoryService mobileSceCategoryService;
	
	@Resource
	private MobileDropLetService mobileDropLetService;
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/getIndexData")
	@ResponseBody
	public String getIndexData() throws Exception {
		
		MobileFirstCategoryEntity me = new MobileFirstCategoryEntity();
		MobileSceCategoryEntity mf = new MobileSceCategoryEntity();
		MobileDropLetEntity md = new MobileDropLetEntity();
		@SuppressWarnings("rawtypes")
		List firstlist = new ArrayList();
		
		
		List<MobileFirstCategoryEntity> list = mobileFirstCategoryService.getIndexData(me);
		
		for(int i=0;i<list.size();i++){
			@SuppressWarnings("rawtypes")
			List seclist = new ArrayList();
			FirstCategoryVo fv = new FirstCategoryVo();
			fv.setFirCatName(list.get(i).getFircatname());
			fv.setIndex(list.get(i).getIndex().toString());
			
        	mf.setFircatid(list.get(i).getId());
        	List<MobileSceCategoryEntity> mobileSceCategoryList = mobileSceCategoryService.getScenListDropLetData(mf);
        	for(int j=0;j<mobileSceCategoryList.size();j++){
        		SceCategoryBean scb = new SceCategoryBean();
        		scb.setSceCatName(mobileSceCategoryList.get(j).getScecatname());
        		scb.setIcon(SysConstant.SERVICE_HOST+mobileSceCategoryList.get(j).getIcon());
        		scb.setIndex(mobileSceCategoryList.get(j).getIndex().toString());
        		scb.setDropLetId(mobileSceCategoryList.get(j).getDropletid());
        		scb.setDropLetConfigTypeId(mobileSceCategoryList.get(j).getDropletconftypeid());
        		scb.setId(mobileSceCategoryList.get(j).getId());
        		
        		md.setId(mobileSceCategoryList.get(j).getDropletid());
        		MobileDropLetEntity mde = mobileDropLetService.getDropLetByID(md);
        		if(mde!=null){
        			scb.setDropLetLink(mde.getDropletlink());
        		}
        		seclist.add(scb);
        	}
        	fv.setSceCategoryListBean(seclist);
        	firstlist.add(fv);
        }
		
		return objectMapper.writeValueAsString(firstlist);
	}

}
