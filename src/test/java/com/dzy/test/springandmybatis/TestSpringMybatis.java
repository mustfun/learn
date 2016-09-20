package com.dzy.test.springandmybatis;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.dzy.model.UserInfo;
import com.dzy.service.UserInfoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath*:/META-INF/spring/spring-bootstrap.xml"})
public class TestSpringMybatis {
	/**
	 * @author dengzhiyuan
	 * @version TestSpringMybatis.java
	 * @since 2.0
	 */
	private static final Logger LOG = Logger.getLogger(TestSpringMybatis.class);
	
	@Autowired
	private UserInfoService userService;
	
	   
  @Test  
    public void testQueryById1() {  
  //      UserInfo userInfo = userService.getUserById(1);  
   //     System.out.println(JSON.toJSON(userInfo));
    //    LOG.info(JSON.toJSON(userInfo));  
    }  
  
    //@Test  
    public void testQueryAll() {  
        List<UserInfo> userInfos = userService.getUsers();  
        LOG.info(JSON.toJSON(userInfos));  
    }  
  
    //@Test
    public void testInsert() {  
        UserInfo userInfo = new UserInfo();  
        userInfo.setUname("xiaoming");  
        userInfo.setUnumber(4+"");
        int result = userService.insert(userInfo);  
        System.out.println(result);  
    }  

}
