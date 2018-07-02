package com.ability.emp.admin.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ability.emp.admin.entity.AdminThesauresPramEntity;
import com.ability.emp.admin.server.AdminThesauresPramService;

@CrossOrigin // 解决跨域请求
@Controller
@RequestMapping("/admin/thesauresParamlist")
public class AdminThesauresParamAction {
	@Resource
	private AdminThesauresPramService adminThesauresPramService;

	/**
	 * 返回数据
	 * 
	 * @param AdminThesauresPramEntity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryAll")
	@ResponseBody
	public List<AdminThesauresPramEntity> queryAll() throws Exception {
		// 第一个参数当前页码，第二个参数每页条数
		return adminThesauresPramService.queryAll();
	}

}
