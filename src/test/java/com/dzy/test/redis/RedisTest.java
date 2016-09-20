package com.dzy.test.redis;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Transaction;

import com.dzy.util.JedisUtil;

/**
 * @author dengzhiyuan
 * @version RedisTest.java
 * @since 2.0
 * @function 本类作为redis客户端jedis的学习类，spring会对其进行封装
 */
public class RedisTest {

	private final static Logger LOG = Logger.getLogger(RedisTest.class);

	public static void main(String[] args) {

	}

	// 测试连接到redis服务器的函数
	public void testConnectToServer() {
		// redis地址
		Jedis jedis = new Jedis("localhost");
		LOG.info("已经连上了redis服务器");
		LOG.info("检查服务器是否正在运行");
		LOG.info("服务器运行中" + jedis.ping()); // 返回时pong？？？
	}

	// 测试redis存储string
	public void testSaveString() {
		Jedis jedis = new Jedis("localhost");
		LOG.info("已经连上了redis服务器");
		LOG.info("将string设置到redis中");
		jedis.set("name", "itar");
		LOG.info("获得存入的数据然后打印出来");
		LOG.info("存入的数据是" + jedis.get("name"));
	}

	// 测试redis集合例子
	public void testSaveList() {
		Jedis jedis = new Jedis("localhost");
		LOG.info("已经连上了redis服务器");
		LOG.info("将list设置到redis中");
		jedis.lpush("name-list", "itar");
		jedis.lpush("name-list", "alin");
		jedis.lpush("name-list", "star");
		LOG.info("获得存入的数据然后打印出来");
		// 返回0-5个
		List<String> list = jedis.lrange("name-list", 0, 5);
		for (int i = 0; i < list.size(); i++) {
			LOG.info("刚刚存入的是" + list.get(i));
		}
	}

	// 测试取出所有的键,打印出name,name-list
	public void testKeys() {
		Jedis jedis = new Jedis("localhost");
		LOG.info("已经连上了redis服务器");
		LOG.info("将keys设置到redis中");
		List<String> list = (List<String>) jedis.keys("*");
		for (int i = 0; i < list.size(); i++) {
			LOG.info("刚刚存入的是" + list.get(i));
		}
	}

	// jedis中pipeline（管道）操作
	public void testPipeline() {
		Jedis jedis = new Jedis("localhost");
		LOG.info("已经连上了redis服务器");
		Pipeline pl = jedis.pipelined();
		LOG.info("request+1||request incr");
		String key = "0";
		pl.incr(key);
		LOG.info("request+1||request incr");
		pl.incr(key);
		LOG.info("结束pipeline，并从相应中获得数据");
		List<Object> responses = pl.syncAndReturnAll();
		if (responses == null || responses.isEmpty()) {
			LOG.error("response没有收到任何响应");
			throw new RuntimeException("没有收到任何相应");
		}
		for (Object object : responses) {
			LOG.debug("Response" + object.toString());
			// 注意这里的object类型是Long
		}

		LOG.info("============pipeline测试2====================");
		long start = new Date().getTime();
		jedis.flushDB();// 清空库
		Pipeline newp = jedis.pipelined();
		for (int i = 0; i < 1000; i++) {
			newp.set("age2" + i, i + "");
			LOG.debug(newp.get("age2" + i));
		}
		newp.sync();// 这段代码获取所有的response
		long end = new Date().getTime();
		LOG.info("使用pipeline花费了" + (end - start) + "ms");

	}

	// jedis事务
	public void testTransaction() {
		Jedis jedis = new Jedis("localhost", 6379);
		LOG.info("已经连上了redis服务器");
		Transaction tx = jedis.multi();
		for (int i = 0; i < 1000; i++) {
			tx.set("itar" + i, "hao" + i);
		}
		List<Object> result = tx.exec();
		jedis.disconnect();
	}

