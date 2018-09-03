package com.ability.emp.mobile.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.mobile.entity.MobileProcessconfEntity;
import com.ability.emp.mobile.entity.vo.MobileProcessconfVo;
import com.ability.emp.mobile.server.MobileProcessconfService;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/getProcessconf")
public class MobileProcessconfAction {
	
	
	
	@Resource
	private MobileProcessconfService mobileProcessconfService;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	
	
	@RequestMapping("/getProcessconf")
	@ResponseBody
	public String getProcessconf() throws Exception {
		List<MobileProcessconfVo> list = new ArrayList<MobileProcessconfVo>();
		MobileProcessconfEntity me = new MobileProcessconfEntity();
		List<MobileProcessconfEntity> mobileProcessconfList = mobileProcessconfService.getProcessconf(me);
		for(int i=0;i<mobileProcessconfList.size();i++){
			MobileProcessconfVo mv = new MobileProcessconfVo();
			mv.setId(mobileProcessconfList.get(i).getId());
			mv.setDropletconftypeid(mobileProcessconfList.get(i).getDropletconftypeid());
			mv.setDropletid(mobileProcessconfList.get(i).getDropletid());
			mv.setIndex(mobileProcessconfList.get(i).getIndex());
			mv.setReladropletid(mobileProcessconfList.get(i).getReladropletid());
			list.add(mv);
		}
		
		return objectMapper.writeValueAsString(list);
	}

}
