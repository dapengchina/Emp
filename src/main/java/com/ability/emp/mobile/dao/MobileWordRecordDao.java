package com.ability.emp.mobile.dao;

import com.ability.emp.base.BaseDao;
import com.ability.emp.mobile.entity.MobileWordRecordEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MobileWordRecordDao <T> extends BaseDao<T> {
	List<MobileWordRecordEntity> selectWord(String id);
}
