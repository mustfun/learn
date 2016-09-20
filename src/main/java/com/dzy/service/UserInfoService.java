package com.dzy.service;

import java.util.List;

import com.dzy.model.UserInfo;


public interface UserInfoService {
	/**
	 * @author dengzhiyuan
	 * @version UserInfoService.java
	 * @since 2.0
	 */
	
	UserInfo getUserById(Long id);
    
    List<UserInfo> getUsers();  
      
    int insert(UserInfo userInfo);  
    
    int updateByIdCondition(UserInfo userInfo);

    List<UserInfo> selectByPrimiryKey(Long id);
}
