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
import com.ability.emp.mobile.entity.MobileSortDropLetEntity;
import com.ability.emp.mobile.entity.bean.SortDropLetBean;
import com.ability.emp.mobile.entity.vo.DropLetButtonVo;
import com.ability.emp.mobile.entity.vo.SortDropLetVo;
import com.ability.emp.mobile.server.MobileDropLetButtonService;
import com.ability.emp.mobile.server.MobileDropLetConfTypeService;
import com.ability.emp.mobile.server.MobileDropLetService;
import com.ability.emp.mobile.server.MobileSortDropLetService;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/getSortData")
public class MobileSortDropLetAction {
	
	
	
	@Resource
	private MobileDropLetConfTypeService mobileDropLetConfTypeService;
	
	@Resource
	private MobileDropLetButtonService mobileDropLetButtonService;
	
	@Resource
	private MobileDropLetService mobileDropLetService;
	
	@Resource
	private MobileSortDropLetService mobileSortDropLetService;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	
	
	@RequestMapping("/getSortData/{dropLetId}/{dropLetConfTypeId}")
	@ResponseBody
	public String getSortData(@PathVariable("dropLetId") String dropLetId,@PathVariable("dropLetConfTypeId") String dropLetConfTypeId) throws Exception {
		List<SortDropLetBean> beanList = new ArrayList<SortDropLetBean>();
		List<DropLetButtonVo> list2 = new ArrayList<DropLetButtonVo>();
		Map<String,Object> map = new HashMap<String,Object>();
		MobileSortDropLetEntity me = new MobileSortDropLetEntity();
		MobileDropLetEntity md = new MobileDropLetEntity();
		if(!dropLetId.equals("1")){
			me.setDropletid(dropLetId);//确认是哪个droplet下的数据
		}
		me.setDropletconftypeid(dropLetConfTypeId);;//确认是哪个位置的数据
		
		List<MobileSortDropLetEntity> sortEntity = mobileSortDropLetService.getSortData(me);
		SortDropLetVo sv = new SortDropLetVo();
		if(sortEntity!=null && sortEntity.size()>0){
			for(int i=0;i<sortEntity.size();i++){
				SortDropLetBean sb = new SortDropLetBean();
				sb.setKey(sortEntity.get(i).getKey());
				sb.setNum(sortEntity.get(i).getNum());
				sb.setTit(sortEntity.get(i).getSentence());
				
				beanList.add(sb);
			}
			sv.setReladropletid(sortEntity.get(0).getReladropletid());
			sv.setReladropletconftypeid(sortEntity.get(0).getReladropletconftypeid());
			sv.setSentence(beanList);
		}
		
		md.setId(sortEntity.get(0).getReladropletid());
		MobileDropLetEntity mo = mobileDropLetService.getDropLetByID(md);
		if(mo!=null){
			sv.setDropLetLink(mo.getDropletlink());
		}
			
		/**
		 * 获取button
		 */
		if(sortEntity!=null 
		   && sortEntity.get(0).getDropletconftypeid()!=null
		   && !"".equals(sortEntity.get(0).getDropletconftypeid())
		){
			MobileDropLetConfTypeEntity mde = new MobileDropLetConfTypeEntity();
			mde.setDropletconftype(sortEntity.get(0).getDropletconftypeid());//确定是哪个位置
			mde.setDropletid(sortEntity.get(0).getDropletid());//确定是哪个droplet
			
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
		map.put("sortdata", sv);
		map.put("button", list2);
		return objectMapper.writeValueAsString(map);
	}

}
