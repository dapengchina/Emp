package com.ability.emp.admin.server.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ability.emp.admin.dao.AdminWordDao;
import com.ability.emp.admin.entity.AdminWordEntity;
import com.ability.emp.admin.server.AdminWordService;
import com.ability.emp.constant.SysConstant;
import com.ability.emp.util.ExcelImportUtil;
import com.ability.emp.util.UUIDUtil;

@Service("AdminWordService")
public class AdminWordServiceImpl implements AdminWordService {

	@SuppressWarnings("rawtypes")
	@Resource
	private AdminWordDao wordDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminWordEntity> queryAll() {
		return wordDao.queryAll();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int update(AdminWordEntity wordEntiy) {
		return wordDao.update(wordEntiy);
	}

	/**
	 * 上传excel文件到临时目录后并开始解析
	 * 
	 * @param fileName
	 * @param file
	 * @param userName
	 * @return
	 */
	public String importWord(String fileName, MultipartFile mfile) {

		File uploadDir = new File("C:\\fileupload-Emp");
		// 创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
		if (!uploadDir.exists())
			uploadDir.mkdirs();
		// 新建一个文件
		File tempFile = new File("C:\\fileupload-Emp\\" + new Date().getTime() + ".xlsx");
		// 初始化输入流
		InputStream is = null;
		try {
			// 将上传的文件写入新建的文件中
			mfile.transferTo(tempFile);

			// 根据新建的文件实例化输入流
			is = new FileInputStream(tempFile);

			// 根据版本选择创建Workbook的方式
			HSSFWorkbook wb = null;
			// 根据文件名判断文件是2003版本还是2007版本
			if (ExcelImportUtil.isExcel2007(fileName)) {
				// wb = new XSSFWorkbook(is);
			} else {
				wb = new HSSFWorkbook(is);
			}
			// 读取Excel
			return readExcel(wb, tempFile);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					//System.out.println("=========="+"流关闭");
					is.close();
				} catch (IOException e) {
					is = null;
					e.printStackTrace();
				}
			}
		}
		return "导入出错！请检查数据格式！";
	}

	/**
	 * 解析Excel里面的数据
	 * 
	 * @param wb
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String readExcel(Workbook wb, File tempFile) {
		// 错误信息接收器
		String errorMsg = "";
		// 得到第一个shell
		Sheet sheet = wb.getSheetAt(0);
		// 得到Excel的行数
		int totalRows = sheet.getPhysicalNumberOfRows();
		//int totalRows = sheet.getLastRowNum();
		// 总列数
		int totalCells = 0;
		// 得到Excel的列数(前提是有行数)，从第二行算起
		if (totalRows >= 2 && sheet.getRow(1) != null) {
			totalCells = sheet.getRow(1).getPhysicalNumberOfCells();
		}
		List<AdminWordEntity> wordList = new ArrayList<AdminWordEntity>();
		AdminWordEntity we;

		String br = "<br/>";

		// 循环Excel行数,从第二行开始。标题不入库
		for (int r = 1; r < totalRows; r++) {
			String rowMessage = "";
			Row row = sheet.getRow(r);
			if (row == null) {
				errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
				continue;
			}
			we = new AdminWordEntity();
			// 赋值主键
			we.setId(UUIDUtil.generateUUID());
			// 单词
			String word = "";
			// 音标
			String symbol = "";
			// 单词释义
			String interpretation = "";
			// 对应句子
			String sentence = "";
			// 错误释义1
			String errInterpretation1 = "";
			// 错误释义2
			String errInterpretation2 = "";
			// 错误释义3
			String errInterpretation3 = "";
			// 赋值未删除
			we.setDel(SysConstant.NO_DEL);

			// 对应句子
			we.setSentence("");
			we.setErrInterpretation1("");
			we.setErrInterpretation2("");
			we.setErrInterpretation3("");
			// 循环Excel的列
			for (int c = 0; c < totalCells; c++) {
				Cell cell = row.getCell(c);
				if (null != cell) {
					if (c == 0) {
						word = cell.getStringCellValue();
						if (word == null && "".equals(word)) {
							rowMessage += "单词不能为空；";
						}
						we.setWord(word);
					} else if (c == 1) {
						symbol = cell.getStringCellValue();
						if (symbol == null && "".equals(symbol)) {
							rowMessage += "音标不能为空；";
						}
						we.setSymbol(symbol);
					} else if (c == 2) {
						interpretation = cell.getStringCellValue();
						if (interpretation == null && "".equals(interpretation)) {
							rowMessage += "单词释义不能为空；";
						}
						we.setInterpretation(interpretation);
					} else if (c == 3) {
						sentence = cell.getStringCellValue();
						if (sentence == null && "".equals(sentence)) {
							rowMessage += "对应句子不能为空；";
						}
						we.setSentence(sentence);
					} else if (c == 4) {
						errInterpretation1 = cell.getStringCellValue();
						if (errInterpretation1 == null && "".equals(errInterpretation1)) {
							rowMessage += "错误单词释义1不能为空；";
						}
						we.setErrInterpretation1(errInterpretation1);
					} else if (c == 5) {
						errInterpretation2 = cell.getStringCellValue();
						if (errInterpretation2 == null && "".equals(errInterpretation2)) {
							rowMessage += "错误单词释义2不能为空；";
						}
						we.setErrInterpretation2(errInterpretation2);
					} else if (c == 6) {
						errInterpretation3 = cell.getStringCellValue();
						if (errInterpretation3 == null && "".equals(errInterpretation3)) {
							rowMessage += "错误单词释义3不能为空；";
						}
						we.setErrInterpretation3(errInterpretation3);
					}
				} else {
					rowMessage += "第" + (c + 1) + "列数据有问题，请仔细检查；";
				}
			}
			// 拼接每行的错误提示
			if (rowMessage != null && !"".equals(rowMessage)) {
				errorMsg += br + "第" + (r + 1) + "行，" + rowMessage;
			} else {
				wordList.add(we);
			}
		}

		// 删除上传的临时文件
		if (tempFile.exists()) {
			tempFile.delete();
		}

		// 全部验证通过才导入到数据库
		if ("".equals(errorMsg)) {
			for (AdminWordEntity wordEntity : wordList) {
				wordDao.insert(wordEntity);
			}
			errorMsg = "导入成功，共导入" + wordList.size() + "条数据！";
		}
		System.out.println(errorMsg);
		return errorMsg;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminWordEntity> queryWordById(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return wordDao.queryWordById(map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminWordEntity> queryWordAll(AdminWordEntity awe) {
		return wordDao.queryWordAll(awe);
	}

	@Override
	public int updateBatch(Map map) {
		return wordDao.updateBatch(map);
	}

}
