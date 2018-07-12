package com.ability.emp.admin.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ability.emp.admin.entity.AdminThesauresPramEntity;
import com.ability.emp.admin.entity.AdminWordEntity;
import com.ability.emp.admin.entity.vo.AdminWordVo;
import com.ability.emp.admin.server.AdminThesauresPramService;
import com.ability.emp.admin.server.AdminWordService;
import com.ability.emp.util.ExcelImportUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@CrossOrigin // 解决跨域请求
@Controller
@RequestMapping("/admin/word")
public class AdminWordListAction {
	
	
	@Resource
	private AdminWordService wordService;
	
	@Resource
	private AdminThesauresPramService adminThesauresPramService;

	ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 返回首页
	 * 
	 * @param wordEntity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("")
	public String listPage(HttpServletRequest request, HttpServletResponse response) {
		return "wordlist";
	}

	/**
	 * 返回数据
	 * 
	 * @param wordEntity
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryAll")
	@ResponseBody
	public String queryAll(int pageSize, int pageNumber,AdminWordEntity awe) throws Exception {
		// 第一个参数当前页码，第二个参数每页条数
		PageHelper.startPage(pageNumber, pageSize);
		List<AdminWordEntity> data = wordService.queryWordAll(awe);
		
		Map<String, Object> map = new HashMap<String, Object>();
		@SuppressWarnings("unchecked")
		PageInfo<AdminWordEntity> page = new PageInfo(data);
		
		for(int i=0;i<data.size();i++){
			AdminThesauresPramEntity atpe_temp = adminThesauresPramService.getByID(data.get(i).getThesaurusType());
			if(atpe_temp!=null){
				data.get(i).setThesaurusName(atpe_temp.getName());
			}
		}
		
		map.put("total", page.getTotal());
		map.put("rows", data);
		return objectMapper.writeValueAsString(map);
	}

	@RequestMapping(value = "/wordedit/{id}")
	@ResponseBody
	public String queryWordById(@PathVariable("id") String id) throws Exception {
		List<AdminWordEntity> word = wordService.queryWordById(id);
		return objectMapper.writeValueAsString(word.get(0));
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateBatch",method = RequestMethod.POST)
	@ResponseBody
	public boolean updateWord(@RequestParam(value = "ids[]") String[] ids,String thesauresType) throws Exception {
		AdminWordVo adminWordVo = new AdminWordVo();
		@SuppressWarnings("rawtypes")
		List list = new ArrayList();
		for(int i=0;i<ids.length;i++){
			list.add(ids[i]);
		}
		adminWordVo.setWordids(list);
		adminWordVo.setThesaurusType(thesauresType);
		try{
			wordService.updateBatch(adminWordVo);
			return true;
		}catch(Exception e){
			return false;
		}
		
	}
	
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public String updateWord(HttpServletRequest request, HttpServletResponse response,AdminWordEntity awe) throws Exception {
		int i = wordService.update(awe); 
		if(i>0){
			return "0";
		}else{
			return "1";
		}
	}

	/**
	 * 导入单词
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/importWord", method = RequestMethod.POST)
	public String importWord(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		// 判断文件是否为空
		if (file == null) {
			map.put("msg", "文件不能为空！");
			return objectMapper.writeValueAsString(map);
		}

		// 获取文件名
		String fileName = file.getOriginalFilename();

		// 验证文件名是否合格
		if (!ExcelImportUtil.validateExcel(fileName)) {
			map.put("msg", "文件必须是excel格式！");
			return objectMapper.writeValueAsString(map);
		}

		// 进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
		long size = file.getSize();
		if (fileName == null || fileName.equals("") || size == 0) {
			map.put("msg", "文件不能为空！");
			return objectMapper.writeValueAsString(map);
		}
        
		try{
			// 批量导入
			String message = wordService.importWord(fileName, file);
			map.put("msg", message);
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return "importusersuccess";
	}

	@RequestMapping("/importSuccess")
	public String importSuccess() throws Exception {
		return "importwordsuccess";
	}
}
