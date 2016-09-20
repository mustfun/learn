package com.dzy.service;

import java.util.List;

import com.dzy.model.UserInfo;


public interface UserInfoService {
	/**
	 * @author dengzhiyuan
	 * @version UserInfoService.java
	 * @since 2.0
	 */
	
	UserInfo getUserById(int id);  
    
    List<UserInfo> getUsers();  
      
    int insert(UserInfo userInfo);  
    
    int updateByIdCondition(UserInfo userInfo);
}
