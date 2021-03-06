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
import com.ability.emp.mobile.entity.MobileChoiceAudioDropletEntity;
import com.ability.emp.mobile.entity.MobileDropLetButtonEntity;
import com.ability.emp.mobile.entity.MobileDropLetConfTypeEntity;
import com.ability.emp.mobile.entity.MobileDropLetEntity;
import com.ability.emp.mobile.entity.vo.ChoiceAudioDropletVo;
import com.ability.emp.mobile.entity.vo.DropLetButtonVo;
import com.ability.emp.mobile.server.MobileChoiceAudioDropletService;
import com.ability.emp.mobile.server.MobileDropLetButtonService;
import com.ability.emp.mobile.server.MobileDropLetConfTypeService;
import com.ability.emp.mobile.server.MobileDropLetService;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/getChoiceAudioDroplet")
public class MobileChoiceAudioDropletAction {
	
	
	
	
	@Resource
	private MobileDropLetConfTypeService mobileDropLetConfTypeService;
	
	@Resource
	private MobileDropLetButtonService mobileDropLetButtonService;
	
	@Resource
	private MobileDropLetService mobileDropLetService;
	
	@Resource
	private MobileChoiceAudioDropletService mobileChoiceAudioDropletService;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	
	
	@RequestMapping("/getChoiceAudioDroplet/{dropLetId}/{dropLetConfTypeId}")
	@ResponseBody
	public String getChoiceAudioDroplet(@PathVariable("dropLetId") String dropLetId,@PathVariable("dropLetConfTypeId") String dropLetConfTypeId) throws Exception {
		List<DropLetButtonVo> list = new ArrayList<DropLetButtonVo>();
		Map<String,Object> map = new HashMap<String,Object>();
		MobileChoiceAudioDropletEntity me = new MobileChoiceAudioDropletEntity();
		MobileDropLetEntity md = new MobileDropLetEntity();
		if(!dropLetId.equals("1")){
			me.setDropletid(dropLetId);//确认是哪个droplet下的数据
		}
		me.setDropletconftypeid(dropLetConfTypeId);;//确认是哪个位置的数据
		MobileChoiceAudioDropletEntity choiceAudioEntity = mobileChoiceAudioDropletService.getChoiceAudioDropletData(me);
		
		ChoiceAudioDropletVo cv = new ChoiceAudioDropletVo();
		if(choiceAudioEntity!=null){
			cv.setReladropletid(choiceAudioEntity.getReladropletid());
			cv.setReladropletconftypeid(choiceAudioEntity.getReladropletconftypeid());
			cv.setChoiceaudioimage(SysConstant.SERVICE_HOST+choiceAudioEntity.getChoiceaudioimage());
			cv.setChoiceaudio(SysConstant.SERVICE_HOST+choiceAudioEntity.getChoiceaudio());
			
			cv.setChoiceaudioindexa(choiceAudioEntity.getChoiceaudioindexa());
			cv.setChoiceaudioflaga(choiceAudioEntity.getChoiceaudioflaga().equals("false")?false:true);
			cv.setChoiceaudioa(SysConstant.SERVICE_HOST+choiceAudioEntity.getChoiceaudioa());
			
			cv.setChoiceaudioindexb(choiceAudioEntity.getChoiceaudioindexb());
			cv.setChoiceaudioflagb(choiceAudioEntity.getChoiceaudioflagb().equals("false")?false:true);
			cv.setChoiceaudiob(SysConstant.SERVICE_HOST+choiceAudioEntity.getChoiceaudiob());
			
			cv.setChoiceaudioindexc(choiceAudioEntity.getChoiceaudioindexc());
			cv.setChoiceaudioflagc(choiceAudioEntity.getChoiceaudioflagc().equals("false")?false:true);
			cv.setChoiceaudioc(SysConstant.SERVICE_HOST+choiceAudioEntity.getChoiceaudioc());
			
			md.setId(choiceAudioEntity.getReladropletid());
			MobileDropLetEntity mo = mobileDropLetService.getDropLetByID(md);
			if(mo!=null){
				cv.setDropLetLink(mo.getDropletlink());
			}
		}
			
		/**
		 * 获取button
		 */
		if(choiceAudioEntity!=null 
		   && choiceAudioEntity.getDropletconftypeid()!=null
		   && !"".equals(choiceAudioEntity.getDropletconftypeid())
		){
			MobileDropLetConfTypeEntity mde = new MobileDropLetConfTypeEntity();
			mde.setDropletconftype(choiceAudioEntity.getDropletconftypeid());//确定是哪个位置
			mde.setDropletid(choiceAudioEntity.getDropletid());//确定是哪个droplet
			
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
						list.add(dv);
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
						list.add(dv);
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
						list.add(dv);
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
						list.add(dv);
					}
                }
			}
		}
		
		//组合数据
		map.put("choiceAudio", cv);
		map.put("button", list);
		return objectMapper.writeValueAsString(map);
	}


}
