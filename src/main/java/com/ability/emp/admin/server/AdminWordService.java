package com.ability.emp.admin.server;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import com.ability.emp.admin.entity.AdminWordEntity;
import com.ability.emp.admin.entity.vo.AdminWordVo;

public interface AdminWordService {
	
	List<AdminWordEntity> queryAll();
	
	String importWord(String fileName,MultipartFile mfile);
	
	int update(AdminWordEntity wordEntiy); 

	List<AdminWordEntity> queryWordById(String id);
	
	List<AdminWordEntity> queryWordAll(AdminWordEntity awe);
	
	int updateBatch(AdminWordVo adminWordVo);
}
