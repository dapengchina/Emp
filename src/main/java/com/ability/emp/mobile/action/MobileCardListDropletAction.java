package com.ability.emp.mobile.action;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.constant.SysConstant;
import com.ability.emp.mobile.entity.MobileCardListDropletEntity;
import com.ability.emp.mobile.entity.vo.CardListDropletVo;
import com.ability.emp.mobile.server.MobileCardListDropletService;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/getCardListDroplet")
public class MobileCardListDropletAction {
	
	
	@Resource
	private MobileCardListDropletService mobileCardListDropletService;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	
	
	@RequestMapping("/getCardListDroplet/{dropLetId}/{dropLetConfTypeId}")
	@ResponseBody
	public String getCardListDroplet(@PathVariable("dropLetId") String dropLetId,@PathVariable("dropLetConfTypeId") String dropLetConfTypeId) throws Exception {
		List<CardListDropletVo> list = new ArrayList<CardListDropletVo>();
		MobileCardListDropletEntity me = new MobileCardListDropletEntity();
		me.setDropletid(dropLetId);
		me.setDropletconftypeid(dropLetConfTypeId);
		List<MobileCardListDropletEntity> cardListDropletList = mobileCardListDropletService.getCardListDropletData(me);
		for(int i=0;i<cardListDropletList.size();i++){
			CardListDropletVo cv = new CardListDropletVo();
			cv.setCardName(cardListDropletList.get(i).getCardname());
			cv.setCardIcon(SysConstant.SERVICE_HOST+cardListDropletList.get(i).getCardicon());
			cv.setCardBackImag(SysConstant.SERVICE_HOST+cardListDropletList.get(i).getCardbackimag());
			cv.setCurrentScore(cardListDropletList.get(i).getCurrentscore());
			cv.setIndex(cardListDropletList.get(i).getIndex().toString());
			list.add(cv);
		}
		
		return objectMapper.writeValueAsString(list);
	}

}
