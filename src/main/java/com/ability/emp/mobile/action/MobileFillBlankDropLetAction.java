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
import com.ability.emp.mobile.entity.MobileFillBlankAnswerEntity;
import com.ability.emp.mobile.entity.MobileFillBlankDropLetEntity;
import com.ability.emp.mobile.entity.MobileFillBlankQuestionEntity;
import com.ability.emp.mobile.entity.bean.FillBlankAnswerBean;
import com.ability.emp.mobile.entity.bean.FillBlankQuestionBean;
import com.ability.emp.mobile.entity.vo.DropLetButtonVo;
import com.ability.emp.mobile.entity.vo.FillBlankDropLetVo;
import com.ability.emp.mobile.server.MobileDropLetButtonService;
import com.ability.emp.mobile.server.MobileDropLetConfTypeService;
import com.ability.emp.mobile.server.MobileDropLetService;
import com.ability.emp.mobile.server.MobileFillBlankAnswerService;
import com.ability.emp.mobile.server.MobileFillBlankDropLetService;
import com.ability.emp.mobile.server.MobileFillBlankQuestionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/getFillBlankData")
public class MobileFillBlankDropLetAction {
	
	
	
	@Resource
	private MobileDropLetConfTypeService mobileDropLetConfTypeService;
	
	@Resource
	private MobileDropLetButtonService mobileDropLetButtonService;
	
	@Resource
	private MobileDropLetService mobileDropLetService;
	
	@Resource
	private MobileFillBlankDropLetService mobileFillBlankDropLetService;
	
	@Resource
	private MobileFillBlankQuestionService mobileFillBlankQuestionService;
	
	@Resource
	private MobileFillBlankAnswerService mobileFillBlankAnswerService;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	
	
	@RequestMapping("/getFillBlankData/{dropLetId}/{dropLetConfTypeId}")
	@ResponseBody
	public String getFillBlankData(@PathVariable("dropLetId") String dropLetId,@PathVariable("dropLetConfTypeId") String dropLetConfTypeId) throws Exception {
		List<DropLetButtonVo> list = new ArrayList<DropLetButtonVo>();
		Map<String,Object> map = new HashMap<String,Object>();
		MobileFillBlankDropLetEntity me = new MobileFillBlankDropLetEntity();
		MobileDropLetEntity md = new MobileDropLetEntity();
		if(!dropLetId.equals("1")){
			me.setDropletid(dropLetId);//确认是哪个droplet下的数据
		}
		me.setDropletconftypeid(dropLetConfTypeId);;//确认是哪个位置的数据
		
		MobileFillBlankDropLetEntity fillBlankEntity = mobileFillBlankDropLetService.getFillBlankData(me);
		
		FillBlankDropLetVo sv = new FillBlankDropLetVo();
		sv.setDropletid(fillBlankEntity.getDropletid());
		sv.setDropletconftypeid(fillBlankEntity.getDropletconftypeid());
		sv.setReladropletid(fillBlankEntity.getReladropletid());
		sv.setReladropletconftypeid(fillBlankEntity.getReladropletconftypeid());
		sv.setImage(SysConstant.SERVICE_HOST+fillBlankEntity.getImage());
		
		md.setId(fillBlankEntity.getReladropletid());
		MobileDropLetEntity mo = mobileDropLetService.getDropLetByID(md);
		if(mo!=null){
			sv.setDropLetLink(mo.getDropletlink());
		}
		/**
		 * 获取问题
		 */
		MobileFillBlankQuestionEntity mq = new MobileFillBlankQuestionEntity();
		mq.setFillblankdataid(fillBlankEntity.getId());
		List<MobileFillBlankQuestionEntity> questList = mobileFillBlankQuestionService.getFillBlankQuestion(mq);
		int[] emptyposition = new int[2];
		int emptyindex=0;
		List<FillBlankQuestionBean> questBeanList = new ArrayList<FillBlankQuestionBean>();
		for(int i=0;i<questList.size();i++){
			FillBlankQuestionBean questBean = new FillBlankQuestionBean();
			questBean.setTit(questList.get(i).getTit());
			questBean.setCorrect(questList.get(i).getCorrect());
			questBean.setNum(questList.get(i).getNum());
			questBean.setIfem(questList.get(i).getIfem().equals("false")?false:true);
			
			//组装空位数组
			if(questList.get(i).getIfem().equals("true")){
				emptyposition[emptyindex]=i;
				emptyindex=emptyindex+1;
			}
			
			
			questBeanList.add(questBean);
		}
		sv.setQuest(questBeanList);
		sv.setEmptyposition(emptyposition);
		
		/**
		 * 获取答案
		 */
		MobileFillBlankAnswerEntity ans = new MobileFillBlankAnswerEntity();
		ans.setFillblankdataid(fillBlankEntity.getId());
		List<MobileFillBlankAnswerEntity> fillBlankAnswerList = mobileFillBlankAnswerService.getFillBlankAnswer(ans);
		List<FillBlankAnswerBean> answerBeanlist = new ArrayList<FillBlankAnswerBean>();
		for(int j=0;j<fillBlankAnswerList.size();j++){
			FillBlankAnswerBean fb = new FillBlankAnswerBean();
			fb.setTit(fillBlankAnswerList.get(j).getTit());
			fb.setSubnum(fillBlankAnswerList.get(j).getSubnum());
			
			answerBeanlist.add(fb);
		}
		sv.setAnswer(answerBeanlist);
		
		
			
		/**
		 * 获取button
		 */
		if(fillBlankEntity!=null 
		   && fillBlankEntity.getDropletconftypeid()!=null
		   && !"".equals(fillBlankEntity.getDropletconftypeid())
		){
			MobileDropLetConfTypeEntity mde = new MobileDropLetConfTypeEntity();
			mde.setDropletconftype(fillBlankEntity.getDropletconftypeid());//确定是哪个位置
			mde.setDropletid(fillBlankEntity.getDropletid());//确定是哪个droplet
			
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
		map.put("fillBlank", sv);
		map.put("button", list);
		return objectMapper.writeValueAsString(map);
	}

}
