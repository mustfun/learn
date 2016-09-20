package com.dzy.util;

import org.springframework.context.annotation.Description;

/**
 * @author dengzhiyuan
 * @version RedisConfig.java
 * @since 2.0
 */
@Deprecated  //已经整合到spring中
public class RedisConfig {
	
	private static final int maxactive=1;
	private static final int maxidle=2;
	private static final long maxwait=3l;
	private static final int  timeout=10000;
	
	
	public static int getMaxactive(){
		return maxactive;
	}
	
	public static int getMaxidle()
	{
		return maxidle;
	}
	
	public static Long getMaxwait()
	{
		return maxwait;
	}
	
	public static int getRetryNum()
	{
		return 24;
	}
	public static int getTimeout()
	{
		return timeout;
	}

}
