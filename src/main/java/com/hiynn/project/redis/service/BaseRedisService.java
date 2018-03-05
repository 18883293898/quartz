package com.hiynn.project.redis.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.stereotype.Service;

import com.hiynn.project.model.util.JsonUtils;
import com.hiynn.project.model.util.RedisOpsType;
import com.hiynn.project.redis.dao.impl.RedisDao;
import com.hiynn.project.redis.entity.BaseRedisObject;



@Service
public class BaseRedisService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseRedisService.class);
	
	@Autowired
	private RedisDao iRedisDao;
	
	@Autowired
	@Qualifier("redisScriptAddUerList")
	private RedisScript<Boolean> redisScriptAddUerList;
	
	@Autowired
	@Qualifier("redisScriptUpdateUerList")
	private RedisScript<Boolean> redisScriptUpdateUerList;
	
	@Autowired
	@Qualifier("redisScriptDeleteUerList")
	private RedisScript<Boolean> redisScriptDeleteUerList;
	
	/**
	 * 
	 * <p>Title: queryAValue </p>
	 * <p>Description: 模糊查询时，由子类实现对不同存储结构数据查询</p>
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public <T> T queryAValue(String key) throws Exception{
		return null;
	}
	/**
	 * 
	 * <p>Title: add </p>
	 * <p>Description: 简单类型操作：增加相应键对应的value值，如果键不存在，则增加；如果存在，则直接返回</p>
	 * @param key
	 * @param t
	 * @return
	 */
	public <T> boolean add(String key,T t){
		boolean bRet = false;
		try{
			bRet = iRedisDao.setNx(key,t);
		}catch(Exception e){
			LOGGER.error("BaseRedisService add failed:",e);
		}
		return bRet;
	}
	/**
	 * 
	 * <p>Title: addOrUpdate </p>
	 * <p>Description:简单类型操作：增加或者更新key相对应的value值，如果键不存在，则增加；如过键存在，则覆盖</p>
	 * @param key
	 * @param t 
	 */
	public <T> void addOrUpdate(String key,T t){
		try{
			iRedisDao.set(key,t);
		}catch(Exception e){
			LOGGER.error("BaseRedisService add failed:",e);
		}
	}
	/**
	 * 
	 * <p>Title: query </p>
	 * <p>Description: 简单类型操作：查询value为简单类型</p>
	 * @param key
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T query(String key,Class<T> type){
		try{
			String jsonValue = (String) iRedisDao.get(key);
			if(jsonValue != null){
				return (T) JsonUtils.fromJson(jsonValue,type);
			}
		}catch(Exception e){
			LOGGER.error("BaseRedisService query failed:",e);
		}
		return null;
	}
	
	/**
	 * 
	 * @param key 键
	 * @param delta 增量
	 * @return Long
	 */
	public Long incrBy(String key , long delta){
		Long ret = null;
		try{
			ret = iRedisDao.incrBy(key, delta);
		}catch(Exception e){
			LOGGER.error("BaseRedisService incrBy failed:",e);
		}
		return ret ; 
	}
	/**
	 * 
	 * <p>Title: hAdd </p>
	 * <p>Description: 哈希结构操作，增加相应键和域对应的value值，如果域不存在，则增加；如果存在，则覆盖</p>
	 * @param key
	 * @param field
	 * @param t
	 */
	public <T> void hAdd(String key, String field, T t){
		try{
			iRedisDao.hSet(key, field, t);
		}catch(Exception e){
			LOGGER.error("BaseRedisService hAdd failed:",e);
		}
	}
	/**
	 * 
	 * <p>Title: hAddIfAbsent </p>
	 * <p>Description: 哈希结构操作，增加相应键和域对应的value值，如果域不存在，则增加；如果存在，则直接返回</p>
	 * @param key
	 * @param field
	 * @param t
	 * @return
	 */
	public <T> boolean hAddIfAbsent(String key, String field, T t){
		boolean bRet = false;
		try{
			bRet = iRedisDao.hSetNx(key, field, t);
		}catch(Exception e){
			LOGGER.error("BaseRedisService hAddIfAbsent failed:",e);
		}
		return bRet;
	}
	/**
	 * 
	 * <p>Title: hmAdd </p>
	 * <p>Description: 哈希结构操作，同时将多个 field-value (域-值)对设置到哈希表 key 中</p>
	 * @param key
	 * @param m
	 */
	public <T> void hmAdd(String key, Map<String,T> m){
		iRedisDao.hmSet(key, m);
	}
	/**
	 * 
	 * <p>Title: hQuery </p>
	 * <p>Description: 哈希结构操作，按照键和域查询值</p>
	 * @param key
	 * @param field
	 * @param type
	 * @return
	 */
	public <T> T hQuery(String key, String field, Class<T> type){
		try{
			return iRedisDao.hGet(key, field, type);
		}catch(Exception e){
			LOGGER.error("BaseRedisService hQery failed:",e);
		}
		return null;
	}
	/**
	 * 
	 * <p>Title: hQueryAll </p>
	 * <p>Description: 哈希结构操作，按照键查询其中所有域</p>
	 * @param key
	 * @param type
	 * @return
	 */
	public <T> Map<Object,T> hQueryAll(String key, Class<T> type){
		try{
			return iRedisDao.hGetAll(key, type);
		}catch(Exception e){
			LOGGER.error("BaseRedisService hQeryAll failed:",e);
		}
		return null;
	}
	/**
	 * 
	 * <p>Title: hmQuery </p>
	 * <p>Description: 哈希结构操作，返回哈希表 key 中，一个或多个给定域的值</p>
	 * @param key
	 * @param fields
	 * @return
	 */
	public <T> List<T> hmQuery(String key, Collection<Object> fields){
		try{
			return iRedisDao.hmGet(key, fields);
		}catch(Exception e){
			LOGGER.error("BaseRedisService hmQuery failed:",e);
		}
		return null;
	}
	
	/**
	 * 
	 * @param key 键
	 * @param field 域值
	 */
	public Long hDelete(String key, String field){
		Long ret = 0l;
		try{
			ret = iRedisDao.hDel(key, field);
		}catch(Exception e){
			LOGGER.error("BaseRedisService hDelete failed:",e);
		}
		return ret;
	}
	/**
	 * 
	 * <p>Title: addList </p>
	 * <p>Description: 列表结构操作：在key对应的list起始位置添加一个String元素</p>
	 * @param key
	 * @param t
	 * @return
	 */
	public <T> Long addList(String key,T t){
		Long length = null ;
		try{
			length = iRedisDao.lSet(key, JsonUtils.toJson(t));
		}catch(Exception e){
			LOGGER.error("BaseRedisService query addList:",e);
		}
		return length;
	}
	/**
	 * 
	 * <p>Title: addListWithAKey </p>
	 * <p>Description: 列表结构操作：在key对应的list起始位置一次性添加多个String元素</p>
	 * @param key
	 * @param list
	 */
	public <T> void addListWithAKey(String key,List<T> list){
		try{
			if(list != null && list.size() >0){
				for(final T t:list){
					iRedisDao.lSet(key, JsonUtils.toJson(t));
				}
			}
		}catch(Exception e){
			LOGGER.error("BaseRedisService  addListWithAKey:",e);
		}

	}
	/**
	 * 
	 * <p>Title: lQuery </p>
	 * <p>Description: 列表结构操作：查询value为list</p>
	 * @param key
	 * @param count
	 * @param type
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T> lQuery(String key,long count,Class<T> type){
		List list = null;
		try{
			List<String> jsonList = iRedisDao.lGet(key, count);
			list = JsonUtils.fromJson(jsonList, type);
		}catch(Exception e){
			LOGGER.error("BaseRedisService lQuery failed:",e);
		}
		return list;

	}
	
	/**
	 * @param <T> 泛型类型 
	 * @param key 键
	 * @param values 值
	 */
	public <T> Long addSet(String key, Set<T> values){
		Long ret = 0l;
		try{
			for(final T t:values){
				ret += iRedisDao.sSet(key, t);	
			}
		}catch(Exception e){
			LOGGER.error("BaseRedisService addSet failed:",e);
		}
		return ret;
	}
	
	/**
	 * 
	 * <p>Title: addSet </p>
	 * <p>Description: 集合结构操作：将一个 member 元素加入到集合 key 当中，已经存在于集合的 member元素将被忽略</p>
	 * @param key 键
	 * @param values 值
	 */
	public <T> Long addSet(String key, T values){
		Long ret = 0l;
		try{
			ret = iRedisDao.sSet(key, values);
		}catch(Exception e){
			LOGGER.error("BaseRedisService addSet failed:",e);
		}
		return ret;
	}
	
	/**
	 * 
	 * <p>Title: delSet </p>
	 * <p>Description: 集合结构操作：删除set中一个元素</p>
	 * @param key 键
	 * @param values 值
	 */
	public Long delSet(String key, Object values){
		Long ret = 0l;
		try{
			ret = iRedisDao.sDel(key, values);
		}catch(Exception e){
			LOGGER.error("BaseRedisService delSet failed:",e);
		}
		return ret;
	}
	
	
	/**
	 * 
	 * <p>Title: sQuery </p>
	 * <p>Description: 集合结构操作：返回集合 key 中的所有成员</p>
	 * @param key
	 * @param type
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> Set<T> sQuery(String key,Class<T> type){
		Set set = null;
		try{
			Set<String> jsonSet = iRedisDao.sGet(key);
			set = JsonUtils.fromJson(jsonSet, type);
		}catch(Exception e){
			LOGGER.error("BaseRedisService sQuery failed:",e);
		}
		return set;
	}
	/**
	 * 
	 * <p>Title: addSortedSetWithAKey </p>
	 * <p>Description: 有序集合结构操作：集合所有值放入 </p>
	 * @param key
	 * @param set
	 */
	public <T> void zAddAll(String key,Set<T> set){
		try{
			if(set != null && set.size() >0){
				for(final T t:set){
					iRedisDao.zAdd(key, JsonUtils.toJson(t), 0.0);
				}
			}
		}catch(Exception e){
			LOGGER.error("BaseRedisService addListWithAKey failed:",e);
		}
	}
	/**
	 * 
	 * <p>Title: zAdd </p>
	 * <p>Description: 有序集合：添加一个元素</p>
	 * @param key
	 * @param value
	 * @param score
	 */
	public boolean zAdd(String key, String value, double score){
		try{
			return iRedisDao.zAdd(key, value, score);
		}catch(Exception e){
			LOGGER.error("BaseRedisService zAdd failed:",e);
			return false;
		}
	}
	/**
	 * 
	 * <p>Title: zQueryScore </p>
	 * <p>Description: 有序集合：查询有序集合中一个元素的分数</p>
	 * @param key
	 * @param value
	 * @return
	 */
	public double zQueryScore(String key, String value){
		return iRedisDao.zScore(key, value);
	}
	/**
	 * 
	 * <p>Title: delSortedSet </p>
	 * <p>Description: 有序集合结构操作：刪除集合中1个或多个元素 </p>
	 */
	public Long zDel(String key, Object values){
		Long ret = 0l;
		try{
			ret = iRedisDao.zRemove(key, values);
		}catch(Exception e){
			LOGGER.error("BaseRedisService delSortedSet failed:", e);
		}
		return ret;
	}
	
	/**
     * 
     * @param key 键
     * @return long
     */
	public Long lLength(String key){
		Long ret = 0l;
		try{
			ret = iRedisDao.lLength(key);
		}catch(Exception e){
			LOGGER.error("BaseRedisService lLength failed:"+e.getMessage());
		}
		return ret;
	}
	/**
	 * 
	 * <p>Title: lTrim </p>
	 * <p>Description: 列表结构操作：保留指定 key 的值范围内的数据</p>
	 * @param key
	 * @param count
	 */
	public void lTrim(String key,long count){
		iRedisDao.lTrim(key, count);
	}
	/**
	 * 
	 * <p>Title: delete </p>
	 * <p>Description: 公共操作：删除某个键对应的值</p>
	 * @param key
	 */
	public void delete(String key){
		iRedisDao.delete(key);
	}
	/**
	 * 
	 * <p>Title: deleteAll </p>
	 * <p>Description: 公共操作：删除当前选择数据库中的所有 key</p>
	 */
	public void deleteAll(){
		try{
		  iRedisDao.deleteAll();
		}catch(Exception e){
			LOGGER.error("BaseRedisService deleteAll failed:",e);
		}
	}
	/**
	 * 
	 * <p>Title: expire </p>
	 * <p>Description: 公共操作：设置指定Key的过期日期</p>
	 * @param key
	 * @param timeout
	 * @param unit
	 * @return
	 */
    public Boolean expire(String key, long timeout, TimeUnit unit){
    	boolean bRet = false;
    	try{
    		bRet = iRedisDao.expire(key, timeout, unit);
    	}catch(Exception e){
    		LOGGER.error("BaseRedisService expire failed:",e);
    	}
    	return bRet;
    }
    /**
     * <p>Title: expireAt </p>
     * <p>Description: 公共操作：设置指定Key的过期日期</p>
     * @param key
     * @param date
     * @return
     */
    public Boolean expireAt(String key, Date date){
    	boolean bRet = false;
    	try{
    		bRet = iRedisDao.expireAt(key, date);
    	}catch(Exception e){
    		LOGGER.error("BaseRedisService expireAt failed:",e);
    	}
    	return bRet;
    }
    /**
     * 
     * <p>Title: zRemoveRangeByScore </p>
     * <p>Description: 有序集合结构操作：刪除集合中score在給定区间的元素 </p>
     * @param key
     * @param min
     * @param max
     */
    public Long zRemoveRangeByScore(String key,double min ,double max){
    	Long ret = 0l;
    	try{
    		ret = iRedisDao.zRemoveRangeByScore(key, min, max);
    	}catch (Exception e) {
    		LOGGER.error("BaseRedisService zRemoveRangeByScore failed:",e);
		}
        return ret;
    }
    /**
     * 
     * <p>Title: zRemoveRange </p>
     * <p>Description: 有序集合结构操作：刪除集合中排名在給定区间的元素</p>
     * @param key
     * @param begin
     * @param end
     */
    public Long zRemoveRange(String key, long begin , long end){
    	Long ret = 0l;
		try{
			ret = iRedisDao.zRemoveRange(key, begin, end);
    	}catch (Exception e) {
    		LOGGER.error("BaseRedisService zRemoveRange failed:",e);
		}
        return ret;
    }

    /**
     * 
     * <p>Title: zQuery </p>
     * <p>Description: 有序集合结构操作：返回有序集 key 中，指定区间内的成员</p>
     * @param key
     * @param count
     * @param type
     * @return
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> Set<T> zQuery(String key,long count,Class<T> type){
		Set<T> oo = null;
		try{
			 Set set = iRedisDao.zRange(key, 0, -1);
			 return JsonUtils.fromJson(set, type);
		}catch(Exception e){
			LOGGER.error("BaseRedisService zQuery failed:",e);
		}
		return oo;
	}
	
	
	/**
     * 按键进行模糊查询,如果标示符flag为true，则添加*号在前面；false，则添加*在后面
     * @param <T> 泛型
     * @param keyPattern 模糊条件表达式
     * @param flag 标识
     * @param count 每次返回结果长度
     * @param redisOpsType 值类型
     * @return LIST
     */
	@SuppressWarnings("unchecked")
	public <T> List<T> fuzzySearch(String keyPattern,int flag,long count,RedisOpsType redisOpsType){
    	List<T> result = new ArrayList<T>();
    	if(flag == 0){
    		keyPattern=keyPattern+"*";
    	}else if(flag == 1){
    		keyPattern= "*"+keyPattern;
    	}else{
    		keyPattern= "*"+keyPattern+"*";
    	}
    	try{
    		long start = System.currentTimeMillis();
    		LOGGER.info("fuzzySearch begin...");
    		ScanOptions sco = ScanOptions.scanOptions().match(keyPattern).count(count).build();
    		List<String> keys = iRedisDao.scan(sco);
    		long end = System.currentTimeMillis();
    		LOGGER.info("fuzzySearch end, cost:" + (end-start) +"ms");
    		if(keys.size() ==0){
    			return result;
    		}
    		result = (List<T>)queryMultiValue(keys, redisOpsType);
    	}catch(Exception e){
    		LOGGER.error("BaseRedisService fuzzySearch failed:"+e.getMessage());
    	}
    	LOGGER.info("fuzzySearch really end...");
    	return result;
    }

	/**
	 * 
	 * <p>Title: fuzzyDelete </p>
	 * <p>Description: 公共操作：删除符合格式的key集合</p>
	 * @param keyPattern
	 * @param flag
	 */
    public void fuzzyDelete(String keyPattern,int flag,long count){
    	if(flag == 0){
    		keyPattern = keyPattern + "*";
    	}else if(flag == 1){
    		keyPattern= "*" + keyPattern;
    	}else{
    		keyPattern= "*" + keyPattern + "*";
    	}
    	try{
    		ScanOptions sco = ScanOptions.scanOptions().match(keyPattern).count(count).build();
    		List<String> keys = iRedisDao.scan(sco);
        	iRedisDao.delete(keys);
        	LOGGER.info("fuzzyDelete key=" + keyPattern + "size=" + keys.size());
    	}catch(Exception e){
    		LOGGER.error("BaseRedisService fuzzyDelete failed:",e);
    	}
    }
    /**
     * 
     * <p>Title: addListWithDiffKey </p>
     * <p>Description: 批量请求,使用pipeline方式</p>
     * @param lt
     * @return
     */
	public List<Object> addListWithDiffKey(List<BaseRedisObject> lt){
		List<Object> list = null;
		try{
			list = iRedisDao.addListWithDiffKey(lt);
		}catch(Exception e){
			LOGGER.error("BaseRedisService addListWithDiffKey failed:" ,e);
		}
		return list;
	}

	/**
	 * 
	 * <p>Title: getRedisClients </p>
	 * <p>Description: 公共操作：获取Redis Server所有客户端连接信息</p>
	 * @return
	 */
	public List<RedisClientInfo> getRedisClients() {
		return iRedisDao.getClientList();
	}
	/**
	 * 
	 * <p>Title: getRedisPerformance </p>
	 * <p>Description: 公共操作：获取Redis Server性能信息</p>
	 * @param section
	 * @param key
	 * @return
	 */
	public String getRedisPerformance(String section, String key){
		return iRedisDao.getRedisPerformance(section, key);
	}
	
	/**
	 * 
	 * @param keys 键集合
	 * @return List 列表
	 * @throws Exception 异常
	 */
	@SuppressWarnings("rawtypes")
	public List queryMultiValue(List<String> keys, RedisOpsType redisOpsType) throws Exception {
		return null;
	}
	
	/**
	 * 
	 * <p>Title: batchExecuteLuaScriptAddUserList </p>
	 * <p>Description: 脚本：插入User</p>
	 * @param keys
	 * @param values
	 * @return
	 */
	public boolean batchExecuteLuaScriptAddUserList(List<String> keys , Object ...values){
		return iRedisDao.getRedisTemplate().execute(redisScriptAddUerList,keys,values);
	}
	
	/**
	 * 
	 * <p>Title: batchExecuteLuaScriptAddUserList </p>
	 * <p>Description: 脚本：更新User</p>
	 * @param keys
	 * @param values
	 * @return
	 */
	public boolean batchExecuteLuaScriptUpdateUserList(List<String> keys , Object ...values){
		return iRedisDao.getRedisTemplate().execute(redisScriptUpdateUerList,keys,values);
	}
	
	/**
	 * 
	 * <p>Title: batchExecuteLuaScriptAddUserList </p>
	 * <p>Description: 脚本：删除User</p>
	 * @param keys
	 * @param values
	 * @return
	 */
	public boolean batchExecuteLuaScriptDeleteUserList(List<String> keys){
		return iRedisDao.getRedisTemplate().execute(redisScriptDeleteUerList,keys);
	}
	
	public long getDbSize(){
		return iRedisDao.dbSize();
	}
	
	
}
