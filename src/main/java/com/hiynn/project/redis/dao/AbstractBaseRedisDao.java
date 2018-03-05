/**
 * 
 */
package com.hiynn.project.redis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

/**
 * @author loululin
 */
@Service
public abstract class AbstractBaseRedisDao<K, V> {
	
	@Autowired
	protected RedisTemplate<K, V> redisTemplate;
	
	@Autowired
	protected RedisSerializer<String> valueSerializer;
	
	@Autowired
	protected RedisSerializer<String> keySerializer;
	
	protected Gson gson = new Gson();
	

}
