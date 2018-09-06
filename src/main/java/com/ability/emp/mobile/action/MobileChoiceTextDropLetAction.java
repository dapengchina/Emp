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
import com.ability.emp.mobile.entity.MobileChoiceTextDropLetEntity;
import com.ability.emp.mobile.entity.MobileDropLetButtonEntity;
import com.ability.emp.mobile.entity.MobileDropLetConfTypeEntity;
import com.ability.emp.mobile.entity.MobileDropLetEntity;
import com.ability.emp.mobile.entity.vo.ChoiceTextDropLetVo;
import com.ability.emp.mobile.entity.vo.DropLetButtonVo;
import com.ability.emp.mobile.server.MobileChoiceTextDropLetService;
import com.ability.emp.mobile.server.MobileDropLetButtonService;
import com.ability.emp.mobile.server.MobileDropLetConfTypeService;
import com.ability.emp.mobile.server.MobileDropLetService;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/getChoiceDropLet")
public class MobileChoiceTextDropLetAction {
	
	@Resource
	private MobileDropLetConfTypeService mobileDropLetConfTypeService;
	
	@Resource
	private MobileDropLetButtonService mobileDropLetButtonService;
	
	@Resource
	private MobileDropLetService mobileDropLetService;
	
	@Resource
	private MobileChoiceTextDropLetService mobileChoiceTextDropLetService;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	
	
	@RequestMapping("/getChoiceDropLet/{dropLetId}/{dropLetConfTypeId}")
	@ResponseBody
	public String getChoiceDropLet(@PathVariable("dropLetId") String dropLetId,@PathVariable("dropLetConfTypeId") String dropLetConfTypeId) throws Exception {
		
		List<DropLetButtonVo> list2 = new ArrayList<DropLetButtonVo>();
		Map<String,Object> map = new HashMap<String,Object>();
		MobileChoiceTextDropLetEntity me = new MobileChoiceTextDropLetEntity();
		MobileDropLetEntity md = new MobileDropLetEntity();
		if(!dropLetId.equals("1")){
			me.setDropletid(dropLetId);//确认是哪个droplet下的数据
		}
		me.setDropletconftypeid(dropLetConfTypeId);;//确认是哪个位置的数据
		
		MobileChoiceTextDropLetEntity choiceEntity = mobileChoiceTextDropLetService.getChoiceDropLetData(me);
		
		ChoiceTextDropLetVo sv = new ChoiceTextDropLetVo();
		sv.setChoicetextaudio(SysConstant.SERVICE_HOST+choiceEntity.getChoicetextaudio());
		sv.setChoicetextimage(SysConstant.SERVICE_HOST+choiceEntity.getChoicetextimage());
		
		sv.setOptionindexa(choiceEntity.getOptionindexa());
		sv.setOptiontexta(choiceEntity.getOptiontexta());
		sv.setOptionflaga(choiceEntity.getOptionflaga().equals("false")?false:true);
		
		sv.setOptionindexb(choiceEntity.getOptionindexb());
		sv.setOptiontextb(choiceEntity.getOptiontextb());
		sv.setOptionflagb(choiceEntity.getOptionflagb().equals("false")?false:true);
		
		sv.setOptionindexc(choiceEntity.getOptionindexc());
		sv.setOptiontextc(choiceEntity.getOptiontextc());
		sv.setOptionflagc(choiceEntity.getOptionflagc().equals("false")?false:true);
		
		sv.setReladropletconftypeid(choiceEntity.getReladropletconftypeid());
		sv.setReladropletid(choiceEntity.getReladropletid());
		
		md.setId(choiceEntity.getReladropletid());
		MobileDropLetEntity mo = mobileDropLetService.getDropLetByID(md);
		if(mo!=null){
			sv.setDropLetLink(mo.getDropletlink());
		}
			
		/**
		 * 获取button
		 */
		if(choiceEntity!=null 
		   && choiceEntity.getDropletconftypeid()!=null
		   && !"".equals(choiceEntity.getDropletconftypeid())
		){
			MobileDropLetConfTypeEntity mde = new MobileDropLetConfTypeEntity();
			mde.setDropletconftype(choiceEntity.getDropletconftypeid());//确定是哪个位置
			mde.setDropletid(choiceEntity.getDropletid());//确定是哪个droplet
			
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
		map.put("choice", sv);
		map.put("button", list2);
		return objectMapper.writeValueAsString(map);
	}

}
