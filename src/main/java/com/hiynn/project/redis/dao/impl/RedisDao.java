package com.hiynn.project.redis.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.stereotype.Service;

import com.hiynn.project.model.util.Constants;
import com.hiynn.project.model.util.JsonUtils;
import com.hiynn.project.redis.dao.AbstractBaseRedisDao;
import com.hiynn.project.redis.dao.IRedisDao;
import com.hiynn.project.redis.entity.BaseRedisObject;

@Service
public class RedisDao extends AbstractBaseRedisDao<String, Object> implements IRedisDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisDao.class);
	
	@Override
	public void delete(String key) {
		redisTemplate.delete(key);
	}
	
	@Override
	public void deleteAll() {
		 redisTemplate.execute(new RedisCallback<Boolean>() {
	            public Boolean doInRedis(RedisConnection connection)
	                    throws DataAccessException {
	                connection.flushDb();
	                return true;
	            }
	        });
	}
	
	@Override
	public void delete(Collection<String> keys){
		redisTemplate.delete(keys);
	}
	
	@Override
	public List<RedisClientInfo> getClientList() {
		return redisTemplate.getClientList();
	}

	@Override
	public String getRedisPerformance(final String section, final String key) {
		return redisTemplate.execute(new RedisCallback<String>() {

			@Override
			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				Properties prop = connection.info(section);
				return prop.getProperty(key, Constants.DEFAULT＿INFO_VALUE);
			}
		});
	}

	@Override
	public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}
	
	@Override
	public Boolean expire(String key, long timeout, TimeUnit unit) {
		return redisTemplate.expire(key, timeout, unit);
	}

	@Override
	public Boolean expireAt(String key, Date date) {
		return redisTemplate.expireAt(key, date);
	}
	
	/**
	 * 公共操作：获取符合格式的key集合，是keys替代方法
	 * @param ScanOptions options:支持输入正则表达式和返回结果列表长度
	 * @return
	 */
	@Override
	public List<String> scan(final ScanOptions options){
		return getRedisTemplate().execute(new RedisCallback<List<String>>() {

			@Override
			public List<String> doInRedis(RedisConnection connection)
					throws DataAccessException {
				 List<String> keys = new ArrayList<String>();
				 Cursor<byte[]> cursor = connection.scan(options);
				 while (cursor.hasNext()) {
					 keys.add(new String(cursor.next()));
			     }
				 try {
				      cursor.close();
				    } catch (IOException e) {
				      e.printStackTrace();
				      LOGGER.error("Cursor close failed:" + e.getMessage());
				 }
				 return keys;
			}
		});
	}
	
	@Override
	public <T> boolean setNx(String key,T t){
		return redisTemplate.opsForValue().setIfAbsent(key,JsonUtils.toJson(t));
	}

	@Override
	public <T> void set(String key ,T t){
		redisTemplate.opsForValue().set(key, JsonUtils.toJson(t));
	}

	@Override
	public String get(String key) {
		return (String) redisTemplate.opsForValue().get(key);
	}
	
	@Override
	public Long incrBy(String key, long delta) {
		return getRedisTemplate().opsForValue().increment(key, delta);
	}
	
	@Override
	public <T> boolean hSetNx(String key, String field, T value) {
		return redisTemplate.opsForHash().putIfAbsent(key, field, JsonUtils.toJson(value));
	}

	@Override
	public <T> void hSet(String key, String field, T value) {
        redisTemplate.opsForHash().put(key, field, JsonUtils.toJson(value));
	}

	@Override
	public Long hDel(final String key, final String field) {
//		getRedisTemplate().opsForHash().delete(key, field);
		return getRedisTemplate().execute(new RedisCallback<Long>() {

			@Override
			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.hDel(keySerializer.serialize(key)
						, valueSerializer.serialize(field));
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T hGet(String key, String field, Class<T> type) {
		return (T)JsonUtils.fromJson((String)redisTemplate.opsForHash().get(key, field), type);
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Map<Object,T> hGetAll(String key, Class<T> type) {
		Map<Object,T> retMap = new HashMap<Object, T>();
		Map<Object,Object> map = redisTemplate.opsForHash().entries(key);
		Iterator<Entry<Object,Object>>  iter = map.entrySet().iterator();
		while(iter.hasNext())
		{
			Entry<Object,Object> entry = iter.next();
			retMap.put(entry.getKey(), (T)JsonUtils.fromJson((String)entry.getValue(), type));
		}
		return retMap;
	}

	@Override
	public <T> void hmSet(String key, Map<String, T> m) {
		redisTemplate.opsForHash().putAll(key, JsonUtils.toJson(m));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> hmGet(String key, Collection<Object> field) {
		return (List<T>)redisTemplate.opsForHash().multiGet(key, field);
	}

	@Override
	public Long lSet(String key, String value){
		return redisTemplate.opsForList().leftPush(key, value);
	}
	@Override
	public Long lmSet(String key,Collection<Object> list){
		return redisTemplate.opsForList().leftPushAll(key, list);
	}


	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> lGet(String key,long count) {
		return (List<T>) redisTemplate.opsForList().range(key, 0, count);
	}
	
	@Override
	public Long lDel(String key, long count, String value){
		return redisTemplate.opsForList().remove(key, count, value);
	}
	
	@Override
	public Long lLength(final String key) {
		return redisTemplate.opsForList().size(key);
	}

	@Override
	public void lTrim(final String key, final long count) {
		redisTemplate.opsForList().trim(key, 0, count);
	}
	
	@Override
	public <T> Long sSet(String key, T values) {
		return redisTemplate.opsForSet().add(key, JsonUtils.toJson(values));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Set<T> sGet(String key) {
		return (Set<T>)redisTemplate.opsForSet().members(key);
	}
	
	@Override
	public Long sDel(String key, Object values){
		return redisTemplate.opsForSet().remove(key, values);
	}
	
	@Override
	public Boolean zAdd(String key, String value, double score){
		return redisTemplate.opsForZSet().add(key, value, score);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Set zRange(String key ,long start, long end){
		return redisTemplate.opsForZSet().range(key, start, end);
	}
	@Override
	public double zScore(String key, String value) {
		return redisTemplate.opsForZSet().score(key, value);
	}
	
	@Override
	public Long zRemoveRange(final String key,final long begin ,final long end) {
		/*redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				Long result = connection.zRemRange(key.getBytes(), begin, end);
				return result;
			}
		});*/
		return redisTemplate.opsForZSet().removeRange(key, begin, end);

	}

	@Override
	public Long zRemoveRangeByScore(final String key,final double min ,final double max){
		return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
	}
	
	@Override 
	public Long zRemove(final String key, final Object values){
		return redisTemplate.opsForZSet().remove(key, values);
	}
	
    /**
     * 批量请求,使用pipeline方式
     */
	@Override
	public List<Object> addListWithDiffKey(final List<BaseRedisObject> list) {
		List<Object> results = redisTemplate.executePipelined(new RedisCallback<Object>() {

			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				for(BaseRedisObject bro:list){
					connection.setNX(keySerializer.serialize(bro.getPkProperty()), gson.toJson(bro,bro.getClass()).getBytes());
				}
				return null;
			}
		});
		return results;
	}

	/* (non-Javadoc)
	 * @see com.lulin.redis.dao.IRedisDao#dbSize()
	 */
	@Override
	public Long dbSize() {
		return getRedisTemplate().execute(new RedisCallback<Long>() {

			@Override
			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.dbSize();
			}
		});
	}

	
	
	
}
