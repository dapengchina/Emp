package com.ability.emp.mobile.action;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.constant.SysConstant;
import com.ability.emp.mobile.entity.MobileDropLetEntity;
import com.ability.emp.mobile.entity.MobileScenListDropLetEntity;
import com.ability.emp.mobile.entity.vo.ScenListDropLetVo;
import com.ability.emp.mobile.server.MobileDropLetService;
import com.ability.emp.mobile.server.MobileScenListDropLetService;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/getScenListDropLetData")
public class MobileGetScenListDropLetAction {
	
	
	
	@Resource
	private MobileScenListDropLetService mobileScenListDropLetService;
	
	@Resource
	private MobileDropLetService mobileDropLetService;
	
	
    ObjectMapper objectMapper = new ObjectMapper();  
	
	
	
	@RequestMapping("/getScenListDropLetData/{dropLetId}/{dropLetConfTypeId}")
	@ResponseBody
	public String getScenListDropLetData(@PathVariable("dropLetId") String dropLetId,@PathVariable("dropLetConfTypeId") String dropLetConfTypeId) throws Exception {
		List<ScenListDropLetVo> list = new ArrayList<ScenListDropLetVo>();
		MobileScenListDropLetEntity me = new MobileScenListDropLetEntity();
		MobileDropLetEntity md = new MobileDropLetEntity();
		me.setDropletid(dropLetId);//确认是哪个droplet下的数据
		me.setDropletconftypeid(dropLetConfTypeId);//通过参数获取button
		List<MobileScenListDropLetEntity> scenListDropLetList = mobileScenListDropLetService.getScenListDropletData(me);
		for(int i=0;i<scenListDropLetList.size();i++){
			ScenListDropLetVo sv = new ScenListDropLetVo();
			sv.setScenName(scenListDropLetList.get(i).getScenname());
			sv.setScenIcon(SysConstant.SERVICE_HOST+scenListDropLetList.get(i).getScenicon());
			sv.setCompPerc(scenListDropLetList.get(i).getCompperc());
			sv.setAverageScore(scenListDropLetList.get(i).getAveragescore());
			sv.setCurrentPoint(scenListDropLetList.get(i).getCurrentpoint());
			sv.setTotalPoint(scenListDropLetList.get(i).getTotalpoint());
			sv.setIndex(scenListDropLetList.get(i).getIndex().toString());
			
			md.setId(scenListDropLetList.get(i).getReladropletid());
			MobileDropLetEntity mde = mobileDropLetService.getDropLetByID(md);
			if(mde!=null){
				sv.setRelaDropLet(mde.getDropletlink());
			}
			list.add(sv);
		}

		return objectMapper.writeValueAsString(list);
	}

}
