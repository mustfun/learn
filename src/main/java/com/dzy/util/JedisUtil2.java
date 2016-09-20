/*
 * Copyright (c) 2009-2016. 上海诺诺镑客 All rights reserved.
 * @(#) JedisUtil2.java 下午6:22:31
 */
package com.dzy.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author dengzhiyuan
 * @version JedisUtil2.java
 * @since 2.0
 */

public   class  JedisUtil2  {  
	private final static Logger LOG = Logger.getLogger(JedisUtil2.class);
 
    /**  
    * 私有构造器.  
    */   
    private  JedisUtil2() {  
         
   }  
    private   static  Map<String,JedisPool> maps  =  new HashMap<String,JedisPool>();  
     
     
    /**  
    * 获取连接池.  
    * @return 连接池实例  
    */   
    private   static  JedisPool getPool(String ip, int  port) {  
       String key = ip+ ":"  +port;  
       JedisPool pool =  null ;  
        if (!maps.containsKey(key)) {  
           JedisPoolConfig config =  new  JedisPoolConfig();  
           config.setMaxIdle(RedisConfig.getMaxidle());  
           config.setMaxWaitMillis(RedisConfig.getMaxwait());  
           config.setTestOnBorrow( true );  
           config.setTestOnReturn( true );  
            try {    
                /**  
                */   
               pool =  new JedisPool(config, ip, port,RedisConfig.getTimeout());  
               maps.put(key, pool);  
           }  catch (Exception e) {  
               e.printStackTrace();  
           }  
       } else {  
           pool = maps.get(key);  
       }  
        return  pool;  
   }  
 
    /**  
    *没有绑定关系，而且只有被调用到时才会装载，从而实现了延迟加载。  
    */   
    private   static   class  RedisUtilHolder{  
        /**  
        * 静态初始化器，由JVM来保证线程安全  
        */   
        private   static  JedisUtil2 instance =  new JedisUtil2();  
   }  
 
    /**  
    *当getInstance方法第一次被调用的时候，它第一次读取  
    */   
    public   static  JedisUtil2 getInstance() {  
        return  RedisUtilHolder.instance;  
   }  
     
    /**  
    * 获取Redis实例.  
    * @return Redis工具类实例  
    */   
    public  Jedis getJedis(String ip, int  port) {  
       Jedis jedis  =  null ;  
        int  count = 0 ;  
        do {  
            try {   
               jedis = getPool(ip,port).getResource();  
                //log.info("get redis master1!");   
           }  catch  (Exception e) {  
               LOG.error( "get redis master1 failed!", e);  
                 // 销毁对象     
               getPool(ip,port).returnBrokenResource(jedis);    
           }  
           count++;  
       } while (jedis== null &&count<RedisConfig.getRetryNum());  
        return  jedis;  
   }  
 
    /**  
    * 释放redis实例到连接池.  
    * @param jedis redis实例  
    */   
    public   void  closeJedis(Jedis jedis,String ip, int  port) {  
        if (jedis !=  null ) {  
           getPool(ip,port).returnResource(jedis);  
       }  
   }  
}  