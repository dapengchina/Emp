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

import com.ability.emp.mobile.entity.MobileDropLetButtonEntity;
import com.ability.emp.mobile.entity.MobileDropLetConfTypeEntity;
import com.ability.emp.mobile.entity.MobileScenarioDropLetEntity;
import com.ability.emp.mobile.entity.vo.DropLetButtonVo;
import com.ability.emp.mobile.entity.vo.ScenarioDropLetVo;
import com.ability.emp.mobile.server.MobileDropLetButtonService;
import com.ability.emp.mobile.server.MobileDropLetConfTypeService;
import com.ability.emp.mobile.server.MobileScenarioDropLetService;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/scenariodroplet")
public class MobileScenarioDropLetAction {
	
	
	@Resource
	private MobileScenarioDropLetService mobileScenarioDropLetService;
	
	@Resource
	private MobileDropLetConfTypeService mobileDropLetConfTypeService;
	
	@Resource
	private MobileDropLetButtonService mobileDropLetButtonService;
	
	
	
    ObjectMapper objectMapper = new ObjectMapper();  
	
	
	
	@RequestMapping("/getScenarioDropletData/{dropLetId}/{dropLetConfTypeId}")
	@ResponseBody
	public String getScenListDropLetData(@PathVariable("dropLetId") String dropLetId,@PathVariable("dropLetConfTypeId") String dropLetConfTypeId) throws Exception {
		List<ScenarioDropLetVo> list = new ArrayList<ScenarioDropLetVo>();
		List<DropLetButtonVo> list2 = new ArrayList<DropLetButtonVo>();
		Map<String,Object> map = new HashMap<String,Object>();
		MobileScenarioDropLetEntity me = new MobileScenarioDropLetEntity();
		
		if(!dropLetId.equals("1")){
			me.setDropletid(dropLetId);//确认是哪个droplet下的数据
		}
		me.setDropletconftypeid(dropLetConfTypeId);;//确认是哪个类型的数据
		
		List<MobileScenarioDropLetEntity> dropLetList = mobileScenarioDropLetService.getScenarioData(me);
		for(int i=0;i<dropLetList.size();i++){
			ScenarioDropLetVo sv = new ScenarioDropLetVo();
			sv.setScenarioimage(dropLetList.get(i).getScenarioimage());
			sv.setScenarioaudio(dropLetList.get(i).getScenarioaudio());
			sv.setIndex(dropLetList.get(i).getIndex());
			sv.setReladropletid(dropLetList.get(i).getReladropletid());
			sv.setReladropletconftypeid(dropLetList.get(i).getReladropletconftypeid());
			
			list.add(sv);
		}
		
		/**
		 * 获取button
		 */
		if(dropLetList.size()>0 
		   && dropLetList.get(0).getDropletconftypeid()!=null
		   && !"".equals(dropLetList.get(0).getDropletconftypeid())
		){
			MobileDropLetConfTypeEntity mde = new MobileDropLetConfTypeEntity();
			mde.setDropletconftype(dropLetList.get(0).getDropletconftypeid());//确定是哪个位置
			mde.setDropletid(dropLetList.get(0).getDropletid());//确定是哪个droplet
			
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
						dv.setReladropletid(reme.getReladropletid());
						dv.setReladropletconftype(reme.getReladropletconftype());
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
						dv.setReladropletid(reme.getReladropletid());
						dv.setReladropletconftype(reme.getReladropletconftype());
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
						dv.setReladropletid(reme.getReladropletid());
						dv.setReladropletconftype(reme.getReladropletconftype());
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
						dv.setReladropletid(reme.getReladropletid());
						dv.setReladropletconftype(reme.getReladropletconftype());
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
