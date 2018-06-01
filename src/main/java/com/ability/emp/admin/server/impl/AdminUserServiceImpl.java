package com.ability.emp.admin.server.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ability.emp.admin.dao.AdminTaskDao;
import com.ability.emp.admin.dao.AdminUserDao;
import com.ability.emp.admin.dao.AdminWordDao;
import com.ability.emp.admin.dao.AdminWordRecordDao;
import com.ability.emp.admin.entity.AdminTaskEntity;
import com.ability.emp.admin.entity.AdminUserEntity;
import com.ability.emp.admin.entity.AdminWordEntity;
import com.ability.emp.admin.entity.AdminWordRecordEntity;
import com.ability.emp.admin.server.AdminUserService;
import com.ability.emp.admin.server.AdminWordService;
import com.ability.emp.constant.SysConstant;
import com.ability.emp.util.ExcelImportUtil;
import com.ability.emp.util.UUIDUtil;

@Service("AdminUserService") 
public class AdminUserServiceImpl implements AdminUserService{
	
	
	@SuppressWarnings("rawtypes")
	@Resource
	private AdminUserDao userDao;
	@SuppressWarnings("rawtypes")
	@Resource
	private AdminWordDao wordDao;
	@SuppressWarnings("rawtypes")
	@Resource
	private AdminWordRecordDao wordRecordDao;
	@Resource
	private AdminWordService wordService;
	@SuppressWarnings("rawtypes")
	@Resource
	private AdminTaskDao taskDao;
	

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminUserEntity> queryAll(AdminUserEntity adminUserEntity) {
		return userDao.queryAll(adminUserEntity);
	}
	
