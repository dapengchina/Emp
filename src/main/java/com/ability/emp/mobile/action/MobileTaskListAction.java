package com.ability.emp.mobile.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ability.emp.mobile.entity.MobileTaskEntity;
import com.ability.emp.mobile.server.MobileTaskService;

@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/task")
public class MobileTaskListAction {
	@Resource
	private MobileTaskService mobileTaskService;
	//学习日历接口
	@RequestMapping("/studyCalendar")
	@ResponseBody
	public String studyCalendar(String id) throws Exception {
		List<MobileTaskEntity> data = mobileTaskService.selectStudyCalendar(id);
        Date startDate=data.get(0).getStartDate();
        Date endDate=data.get(0).getEndDate();
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start = sDateFormat.format(startDate);
        String end = sDateFormat.format(endDate);
		return start+","+end;
	}
}
