package com.gstz.utils;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.jedis.exceptions.JedisException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class RedisUtils{

	// Redis服务器IP
	private static String ADDR;

	// Redis的端口号
	private static int PORT = 6379;

	// 访问密码,若你的redis服务器没有设置密码，就不需要用密码去连接
	private static String AUTH = "123456";

	// 可用连接实例的最大数目，默认值为8；
	private static int MAX_TOTAL = 1024;

	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 50;

	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。
	private static int MAX_WAIT = 30000;

	//设置客户端连接时的超时时间，单位为秒。当客户端在这段时间内没有发出任何指令，那么关闭该连接(和服务端相匹配600s)
	private static int TIMEOUT = 60000;

	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW = true;

	private static JedisPool jedisPool = null;
	private static Logger logger = Logger.getLogger("RedisUtils class");
	private static JedisPoolConfig config=null;
	/**
	 * 初始化Redis连接池
	 */
	static {
		try {
			Properties prop = new Properties();
			try (InputStream input = RedisUtils.class.getClassLoader().getResourceAsStream("application.yml")) {
				prop.load(input);
				ADDR = prop.getProperty("redis_addr");
			} catch (IOException e) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			config = new JedisPoolConfig();
			config.setMaxTotal(MAX_TOTAL);
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWaitMillis(MAX_WAIT);
			config.setTestOnBorrow(TEST_ON_BORROW);
			jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
     * 获取Jedis实例
     * 2018年12月6日20:39:28，增加了try和catch，避免出现问题后不能恢复
     * @return
     */
    public synchronized static Jedis getJedis() {
    	try {
            if (jedisPool != null) {
                Jedis jedis = jedisPool.getResource();
                logger.info("获得一个jedis.");
                return jedis;
            } else {
            	logger.info("jedisPool为空,返回空");
                return null;
            }
    	}catch(Exception e) {
    		logger.info("-----getJedis()异常！url:"+ADDR+":"+PORT);
    		e.printStackTrace();
    		return null;
    	}
    }

	/**
	 * 释放jedis资源
	 * 
	 * @param jedis
	 */
	public static void returnResource(final Jedis jedis, boolean conectionBroken) {
		try {
			if (conectionBroken) {
				jedisPool.returnBrokenResource(jedis);
			} else {
				jedisPool.returnResource(jedis);
			}
		} catch (Exception e) {
			logger.error("return back jedis failed, will fore close the jedis.", e);
		}
	}

	public static boolean handleJedisException(Exception exception) {
		if (!(exception instanceof JedisException)) {
			logger.info("exception的类型不对，返回false.",exception);
			return false;  
		}

		if (exception instanceof JedisConnectionException) {
			logger.error("Redis connection lost.", exception);
		} else if (exception instanceof JedisDataException) {
			if ((exception.getMessage() != null) && (exception.getMessage().indexOf("READONLY") != -1)) {
				logger.error("Redis connection are read-only slave.", exception);
			} else {
				// dataException, isBroken=false
				return false;
			}
		} else {
			logger.error("Jedis exception happen.", exception);
		}
		return true;
	}

	public static String getValue(Jedis jedis, String key) {
		String value = jedis.get(key);
		if (null != value) {
			jedis.setex(key, 1800, value);
			logger.info("jedis设置key=" + key + "value=" + value);
		}
		logger.info("释放jedis,从jedis获取的key=" + key + "value=" + value);
		return value;
	}

}