	//事务
	@SuppressWarnings("unchecked")
	@Transactional
	public void taskAppoint(String[] ids, String taskid) {
		AdminUserEntity adminUserEntity = new AdminUserEntity();
		String[] array = ids;
		List<AdminWordEntity> list = wordDao.queryAll();
		AdminWordRecordEntity wordRecordEntiy = new AdminWordRecordEntity();
		for (int i = 0; i < array.length; i++) {
			adminUserEntity.setId(array[i]);
			adminUserEntity.setTaskid(taskid);
			adminUserEntity.setIsAppoint("1");
			userDao.update(adminUserEntity);
			for (int j = 0; j < list.size(); j++) {
				wordRecordEntiy.setUserId(array[i]);
				wordRecordEntiy.setWord(list.get(j).getWord());
				wordRecordEntiy.setWordId(list.get(j).getId());
				wordRecordEntiy.setId(UUIDUtil.generateUUID());
				wordRecordEntiy.setCreateDate(new Date());
				wordRecordDao.insert(wordRecordEntiy);
			}
		}
	}
	
	
	/**
	 * 上传excel文件到临时目录后并开始解析
	 * @param fileName
	 * @param file
	 * @param userName
	 * @return
	 */
	public String importUser(String fileName,MultipartFile mfile){
		
		   File uploadDir = new  File("D:\\fileupload-Emp");
	       //创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
	       if (!uploadDir.exists()) uploadDir.mkdirs();
	       //新建一个文件
	       File tempFile = new File("D:\\fileupload-Emp\\" + new Date().getTime() + ".xlsx"); 
	       //初始化输入流
	       InputStream is = null;  
	       try{
	    	   //将上传的文件写入新建的文件中
	    	   mfile.transferTo(tempFile);
	    	   
	    	   //根据新建的文件实例化输入流
	           is = new FileInputStream(tempFile);
	    	   
	    	   //根据版本选择创建Workbook的方式
	           HSSFWorkbook  wb = null;
	           //根据文件名判断文件是2003版本还是2007版本
	           if(ExcelImportUtil.isExcel2007(fileName)){
	        	  //wb = new XSSFWorkbook(is); 
	           }else{
	        	  wb = new HSSFWorkbook(is); 
	           }
	           //读取Excel
	           return readExcel(wb,tempFile);
	      }catch(Exception e){
	          e.printStackTrace();
	      } finally{
	          if(is !=null)
	          {
	              try{
	                  is.close();
	              }catch(IOException e){
	                  is = null;    
	                  e.printStackTrace();  
	              }
	          }
	      }
          return "导入出错！请检查数据格式！";
    }
	
	
	/**
	 * 解析Excel里面的数据
	 * @param wb
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String readExcel(Workbook wb,File tempFile){
		   //拿到随机不重复code
		   @SuppressWarnings("rawtypes")
		   //Set set = GenerateRandomUtil.getRandomNumber();
		   //Object[] randlist = set.toArray();
		   //错误信息接收器
		   String errorMsg = "";
	       //得到第一个shell  
	       Sheet sheet=wb.getSheetAt(0);
	       //得到Excel的行数
	       int totalRows=sheet.getPhysicalNumberOfRows();
	       //总列数
		   int totalCells = 0; 
	       //得到Excel的列数(前提是有行数)，从第二行算起
	       if(totalRows>=2 && sheet.getRow(1) != null){
	            totalCells=sheet.getRow(1).getPhysicalNumberOfCells();
	       }
	       List<AdminUserEntity> userList = new ArrayList<AdminUserEntity>();
	       AdminUserEntity ue;
	       
	       String br = "<br/>";
	       
	       //循环Excel行数,从第二行开始。标题不入库
	       for(int r=1;r<totalRows;r++){
	    	   String rowMessage = "";
	           Row row = sheet.getRow(r);
	           if (row == null){
	        	   errorMsg += br+"第"+(r+1)+"行数据有问题，请仔细检查！";
	        	   continue;
	           }
	           ue = new AdminUserEntity();
	           //赋值主键
	           ue.setId(UUIDUtil.generateUUID());
	           //赋值未删除
	           ue.setDel(SysConstant.NO_DEL);
	           //赋值未指派
	           ue.setIsAppoint(SysConstant.NOT_ASSIGNED);
	           //赋值认证code
	           //ue.setCode(randlist[r].toString());
	           //set.remove(randlist[r]);
	           
	           String userName = "";
	           String phone = "";
	           
	           //循环Excel的列
	           for(int c = 0; c <totalCells; c++){
	               Cell cell = row.getCell(c);
	               if (null != cell){
	                   if(c==0){
	                	   userName = cell.getStringCellValue();
	                	   if(userName==null && "".equals(userName)){
	                		   rowMessage += "用户名不能为空；";
	                	   }
	                	   ue.setUserName(userName);
	                   }else if(c==1){
	                	   phone = cell.getStringCellValue();
	                	   if(phone==null && "".equals(phone)){
	                		   rowMessage += "电话不能为空；";
	                	   }
	                	   ue.setPhone(phone);
	                   }
	               }else{
	            	   rowMessage += "第"+(c+1)+"列数据有问题，请仔细检查；";
	               }
	           }
	           //拼接每行的错误提示
	           if(rowMessage!=null && !"".equals(rowMessage)){
	        	   errorMsg += br+"第"+(r+1)+"行，"+rowMessage;
	           }else{
	        	   userList.add(ue);
	           }
	       }
	       
	       //删除上传的临时文件
	       if(tempFile.exists()){
	    	   tempFile.delete();
	       }
	       
	       //全部验证通过才导入到数据库
	       if("".equals(errorMsg)){
	    	   for(AdminUserEntity userEntity : userList){
	    		   userDao.insert(userEntity);
	    	   }
	    	   errorMsg = "导入成功，共导入"+userList.size()+"条数据！";
	       }
	       return errorMsg;
	  }

	@Override
	public String findTaskName(String taskId) {
		AdminTaskEntity task = new AdminTaskEntity();
		task.setId(taskId);
		return taskDao.findTaskName(task);
	}

	@Override
	public String verifyUserAppoint(HttpServletRequest req) {
		String[] array = req.getParameterValues("id[]");
		for (int i = 0; i < array.length; i++) {
			String appoint = userDao.verifyUserAppoint(array[i]);
			if("1".equals(appoint)) {
				return appoint;
			}
		}
		return "0";
	}
}
