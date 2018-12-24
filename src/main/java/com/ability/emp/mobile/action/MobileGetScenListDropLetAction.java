package com.ability.emp.mobile.action;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
import com.ability.emp.mobile.entity.MobileSubTaskEntity;
import com.ability.emp.mobile.entity.vo.DropLetButtonVo;
import com.ability.emp.mobile.entity.vo.ScenListDropLetVo;
import com.ability.emp.mobile.server.MobileDropLetButtonService;
import com.ability.emp.mobile.server.MobileDropLetConfTypeService;
import com.ability.emp.mobile.server.MobileDropLetService;
import com.ability.emp.mobile.server.MobileScenListDropLetService;
import com.ability.emp.mobile.server.MobileSubTaskService;
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
	
	@Resource
	private MobileSubTaskService mobileSubTaskService;
	
	
    ObjectMapper objectMapper = new ObjectMapper();  
	
	
	
	@RequestMapping("/getScenListDropLetData/{dropLetId}/{dropLetConfTypeId}/{userid}")
	@ResponseBody
	public String getScenListDropLetData(@PathVariable("dropLetId") String dropLetId,@PathVariable("dropLetConfTypeId") String dropLetConfTypeId,@PathVariable("userid") String userid) throws Exception {
		List<ScenListDropLetVo> list = new ArrayList<ScenListDropLetVo>();
		List<DropLetButtonVo> list2 = new ArrayList<DropLetButtonVo>();
		Map<String,Object> map = new HashMap<String,Object>();
		MobileScenListDropLetEntity me = new MobileScenListDropLetEntity();
		MobileDropLetEntity md = new MobileDropLetEntity();
		MobileSubTaskEntity subtask = new MobileSubTaskEntity();
		int currentPoint = 0;
		double done = 0.0;
		double total = 0.0;
		Double score = 0.0;
		DecimalFormat df = (DecimalFormat)NumberFormat.getInstance();
		DecimalFormat df1 = (DecimalFormat)NumberFormat.getInstance();
		if(!dropLetId.equals("1")){
			me.setDropletid(dropLetId);//确认是哪个droplet下的数据
		}
		me.setReladropletcontypeid(dropLetConfTypeId);//确认是哪个类型的数据
		
		List<MobileScenListDropLetEntity> scenListDropLetList = mobileScenListDropLetService.getScenListDropletData(me);
		for(int i=0;i<scenListDropLetList.size();i++){
			ScenListDropLetVo sv = new ScenListDropLetVo();
			sv.setScenName(scenListDropLetList.get(i).getScenname());
			sv.setScenIcon(SysConstant.SERVICE_HOST+scenListDropLetList.get(i).getScenicon());
			sv.setIndex(scenListDropLetList.get(i).getIndex().toString());
			sv.setRelaDropLetId(scenListDropLetList.get(i).getReladropletid());
			sv.setId(scenListDropLetList.get(i).getId());
			sv.setReladropletcontypeid(scenListDropLetList.get(i).getReladropletcontypeid());
			
			//处理用户得分
			if(
				userid!=null && 
				scenListDropLetList.get(i).getReladropletid()!=null &&
				scenListDropLetList.get(i).getReladropletcontypeid()!=null &&
				!"".equals(scenListDropLetList.get(i).getReladropletid()) &&
				!"".equals(scenListDropLetList.get(i).getReladropletcontypeid())
				
			){
				subtask.setUserid(userid);
				subtask.setState(SysConstant.TASK_STATE0);
				subtask.setDropletid(scenListDropLetList.get(i).getReladropletid());
				subtask.setDropletconftypeid(scenListDropLetList.get(i).getReladropletcontypeid());
				List<MobileSubTaskEntity> subtasklist = mobileSubTaskService.getSubTaskList(subtask);
				if(subtasklist!=null && subtasklist.size()>0){
					total = subtasklist.size();
					sv.setTotalPoint(String.valueOf(subtasklist.size() * SysConstant.CARD_STAR));
					for(int j=0;j<subtasklist.size();j++){
						if(subtasklist.get(j).getIfpass().equals(SysConstant.CARD_STUDY_PASS)){
							currentPoint = currentPoint + SysConstant.CARD_STAR;
							done = done + 1;
						}
						if(subtasklist.get(j).getScore()!=null){
							score = score + Double.parseDouble(subtasklist.get(j).getScore());
						}
					}
					//可以设置精确几位小数
					df.setMaximumFractionDigits(0);
					df1.setMaximumFractionDigits(1);
					//模式四舍五入
					df.setRoundingMode(RoundingMode.HALF_UP);
					sv.setCurrentPoint(String.valueOf(currentPoint));
					sv.setAverageScore(String.valueOf(df1.format(score/total)));
					double accuracy_num = done / total;
					sv.setCompPerc(Double.parseDouble(df.format(accuracy_num * 100)));
				}else{
					sv.setTotalPoint("0");
					sv.setCurrentPoint("0");
					sv.setCompPerc(0.0);
					sv.setAverageScore("0");
				}
			}else{
				sv.setTotalPoint("0");
				sv.setCurrentPoint("0");
				sv.setCompPerc(0.0);
				sv.setAverageScore("0");
			}
			
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
			mde.setDropletconftype(scenListDropLetList.get(0).getDropletconftypeid());//确定是哪个位置
			mde.setDropletid(scenListDropLetList.get(0).getDropletid());//确定是哪个droplet
			
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
