package com.jt.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;


@Service
public class RedisService {

	@Autowired(required=false)
	private JedisSentinelPool jedisSentinelPool;

	public void set(String key,String value){
		Jedis jedis = jedisSentinelPool.getResource();
		//将数据写入redis中
		jedis.set(key, value);
		//将连接放回连接池
		jedisSentinelPool.returnResource(jedis);
		
	}
	
	public String get(String key){
		
		Jedis jedis = jedisSentinelPool.getResource();
		String value = jedis.get(key);
		jedisSentinelPool.returnResource(jedis);
		return value;
	}
}

