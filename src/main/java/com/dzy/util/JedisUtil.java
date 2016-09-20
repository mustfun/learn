/*
 * Copyright (c) 2009-2016. 上海诺诺镑客 All rights reserved.
 * @(#) JedisUtil.java 下午2:18:42
 */
package com.dzy.util;

import redis.clients.jedis.Jedis;

/**
 * @author dengzhiyuan
 * @version JedisUtil.java
 * @since 2.0
 */

// 单例模式获取redis
public class JedisUtil {
	public static Jedis jedis = null;

	private JedisUtil() {

	}

	public static synchronized Jedis getInstance() {
		if (jedis == null) {
			jedis = new Jedis("localhost", 6379);
		}
		return jedis;
	}
}
