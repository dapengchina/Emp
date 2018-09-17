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
import com.ability.emp.mobile.entity.MobileDropLetEntity;
import com.ability.emp.mobile.entity.MobileMatchDropLetEntity;
import com.ability.emp.mobile.entity.vo.DropLetButtonVo;
import com.ability.emp.mobile.entity.vo.MatchDropLetVo;
import com.ability.emp.mobile.server.MobileCardListDropletService;
import com.ability.emp.mobile.server.MobileDropLetButtonService;
import com.ability.emp.mobile.server.MobileDropLetConfTypeService;
import com.ability.emp.mobile.server.MobileDropLetService;
import com.ability.emp.mobile.server.MobileMatchDropLetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/getMatch")
public class MobileMatchDropLetAction {
	
	
	
	@Resource
	private MobileCardListDropletService mobileCardListDropletService;
	
	@Resource
	private MobileDropLetConfTypeService mobileDropLetConfTypeService;
	
	@Resource
	private MobileDropLetButtonService mobileDropLetButtonService;
	
	@Resource
	private MobileDropLetService mobileDropLetService;
	
	@Resource
	private MobileMatchDropLetService mobileMatchDropLetService;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	
	@RequestMapping("/getMatch/{dropLetId}/{dropLetConfTypeId}")
	@ResponseBody
	public String getMatch(@PathVariable("dropLetId") String dropLetId,@PathVariable("dropLetConfTypeId") String dropLetConfTypeId) throws JsonProcessingException{
		
		List<DropLetButtonVo> buttonList = new ArrayList<DropLetButtonVo>();
		Map<String,Object> map = new HashMap<String,Object>();
		MobileMatchDropLetEntity me = new MobileMatchDropLetEntity();
		MobileDropLetEntity md = new MobileDropLetEntity();
		
		if(!dropLetId.equals("1")){
			me.setDropletid(dropLetId);//确认是哪个droplet下的数据
		}
		me.setDropletconftypeid(dropLetConfTypeId);//确认是哪个位置下的数据
		MobileMatchDropLetEntity matchEntity = mobileMatchDropLetService.getMatchData(me);
		MatchDropLetVo mv = new MatchDropLetVo();
		if(matchEntity!=null){
			mv.setAudio(matchEntity.getAudio());
			mv.setMatchtype(matchEntity.getMatchtype());
			mv.setReladropletid(matchEntity.getReladropletid());
			mv.setReladropletconftypeid(matchEntity.getReladropletconftypeid());
			
			mv.setImage(matchEntity.getImage());
			mv.setIfimageright(matchEntity.getIfimageright());
			
			mv.setSentence(matchEntity.getSentence());
			mv.setIfsentenceright(matchEntity.getIfsentenceright());
			
			mv.setAnswersentence(matchEntity.getAnswersentence());
			mv.setIfanswersentenceright(matchEntity.getIfanswersentenceright());
			
			mv.setAnsweraudio(matchEntity.getAnsweraudio());
			mv.setIfansweraudioright(matchEntity.getIfansweraudioright());
			
			md.setId(matchEntity.getReladropletid());
    		MobileDropLetEntity mde = mobileDropLetService.getDropLetByID(md);
    		if(mde!=null){
    			mv.setDropletlink(mde.getDropletlink());
    		}
		}
		
		
		/**
		 * 获取button
		 */
		if(matchEntity!=null 
		   && matchEntity.getDropletconftypeid()!=null
		   && !"".equals(matchEntity.getDropletconftypeid())
		){
			MobileDropLetConfTypeEntity mde = new MobileDropLetConfTypeEntity();
			mde.setDropletid(matchEntity.getDropletid());
			mde.setDropletconftype(matchEntity.getDropletconftypeid());
			
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
						buttonList.add(dv);
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
						buttonList.add(dv);
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
						buttonList.add(dv);
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
						buttonList.add(dv);
					}
                }
			}
		}
		
		//组合数据
		map.put("match", mv);
		map.put("button", buttonList);
		return objectMapper.writeValueAsString(map);
	}
}
