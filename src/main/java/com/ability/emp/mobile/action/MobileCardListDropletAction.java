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
import com.ability.emp.mobile.entity.MobileCardListDropletEntity;
import com.ability.emp.mobile.entity.MobileDropLetButtonEntity;
import com.ability.emp.mobile.entity.MobileDropLetConfTypeEntity;
import com.ability.emp.mobile.entity.vo.CardListDropletVo;
import com.ability.emp.mobile.entity.vo.DropLetButtonVo;
import com.ability.emp.mobile.server.MobileCardListDropletService;
import com.ability.emp.mobile.server.MobileDropLetButtonService;
import com.ability.emp.mobile.server.MobileDropLetConfTypeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/getCardListDroplet")
public class MobileCardListDropletAction {
	
	
	@Resource
	private MobileCardListDropletService mobileCardListDropletService;
	
	@Resource
	private MobileDropLetConfTypeService mobileDropLetConfTypeService;
	
	@Resource
	private MobileDropLetButtonService mobileDropLetButtonService;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	
	
	@RequestMapping("/getCardListDroplet/{dropLetId}/{dropLetConfTypeId}")
	@ResponseBody
	public String getCardListDroplet(@PathVariable("dropLetId") String dropLetId,@PathVariable("dropLetConfTypeId") String dropLetConfTypeId) throws Exception {
		List<CardListDropletVo> list = new ArrayList<CardListDropletVo>();
		List<DropLetButtonVo> list2 = new ArrayList<DropLetButtonVo>();
		Map<String,Object> map = new HashMap<String,Object>();
		MobileCardListDropletEntity me = new MobileCardListDropletEntity();
		
		if(!dropLetId.equals("1")){
			me.setDropletid(dropLetId);//确认是哪个droplet下的数据
		}
		me.setDropletconftypeid(dropLetConfTypeId);//确认是哪个位置下的数据
		
		List<MobileCardListDropletEntity> cardListDropletList = mobileCardListDropletService.getCardListDropletData(me);
		for(int i=0;i<cardListDropletList.size();i++){
			CardListDropletVo cv = new CardListDropletVo();
			cv.setCardName(cardListDropletList.get(i).getCardname());
			cv.setCardIcon(SysConstant.SERVICE_HOST+cardListDropletList.get(i).getCardicon());
			cv.setCardBackImag(SysConstant.SERVICE_HOST+cardListDropletList.get(i).getCardbackimag());
			cv.setIndex(cardListDropletList.get(i).getIndex().toString());
			list.add(cv);
		}
		
		/**
		 * 获取button
		 */
		if(cardListDropletList.size()>0 
		   && cardListDropletList.get(0).getDropletconftypeid()!=null
		   && !"".equals(cardListDropletList.get(0).getDropletconftypeid())
		){
			MobileDropLetConfTypeEntity mde = new MobileDropLetConfTypeEntity();
			mde.setDropletid(cardListDropletList.get(0).getDropletid());
			mde.setDropletconftype(cardListDropletList.get(0).getDropletconftypeid());
			//mde.setId(cardListDropletList.get(0).getDropletconftypeid());
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
