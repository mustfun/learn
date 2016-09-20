package com.dzy.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author dengzhiyuan
 * @version RedisTemplateUtil.java
 * @since 2.0
 */
/**
 * 
 * 打算用反射加泛型
 *
 */
public class RedisTemplateUtil {

	@Resource(name="stringRedisTemplate")
	private static StringRedisTemplate redisTemplate;
	private final String className;
	
	public RedisTemplateUtil(String className) {
		this.className=className;
	}
	
	public static boolean setList(String redisKey,List<? extends Object> list)
	{
		boolean flag=true;
		try {
			for (Object object : list) {
				redisTemplate.opsForList().rightPush(redisKey,object.toString());
			}
		} catch (Exception e) {
			flag=false;
		}
		return flag;
	}
	
	public static List<Object> getListByKey(String redisKey)
	{
		List<Object> list=new ArrayList<Object>();
		Long listSize=redisTemplate.opsForList().size(redisKey);
		for (int i = 0; i <listSize ; i++) {
			list.add(redisTemplate.opsForList().rightPop(redisKey));
		}
		return list;
	}
	
	public static boolean exists(String redisKey){
		return redisTemplate.hasKey(redisKey);
	}
	
}