	// 测绘
	public void testALL() {
		Jedis jedis = JedisUtil.getInstance();
		try {
			jedis.set("name", "itar");
			String ss = jedis.get("name");
			// 把另一个字符串设置到itar之后
			jedis.append("name", "black");
			LOG.debug("打印出来" + jedis.get("name"));
			// 覆盖掉原来的数据
			jedis.set("name", "another name");
			LOG.debug("覆盖之后的name" + jedis.get("name"));
			// 删除key对应的记录
			jedis.del("name");
			LOG.info("name的名称是" + jedis.get("name"));// nulll
			// 设置一个或者多个键值对
			jedis.mset("key1", "value1", "key2", "value2");
			// 清空所有的key
			jedis.flushAll();
			LOG.info("数据库大小是" + jedis.dbSize());
			// mget获得一个或者多个key
			List<String> getList = jedis.mget("key1", "key2", "key3");
			for (String str : getList) {
				LOG.info("mget取出的数据是" + str);
			}
			// DECR key将key中存储的数字值减一
			// DECRBY key decrement ,减去decrement个单位
			// INCR key将数值加一
			// INCRBY key increment将key所存储的值加上增量increment

			// jedis.decr("num");

			LOG.info("\n" + "=================hash操作===========");
			// hset就是一个hashset key field value,键是website
			jedis.hset("website", "google", "www.google.com");
			// hmset key field value同时将多个field-value加入到hash表的key中
			Map<String, String> map = new HashMap<String, String>();
			map.put("cardid", "123456");
			map.put("username", "itar");
			map.put("age", "21");
			jedis.hmset("itar-info", map);
			// hget key field返回hash表中给定field对应的值
			LOG.debug("用户名叫itar的年龄是" + jedis.hget("itar-info", "age"));
			// HGETALL key返回hashkey中，所有的域和值
			Map<String, String> allMap = jedis.hgetAll("itar-info");
			for (Map.Entry<String, String> entry : allMap.entrySet()) {
				// 用entry来遍历map
				LOG.info(entry.getKey() + "::" + entry.getValue());
			}
			// HDEL key field删除hash中key某一个field
			jedis.hdel("itar-info", "age");// 删除了age
			// HLEN key返回hash表key中的域的数量
			jedis.hlen("itar-info");
			// Hexists key field查看hash表中指定的field是否存在
			jedis.hexists("itar-info", "username");// true
			// HINCRBY key field increment,给key的field+increment
			jedis.hincrBy("itar-info", "age", 2l);// 年龄加2
			// hkeys返回所有key中的域
			jedis.hkeys("itar-info");// 返回所有的键 ,set集合
			jedis.hvals("itar-info");// 对应键所有的值
			LOG.info("===============下面是list操作========================");
			// LPUSH key value将value插入到列表key的表头中
			jedis.lpush("mylist", "abc");
			jedis.lpush("mylist", "xzc");
			jedis.lpush("mylist", "erf");
			jedis.lpush("mylist", "bnh");
			// Lrange key start top返回列表key中指定区间内的元素，区间以偏移量start到stop为主
			List allList = jedis.lrange("mylist", 0, -1);// -1表示返回所有元素
			for (int i = 0; i < allList.size(); i++) {
				LOG.debug("每一个元素是" + allList.get(i));
			}
			LOG.info("列表key的长度是" + jedis.llen("mylist"));
			// LLEN key返回列表的长度
			// lrem key count value根据参数返回count的值，移除列表中域参数value相等的元素
			jedis.lrem("mylist", 3l, "bnh");
			// list排序
			jedis.sort("mylist");
			// 修改列表里面单个值
			jedis.lset("mylist", 0, "hello list!");// 第0个修改
			// 获取指定下标的值
			jedis.lindex("mylist", 0);// 获取list里面第一个
			// 删除列表指定下标记的值
			jedis.lrem("mylist", 1, "bnh");// 删除了bnh了
			// 删除列表中所定义区间以外的数据
			jedis.ltrim("mylist", 0, 1);// 相当于这个列表只保存第0和第1个
			// 列表出栈
			jedis.lpop("mylist");

			LOG.info("\n"
					+ "===========================set=========================");
			;
			jedis.sadd("myset", "s1");
			jedis.sadd("myset", "s2");
			jedis.sadd("myset", "s3");
			jedis.sadd("myset", "s4");
			jedis.sadd("myset", "s5");
			// srem key memter移除set中的member元素
			jedis.srem("myset", "s5"); // s5被移除
			// smembers key返回集合key中的所有成员
			Set set = jedis.smembers("myset");
			Iterator ite = set.iterator();
			while (ite.hasNext()) {
				Object obj1 = ite.next();
				LOG.info("myset中的元素是" + obj1);
			}
			// sismember key member判断member元素是否是集合key的成员
			boolean isInSet = jedis.sismember("myset", "s4");

			// scard key返回集合key的基数（集合中元素的数量）
			jedis.scard("myset");
			// smove source destination member将member元素从source集合移动到destination中
			// jedis.smove("集合1的key", "集合2的元素", "某个元素");
			// 删除指定的元素
			jedis.srem("myset", "s5");// s5被删除了
			// 出栈
			String allElements = jedis.spop("myset");
			// 两个set集合的交集
			jedis.sinter("myset1", "myset2");
			// 两个set集合的并集
			jedis.sunion("set1", "set2");
			// 两个set集合的差集
			jedis.sdiff("set1", "set2");
			LOG.info("==================排序set===sortedset===================");
			jedis.zadd("hackers", 1940, "Alan kay");
			jedis.zadd("hackers", 1941, "star kay");
			jedis.zadd("hackers", 1942, "black kay");
			jedis.zadd("hackers", 1943, "daddy kay");
			jedis.zadd("hackers", 1944, "cpukon kay");
			Set<String> setValues = jedis.zrange("hackers", 0, -1);
			LOG.debug("set是" + setValues.toString());
			Set<String> setValues2 = jedis.zrevrange("hackers", 0, -1);
			LOG.debug("set是" + setValues2);
			// 元素个数
			jedis.zcard("hackers");
			// 获取value是daddy kay的元素下标
			jedis.zscore("hackers", "daddy kay");
			// 集合所有元素
			jedis.zrange("hackers", 0, -1);
			// 删除元素
			LOG.info(jedis.zrem("hackers", "cpukon kay"));
			// 删除在1940-1942的元素
			LOG.info(jedis.zcount("hackers", 1940, 1942));

			LOG.info("===================查漏补缺=======================");
			// keys中传入的值可以是通配符
			LOG.info(jedis.keys("*"));// 这样会返回库中所有的key
			LOG.info(jedis.keys("*name"));// 返回库中长得像*name的
			LOG.info(jedis.del("sanamge"));// 删除key为sanamge的，不存在返回0
			LOG.info(jedis.ttl("sname"));// 返回给定key的有效时间，如果是-1表示永有效
			LOG.info(jedis.setex("timekey", 10, "min")); // 存活10分钟
			Thread.sleep(5000);// 睡眠5秒钟，后，神域存活时间《5秒钟了
			LOG.info(jedis.ttl("timekey"));
			LOG.info(jedis.exists("timekey"));// 判断这个key是否存在
			LOG.info(jedis.rename("timekey", "newtimekey"));// 重命名

		} catch (Exception e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 测试排序
	 */
	public void testSort1() {
		// 排序默认以数字作为对象，值会被解释为双精度浮点数，然后进行比较
		Jedis jedis = JedisUtil.getInstance();
		// 一般的sort方法，最简单的sort使用方法是sort key了
		jedis.lpush("mylist", "1");
		jedis.lpush("mylist", "4");
		jedis.lpush("mylist", "0");
		jedis.lpush("mylist", "3");
		jedis.lpush("mylist", "8");
		// List<String> list = jedis.sort("mylist");//默认是升序
		SortingParams sortParams = new SortingParams();
		sortParams.desc();
		// 修饰符进行排序
		sortParams.limit(0, 2);// 可以用于分页查询，只要2个
		sortParams.desc();// 已经改成降序
		List<String> list = jedis.sort("mylist", sortParams);// 默认是升序
		for (int i = 0; i < list.size(); i++) {
			LOG.info(list.get(i));
		}
		jedis.flushDB();// 清空数据库

	}

	/**
	 * list结合hash进行排序
	 */
	public void testSort2() {
		Jedis jedis = JedisUtil.getInstance();
		jedis.del("user:66", "user:55", "userlist");
		jedis.lpush("userlist", "33");
		jedis.lpush("userlist", "22");
		jedis.lpush("userlist", "55");
		jedis.lpush("userlist", "11");

		jedis.hset("user:66", "name", "66");
		jedis.hset("user:55", "name", "55");
		jedis.hset("user:33", "name", "33");
		jedis.hset("user:22", "name", "79");
		jedis.hset("user:11", "name", "24");
		jedis.hset("user:11", "add", "beijing");
		jedis.hset("user:22", "add", "shanghai");
		jedis.hset("user:33", "add", "guangzhou");
		jedis.hset("user:55", "add", "chongqing");
		jedis.hset("user:66", "add", "xi'an");
		// ok数据已经准备好，下面准备排序参数
		SortingParams sortingParams = new SortingParams();
		sortingParams.get("user:*->name");
		sortingParams.get("user:*->add");
		// sortingParams.by("user:*->name");
		// sortingParams.get("#");
		List<String> sortList = jedis.sort("userlist", sortingParams);
		for (String item : sortList) {
			LOG.info("item...." + item);
		}

	}

	/**
	 * set结合String的排序
	 */
	public void testSort3() {
		Jedis jedis = JedisUtil.getInstance();
		jedis.sadd("tom:friend:list", "123"); // tom的好友列表
		jedis.sadd("tom:friend:list", "456");
		jedis.sadd("tom:friend:list", "789");
		jedis.sadd("tom:friend:list", "101");

		jedis.set("score:uid:123", "1000"); // 好友对应的成绩
		jedis.set("score:uid:456", "6000");
		jedis.set("score:uid:789", "100");
		jedis.set("score:uid:101", "5999");

		jedis.set("uid:123", "{'uid':123,'name':'lucy'}"); // 好友的详细信息
		jedis.set("uid:456", "{'uid':456,'name':'jack'}");
		jedis.set("uid:789", "{'uid':789,'name':'jay'}");
		jedis.set("uid:101", "{'uid':101,'name':'jolin'}");
		
		SortingParams sortingParameters =  new  SortingParams();  
        sortingParameters.desc();
        sortingParameters.get("#");
        sortingParameters.get("uid:*");
        sortingParameters.get( "score:uid:*" );  
        sortingParameters.by( "score:uid:*" );  
           
         // 通过这个uid:* get score:uid:*   
        List<String> result = jedis.sort( "tom:friend:list", sortingParameters);  
         for  (String item : result) {  
            System.out.println( "item..."  + item);  
        }  
	}
	
	
	//获取最新评论
	public List<String> get_latest_comments(int start,int num_items)
	{
		//获取最新评论
		//lpush latest.comments<ID>
		//然后我们将列表裁剪为指定的长度，因此redis只需要保存最新的5000条评论即可
		//ltrim latest.comments 0 5000
		Jedis jedis=JedisUtil.getInstance();
		
		//从start开始，截取后面-1个
		List<String> id_list = jedis.lrange("latest.comments",start, start+num_items-1);
		if (id_list.size()<num_items) {
			//id_list=sql.execute("select id from .. limt ...");
			//或者用封装好的方法代替
		}
		return id_list;
	}
	
	/**
	 * 利用lrange对list进行分页操作
	 */
	public void queryPageBy(int pageNum,int pageSize)
	{
		//传进来当前页和页面大小
		Jedis jedis=JedisUtil.getInstance();
		jedis.del("a");
		for (int i = 0; i < 30; i++) {
			jedis.rpush("a", i+"");
		}
		//已经设置好了list,rpush是放在尾部，lpush是放在头部
		int start=pageSize*(pageNum-1);//redis中list的元素从0开始
		int end=start+pageSize-1;
		List<String> results = jedis.lrange("a", start, end);
		for (String str:results) {
			LOG.debug("str"+str);
		}
	}
	
	/**
	 * 获取jedis连接池
	 * 需要使用的时候自行注释掉config,然后设置好config
	 */
	private   static  JedisPool getPool(String ip, int  port) {  
	    JedisPoolConfig config =  new  JedisPoolConfig();
	    //config.setMaxActive(RedisConfig.getMaxactive());  
	    //config.setMaxIdle(RedisConfig.getMaxidle());  
	    //config.setMaxWait(RedisConfig.getMaxwait());  
	    config.setTestOnBorrow( true );  
	    config.setTestOnReturn( true );  
	    JedisPool pool=null;
	     try {    
	         /**  
	         */   
	        //pool =  new JedisPool(config, ip, port,RedisConfig.getTimeout());  
	    }  catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	     return  pool;
	}  
	
	
}
