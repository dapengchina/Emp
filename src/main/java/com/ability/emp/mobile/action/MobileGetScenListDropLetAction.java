package com.ability.emp.mobile.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.constant.SysConstant;
import com.ability.emp.mobile.entity.MobileDropLetButtonEntity;
import com.ability.emp.mobile.entity.MobileDropLetConfTypeEntity;
import com.ability.emp.mobile.entity.MobileDropLetEntity;
import com.ability.emp.mobile.entity.MobileScenListDropLetEntity;
import com.ability.emp.mobile.entity.vo.DropLetButtonVo;
import com.ability.emp.mobile.entity.vo.ScenListDropLetVo;
import com.ability.emp.mobile.server.MobileDropLetButtonService;
import com.ability.emp.mobile.server.MobileDropLetConfTypeService;
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
	
	@Resource
	private MobileDropLetConfTypeService mobileDropLetConfTypeService;
	
	@Resource
	private MobileDropLetButtonService mobileDropLetButtonService;
	
	
    ObjectMapper objectMapper = new ObjectMapper();  
	
	
	
	@RequestMapping("/getScenListDropLetData/{dropLetId}/{dropLetConfTypeId}")
	@ResponseBody
	public String getScenListDropLetData(@PathVariable("dropLetId") String dropLetId,@PathVariable("dropLetConfTypeId") String dropLetConfTypeId) throws Exception {
		List<ScenListDropLetVo> list = new ArrayList<ScenListDropLetVo>();
		List<DropLetButtonVo> list2 = new ArrayList<DropLetButtonVo>();
		Map<String,Object> map = new HashMap<String,Object>();
		MobileScenListDropLetEntity me = new MobileScenListDropLetEntity();
		MobileDropLetEntity md = new MobileDropLetEntity();
		if(!dropLetId.equals("1")){
			me.setDropletid(dropLetId);//确认是哪个droplet下的数据
		}
		me.setPredropletcontypeid(dropLetConfTypeId);//确认是哪个类型的数据
		
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
			sv.setRelaDropLetId(scenListDropLetList.get(i).getReladropletid());
			
			md.setId(scenListDropLetList.get(i).getReladropletid());
			MobileDropLetEntity mde = mobileDropLetService.getDropLetByID(md);
			if(mde!=null){
				sv.setRelaDropLet(mde.getDropletlink());
			}
			list.add(sv);
		}
		
		/**
		 * 获取button
		 */
		if(scenListDropLetList.size()>0 
		   && scenListDropLetList.get(0).getDropletconftypeid()!=null
		   && !"".equals(scenListDropLetList.get(0).getDropletconftypeid())
		){
			MobileDropLetConfTypeEntity mde = new MobileDropLetConfTypeEntity();
			mde.setId(scenListDropLetList.get(0).getDropletconftypeid());
			MobileDropLetConfTypeEntity remde = mobileDropLetConfTypeService.getDropLetConfigType(mde);
			if(remde!=null){
				MobileDropLetButtonEntity butt = new MobileDropLetButtonEntity();
				if(remde.getFirstbuttonid()!=null && !"".equals(remde.getFirstbuttonid())){
					butt.setId(remde.getFirstbuttonid());
					MobileDropLetButtonEntity reme = mobileDropLetButtonService.getButtonByID(butt);
					if(reme!=null){
						DropLetButtonVo dv = new DropLetButtonVo();
						dv.setButtonlink(reme.getButtonlink());
						dv.setButtonname(reme.getButtonname());
						dv.setIcon(reme.getIcon());
						list2.add(dv);
					}
					
				}
                if(remde.getSecbuttonid()!=null && !"".equals(remde.getSecbuttonid())){
                	butt.setId(remde.getSecbuttonid());
                	MobileDropLetButtonEntity reme = mobileDropLetButtonService.getButtonByID(butt);
                	if(reme!=null){
						DropLetButtonVo dv = new DropLetButtonVo();
						dv.setButtonlink(reme.getButtonlink());
						dv.setButtonname(reme.getButtonname());
						dv.setIcon(reme.getIcon());
						list2.add(dv);
					}
                }
                if(remde.getThirbuttonid()!=null && !"".equals(remde.getThirbuttonid())){
                	butt.setId(remde.getThirbuttonid());
                	MobileDropLetButtonEntity reme = mobileDropLetButtonService.getButtonByID(butt);
                	if(reme!=null){
						DropLetButtonVo dv = new DropLetButtonVo();
						dv.setButtonlink(reme.getButtonlink());
						dv.setButtonname(reme.getButtonname());
						dv.setIcon(reme.getIcon());
						list2.add(dv);
					}
                }
                if(remde.getFourbuttonid()!=null && !"".equals(remde.getFourbuttonid())){
                	butt.setId(remde.getFourbuttonid());
                	MobileDropLetButtonEntity reme = mobileDropLetButtonService.getButtonByID(butt);
                	if(reme!=null){
						DropLetButtonVo dv = new DropLetButtonVo();
						dv.setButtonlink(reme.getButtonlink());
						dv.setButtonname(reme.getButtonname());
						dv.setIcon(reme.getIcon());
						list2.add(dv);
					}
                }
			}
		}
		
		
		
		//组合数据
		map.put("list", list);
		map.put("button", list2);
		return objectMapper.writeValueAsString(map);
	}

}
