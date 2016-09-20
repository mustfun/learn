package com.dzy.test.redis;

import redis.clients.jedis.Jedis;

/**
 * @author dengzhiyuan
 * @version RedisConnTest.java
 * @since 2.0
 */

public class RedisConnTest {

			//一开始连接不上，后来关闭linux的防火墙功能就可以了
	       static String constr = "192.168.17.222" ;
	       public static Jedis getRedis(){
	          Jedis jedis = new Jedis(constr,6380) ;
	            
	          return jedis ;
	      }
	       public static void main(String[] args){
	            Jedis j = RedisConnTest. getRedis() ;
	            String clusterInfo = j.ping();
	            System.out.println(clusterInfo);

	            System.out.println(j.clientList());
	      }
	}
