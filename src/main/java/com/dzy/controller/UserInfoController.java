package com.dzy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.dzy.bizService.UserInfoHandleService;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dzy.model.ReturnBase;
import com.dzy.model.UserInfo;
import com.dzy.service.UserInfoService;

@Controller
@RequestMapping("userInfo")
public class UserInfoController {
	/**
	 * @author dengzhiyuan
	 * @version UserInfoController.java
	 * @since 2.0
	 */

	private static Logger LOG = Logger.getLogger(UserInfoController.class);
	
	@Resource
	private UserInfoService userService;
	@Resource(name="stringRedisTemplate")
	private StringRedisTemplate redisTemplate;
	@Resource
	private UserInfoHandleService userInfoHandleService;


	
	@RequestMapping("showInfo/{userId}")
	public String showUserInfo(ModelMap modelMap, @PathVariable Long userId) {
		UserInfo userInfo = userService.getUserById(userId);
		modelMap.addAttribute("userInfo", userInfo);
		return "user/showInfo";
	}
	
	@RequestMapping("showInfos")
	public @ResponseBody Object showUserInfos() {
		List<UserInfo> userInfos = userService.getUsers();
		return JSON.toJSON(userInfos).toString();
	}
	
	@ResponseBody
	@RequestMapping("testRedis")
	public ReturnBase testRedis()
	{
		ReturnBase rb=new ReturnBase();
		List<UserInfo> userInfos =null;
		if (redisTemplate.hasKey("data")) {
			String userInfoStr=redisTemplate.opsForValue().get("data");
			userInfos=(List<UserInfo>) JSON.parse(userInfoStr);
			rb.setMsg("从缓存中取出学生成功");
		}else{
			userInfos=userService.getUsers();
			redisTemplate.opsForValue().set("data", JSON.toJSONString(userInfos));
			rb.setMsg("从数据库中取出学生成功");
		}
		rb.setFlag("1");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("data", userInfos);
		rb.setData(map);
		return rb;
	}
	
	@Transactional
	@RequestMapping(value = "/testSecond", method = RequestMethod.GET)
	public ReturnBase testSecond(@RequestParam("request")String request)
	{
		try {
			Long time1=System.currentTimeMillis();
			UserInfo userInfo = userService.getUserById(Long.parseLong(request));
			if (userInfo.getUname().equals("晓明7")) {
				System.out.println(request+"----------"+time1+userInfo.getUname());
			}
			Thread.sleep(100);
			userInfo.setUname("晓明7"+System.currentTimeMillis());
			userService.updateByIdCondition(userInfo);
			Long time2=System.currentTimeMillis();
			System.out.println(time2);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * 这个类就测试一下并发情况下springmvc的表现吧，很期待
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "testLock",method = RequestMethod.GET)
	public ReturnBase testLock(HttpServletRequest request)
	{
		String id=request.getParameter("terminal");
		LOG.info(id+"号客户端正在抢占资源");
		ReturnBase rb=new ReturnBase();
		return userInfoHandleService.testRetrantReadWriteLock(id);
	}

}
