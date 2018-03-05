package com.hiynn.project.redis.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.types.RedisClientInfo;

import com.hiynn.project.redis.entity.BaseRedisObject;



public interface IRedisDao {
	
	/**
	 * 公共操作：删除某个键对应的值
	 * @param key 主键
	 */
	void delete(String key);
	
	/**
	 * 公共操作：删除当前选择数据库中的所有 key
	 */
	void deleteAll();
	
	/**
	 * 公共操作：同时删除多个键
	 * @param keys 多个键
	 */
	void delete(Collection<String> keys);
	
	/**
	 * 公共操作：获取Redis Server所有客户端连接信息
	 * @return List<RedisClientInfo>
	 */
	List<RedisClientInfo> getClientList();
	
	/**
	 * 公共操作：获取Redis Server性能信息
	 * @param section 性能部分
	 * @param key 主键
	 * @return String
	 */
	String getRedisPerformance(String section , String key);
	
	/**
	 * 公共操作：
	 * @return RedisTemplate
	 */
	RedisTemplate<String, Object> getRedisTemplate();
	
	/**
	 * 公共操作：设置指定Key的过期日期
	 * @param key 主键
	 * @param timeout 超时时间
	 * @param unit 时间单位
	 * @return true|false
	 */
	Boolean expire(String key, final long timeout, final TimeUnit unit);

	/**
	 * 公共操作：设置指定Key的过期日期
	 * @param key 主键
	 * @param date 日期
	 * @return true|false
	 */
	Boolean expireAt(String key, Date date);
	
	/**
	 * 公共操作：获取符合格式的key集合，是keys替代方法
	 * @param ScanOptions options:支持输入正则表达式和返回结果列表长度
	 * @return
	 */
	List<String> scan(ScanOptions sco);

	/**
	 * 简单类型操作：增加相应键对应的value值，如果键不存在，则增加；如果存在，则直接返回
	 * @param <T> 泛型类型
	 * @param key 主键
	 * @param t 类型
	 * @return true|false
	 */
	<T> boolean setNx(String key, T t);

	/**
	 * 简单类型操作：增加或者更新key相对应的value值，如果键不存在，则增加；如过键存在，则覆盖
	 * @param <T> 泛型类型
	 * @param key 主键
	 * @param t 类型
	 * @return
	 */
	<T> void set(String key, T t);

	/**
	 * 简单类型操作：查询value为简单类型
	 * @param key 主键
	 * @return String
	 */
	String get(String key);
	
	/**
	 * 简单类型操作：key所储存的值加上增量 delta
	 * @param key 主键
	 * @param delta 增量值
	 * @return true|false
	 */
	Long incrBy(String key, long delta);

	/**
	 * 哈希结构操作，增加相应键和域对应的value值，如果域不存在，则增加；如果存在，则直接返回
	 * @param <T> 泛型类型
	 * @param key 主键
	 * @param field 域值
	 * @param value 值
	 * @return true|false
	 */
	<T> boolean hSetNx(String key, String field, T value);

	/**
	 * 哈希结构操作，增加相应键和域对应的value值，如果域不存在，则增加；如果存在，则覆盖
	 * @param <T> 泛型类型
	 * @param key 键
	 * @param field 域值
	 * @param value 值
	 */
	<T> void hSet(String key, String field, T value);
	
	/**
	 * 哈希结构操作，同时将多个 field-value (域-值)对设置到哈希表 key 中
	 * @param <T> 泛型类型
	 * @param key 键
	 * @param m 值
	 */
	<T> void hmSet(String key,Map<String,T> m);

	/**
	 * 哈希结构操作，删除相应键和域对应的value值
	 * @param key 键
	 * @param field 域值
	 */
	Long hDel(String key, String field);

	/**
	 * 哈希结构操作，按照键和域查询值
	 * @param <T> 泛型类型
	 * @param key 键
	 * @param field 域值
	 * @param type  类型
	 * @return 泛型类型
	 */
	<T> T hGet(String key,String field, Class<T> type);

