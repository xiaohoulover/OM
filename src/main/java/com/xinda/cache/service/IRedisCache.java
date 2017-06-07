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
     * 缓存基本的对象，Integer、String、实体类等.
     *
     * @param key   缓存的Key值
     * @param value 缓存的Value值
     * @return 缓存的对象
     */
    public <T> ValueOperations<String, T> setCacheObject(String key, T value);

    /**
     * 根据Key值获取缓存中的Value值.
     *
     * @param key 键值
     * @return 键值对应的Value值
     */
    public <T> T getCacheObject(String key);

    /**
     * 缓存List集合.
     *
     * @param key         键值
     * @param paramObject 需缓存的Value.
     * @return
     */
    public ListOperations<String, T> setCacheList(String key, Object paramObject);

    /**
     * 移除指定List集合.
     *
     * @param key 键值
     */
    public <T> void removeCacheList(String key);

    /**
     * 获取缓存中List集合元素.
     *
     * @param key 键值
     * @param <T>
     * @return 对应List集合的数据
     */
    public <T> List<T> getCacheList(String key);

    /**
     * 缓存Set集合.
     *
     * @param key      缓存键值
     * @param paramSet 缓存数据Value值
     * @param <T>
     * @return 缓存数据的对象
     */
    public <T> SetOperations<String, T> setCacheSet(String key, Set<T> paramSet);

    /**
     * 获取Set集合数据.
     *
     * @param key 键值
     * @param <T>
     * @return 数据集合
     */
    public <T> Set<T> getCacheSet(String key);

    /**
     * 缓存Map数据.
     *
     * @param key      存储键值
     * @param paramMap 存储数据集合
     * @return 缓存对象
     */
    public <K, V> HashOperations<String, K, V> getCacheMap(String key, Map<K, V> paramMap);

    /**
     * 获取缓存Map数据.
     *
     * @param key 键值
     * @param <T>
     * @return 缓存对象
     */
    public <T> Map<String, T> getCacheMap(String key);
}
