package com.dzy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dzy.dao.UserInfoMapper;
import com.dzy.model.UserInfo;
import com.dzy.service.UserInfoService;
import com.dzy.validation.annotation.NotEmpty;

@Service("userService")
public class UserInfoServiceImpl implements UserInfoService {
	/**
	 * @author dengzhiyuan
	 * @version UserInfoServiceImpl.java
	 * @since 2.0
	 */

	@Autowired
	private UserInfoMapper userInfoMapper;

	
	@Override
	public UserInfo getUserById(int id) {
		return userInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<UserInfo> getUsers() {
		return userInfoMapper.getAllUsers();
	}

	@Override
	public int insert(UserInfo userInfo) {

		int result = userInfoMapper.insert(userInfo);

		System.out.println(result);
		return result;
	}

	@Override
	public int updateByIdCondition(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return userInfoMapper.updateByIdCondition(userInfo);
	}

}