	/**
	 * 哈希结构操作，按照键查询其中所有域
	 * @param <T> 泛型类型
	 * @param key 键
	 * @param type 类型
	 * @return Map<Object,T>
	 */
	<T> Map<Object,T> hGetAll(String key, Class<T> type);

	/**
	 * 哈希结构操作，返回哈希表 key 中，一个或多个给定域的值
	 * @param <T> 泛型类型
	 * @param key 键
	 * @param field 域值
	 * @return List<T>
	 */
	<T> List<T> hmGet(String key,Collection<Object> field);

	/**
	 * 列表结构操作：在key对应的list起始位置添加一个或者多个String元素
	 * @param key 键
	 * @param value 值
	 * @return Long
	 */
	Long lSet(String key, String value);
	
	/**
	 * 列表结构操作：在key对应的list起始位置添加一个或者多个String元素
	 * @param key 键
	 * @param list list值
	 * @return Long
	 */
	Long lmSet(String key,Collection<Object> list);

	 /**
     * 列表结构操作：查询value为list
     * @param <T> 泛型类型
     * @param key 键
     * @param count 范围
     * @return List<T>
     */
	<T> List<T> lGet(String key,long count);
	
	/**
	 * 列表结构操作：Removes the first count occurrences of value from the list stored at key
	 * @param key 键
	 * @param count 范围
	 * @param value 值
	 */
	Long lDel(String key, long count, String value);
	
	/**
	 * 列表结构操作：返回列表的长度
	 * @param key 键
	 * @return Long
	 */
	Long lLength(String key);

	/**
	 * 列表结构操作：保留指定 key 的值范围内的数据
	 * @param key 键
	 * @param count 范围
	 * @return
	 */
	void lTrim(String key,long count);

	/**
	 * 集合结构操作：将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member元素将被忽略
	 * @param <T> 泛型类型
	 * @param key 键
	 * @param values 值
	 * @return long
	 */
	<T> Long sSet(String key, T values);

	/**
	 * 集合结构操作：返回集合 key 中的所有成员
	 * @param <T> 泛型类型
	 * @param key 键
	 * @return 类型
	 */
	<T> Set<T> sGet(String key);
	
	/**
	 * 集合结构操作：将一个或多个 member 元素从集合 key 当中移除
	 * @param key 键
	 * @param values 值
	 * @return long
	 */
	Long sDel(String key, Object values);
	
	/**
	 * 有序集合结构操作：集合单个值放入
	 * @param key 键
	 * @param value 值 
	 * @param score 得分
	 * @return TRUE|FALSE
	 */
	Boolean zAdd(String key, String value, double score);
	/**
	 * 
	 * <p>Title: zScore </p>
	 * <p>Description: 有序集合：查询有序集合中一个元素的分数</p>
	 * @param key
	 * @param value
	 * @return
	 */
	double zScore(String key, String value);
	
	/**
	 * 有序集合结构操作：返回有序集 key 中，指定区间内的成员
	 * @param key 键
	 * @param start 开始
	 * @param end 结束
	 * @return Set
	 */
	@SuppressWarnings("rawtypes")
	Set zRange(String key ,long start, long end);

	/**
	 * 有序集合结构操作：刪除集合中排名在給定區間的元素
	 * @param key 键
	 * @param begin 开始
	 * @param end 结束
	 */
	Long zRemoveRange(String key,long begin ,long end);

	/**
	 * 有序集合结构操作：刪除集合中score在給定区间的元素
	 * @param key 键
	 * @param min 最小
	 * @param max 最大
	 */
	Long zRemoveRangeByScore(String key,double min ,double max);
	
	/**
	 * 有序集合结构操作：刪除集合中1个或多个元素
	 * @param key 键
	 * @param values 值
	 * @return Long
	 */
	Long zRemove(String key, Object values);

	
	/**
	 *
	 * @param list 特定对象列表
	 * @return List
	 */
	List<Object> addListWithDiffKey(List<BaseRedisObject> list);  
	
	/**
	 *
	 * @return dbSize 数据库key的总数
	 */
	Long dbSize();  
      

}
