package com.xinda.cache.service;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis管理类.
 * ValueOperations　　——基本数据类型和实体类的缓存
 * ListOperations　　   ——list的缓存
 * SetOperations　　  ——set的缓存
 * HashOperations　　Map的缓存
 *
 * @author Coundy.
 * @date 2017/5/21 21:51.
 */
public interface IRedisCache<T> {

    /**
     * 判断是否存在指定Key值.
     *
     * @param key 待验证Key
     * @return true-存在;false-不存在
     */
    public boolean isContainsKey(String key);

    /**
     * 移除Key值.
     *
     * @param t 对象或对象集合
     */
    public <T> void removeKey(T t);

    /**
     * 缓存基本的对象，Integer、String、实体类等.
     *
     * @param key        缓存的Key值
     * @param value      缓存的Value值
     * @param expireTime 失效时间(单位：秒)
     * @return 缓存的对象
     */
    public <T> ValueOperations<String, T> setCacheValue(String key, T value, long expireTime);

    /**
     * 根据Key值获取缓存中的Value值.
     *
     * @param key 键值
     * @return 键值对应的Value值
     */
    public <T> T getCacheValue(String key);

    /**
     * 缓存List集合.
     *
     * @param key         键值
     * @param paramObject 需缓存的Value.
     * @param expireTime  失效时间(单位：秒)
     * @return
     */
    public ListOperations<String, T> setCacheList(String key, Object paramObject, long expireTime);

    /**
     * 移除指定List集合.
     *
     * @param key 键值
     */
    public <T> void removeCacheList(String key);

    /**
     * 获取缓存中List集合元素.
     *
     * @param key   键值
     * @param start 开始索引
     * @param end   结束索引
     * @return 对应List集合的数据
     */
    public List<T> getCacheList(String key, long start, long end);

    /**
     * 获取List总数(可用于分页).
     *
     * @param key 键值
     * @return List元素个数
     */
    public long getCacheListSize(String key);

    /**
     * 缓存Set集合.
     *
     * @param key        缓存键值
     * @param paramSet   缓存数据Value值
     * @param expireTime 失效时间(单位：秒)
     * @return 缓存数据的对象
     */
    public <T> SetOperations<String, T> setCacheSet(String key, T paramSet, long expireTime);

    /**
     * 获取Set集合数据.
     *
     * @param key 键值
     * @param <T>
     * @return 数据集合
     */
    public Set<T> getCacheSet(String key);

    /**
     * 移除指定Key中value值
     *
     * @param key   缓存键值
     * @param value 待删除Value值
     * @return 移除的个数
     */
    public <T> long removeOneOfSet(String key, T value);

    /**
     * 缓存Map数据.
     *
     * @param key 存储Map集合对象键值
     * @param k   对象键值
     * @param v   对象Value
     * @param <K>
     * @param <V>
     * @return 缓存对象
     */
    public <K, V> HashOperations<String, K, V> setCacheMap(String key, K k, V v);

    /**
     * 缓存Map数据.
     *
     * @param key      存储键值
     * @param paramMap 存储数据集合
     * @return 缓存对象
     */
    public <K, V> HashOperations<String, K, V> setCacheMap(String key, Map<K, V> paramMap);

    /**
     * 获取缓存Map数据.
     *
     * @param key 键值
     * @param <T>
     * @return 缓存对象
     */
    public <T> Map<String, T> getCacheMap(String key);

    /**
     * 删除Map集合对象中的某个Key-Value对象
     *
     * @param key 集合对象Key值
     * @param k   对象Key值
     * @param <K>
     * @return
     */
    public <K> void removeOneOfMap(String key, K k);
}
