package com.dzy.data.redis;

import org.springframework.data.redis.connection.RedisSentinelConfiguration;

/**
 * @author dengzhiyuan
 * @version SentinelConfiguration.java
 * @since 2.0
 * Sentinel  redis 集群配置文件类
 */

public class SentinelConfiguration extends RedisSentinelConfiguration {

	/**
	 * 获得主redis的名称
	 * @return
	 */
	public String getMasterName()
	{
		 return getMaster() == null ? "" : getMaster().getName();
	}
	
	public void setMasterName(String masterName)
	{
		setMaster(masterName);
	}
}
