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
import com.ability.emp.mobile.entity.MobileDictationDropLetEntity;
import com.ability.emp.mobile.entity.MobileDropLetButtonEntity;
import com.ability.emp.mobile.entity.MobileDropLetConfTypeEntity;
import com.ability.emp.mobile.entity.MobileDropLetEntity;
import com.ability.emp.mobile.entity.vo.DictationVo;
import com.ability.emp.mobile.entity.vo.DropLetButtonVo;
import com.ability.emp.mobile.server.MobileCardListDropletService;
import com.ability.emp.mobile.server.MobileDictationDropLetService;
import com.ability.emp.mobile.server.MobileDropLetButtonService;
import com.ability.emp.mobile.server.MobileDropLetConfTypeService;
import com.ability.emp.mobile.server.MobileDropLetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/getDictation")
public class MobileDictationDropLetAction {
	
	
	
	
	@Resource
	private MobileDictationDropLetService mobileDictationDropLetService;
	
	@Resource
	private MobileCardListDropletService mobileCardListDropletService;
	
	@Resource
	private MobileDropLetConfTypeService mobileDropLetConfTypeService;
	
	@Resource
	private MobileDropLetButtonService mobileDropLetButtonService;
	
	@Resource
	private MobileDropLetService mobileDropLetService;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	
	@RequestMapping("/getDictation/{dropLetId}/{dropLetConfTypeId}")
	@ResponseBody
	public String getDictation(@PathVariable("dropLetId") String dropLetId,@PathVariable("dropLetConfTypeId") String dropLetConfTypeId) throws JsonProcessingException{
		
		List<DropLetButtonVo> buttonList = new ArrayList<DropLetButtonVo>();
		Map<String,Object> map = new HashMap<String,Object>();
		MobileDictationDropLetEntity me = new MobileDictationDropLetEntity();
		MobileDropLetEntity md = new MobileDropLetEntity();
		
		if(!dropLetId.equals("1")){
			me.setDropletid(dropLetId);//确认是哪个droplet下的数据
		}
		me.setDropletconftypeid(dropLetConfTypeId);//确认是哪个位置下的数据
		DictationVo dication = new DictationVo();
		MobileDictationDropLetEntity dictationEntity = mobileDictationDropLetService.getDictationData(me);
		if(dictationEntity!=null){
			dication.setImage(SysConstant.SERVICE_HOST+dictationEntity.getImage());
			dication.setAudio(SysConstant.SERVICE_HOST+dictationEntity.getAudio());
			dication.setQuestion(dictationEntity.getQuestion());
			dication.setAnwser(dictationEntity.getAnwser());
			dication.setReladropletid(dictationEntity.getReladropletid());
			dication.setReladropletcontypeid(dictationEntity.getReladropletcontypeid());
			
			md.setId(dictationEntity.getReladropletid());
			MobileDropLetEntity mo = mobileDropLetService.getDropLetByID(md);
			if(mo!=null){
				dication.setDropletlink(mo.getDropletlink());
			}
		}
		
		/**
		 * 获取button
		 */
		if(dictationEntity!=null
		   && dictationEntity.getDropletconftypeid()!=null
		   && !"".equals(dictationEntity.getDropletconftypeid())
		){
			MobileDropLetConfTypeEntity mde = new MobileDropLetConfTypeEntity();
			mde.setDropletid(dictationEntity.getDropletid());
			mde.setDropletconftype(dictationEntity.getDropletconftypeid());
			
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
		map.put("dictation", dication);
		map.put("button", buttonList);
		return objectMapper.writeValueAsString(map);
	}

}
