package com.ability.emp.base;

import java.util.List;
import java.util.Map;

public interface BaseDao<T> {
	
	
    int insert(T t);  
   
    int delete(String id);  
    
    int update(T t);  
    
    Integer count(Map<String,Object> map);
   
    T queryById(String id);  
    
    List<T> queryAll();  
    
    

}
