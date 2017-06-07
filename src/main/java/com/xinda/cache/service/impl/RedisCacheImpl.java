package com.xinda.cache.service.impl;

import com.xinda.cache.service.IRedisCache;
import com.xinda.um.user.dto.SysUser;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis缓存管理实现类.
 *
 * @author Coundy.
 * @date 2017/5/26 14:56.
 */
public class RedisCacheImpl<T> implements IRedisCache<T> {

    protected RedisTemplate redisTemplate;

    /**
     * 设置redisTemplate
     *
     * @param redisTemplate the redisTemplate to set
     */
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 获取 RedisSerializer.
     *
     * @return
     */
    public RedisSerializer<String> getRedisSerializer() {
        return redisTemplate.getStringSerializer();
    }

    @Override
    public <T> ValueOperations<String, T> setCacheObject(String key, T value) {
        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
        return valueOperations;
    }

    /**
     * 根据Key值获取缓存中的Value值.
     *
     * @param key 键值
     * @return 键值对应的Value值
     */
    @Override
    public <T> T getCacheObject(String key) {
        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    /**
     * 缓存List集合.
     *
     * @param key         键值
     * @param paramObject 需缓存的Value.
     * @return
     */
    @Override
    public ListOperations<String, T> setCacheList(String key, Object paramObject) {
        ListOperations<String, T> listOperations = redisTemplate.opsForList();
        if (null != paramObject) {
            if (paramObject instanceof List) {//存储集合
                List<T> paramList = (List<T>) paramObject;
                if (!paramList.isEmpty()) {
                    listOperations.rightPushAll(key, paramList);
                    /*for (T t : paramList) {
                        listOperations.rightPush(key, t);
                    }*/
                }
            } else {//存储单个元素
                listOperations.rightPush(key, (T) paramObject);
            }
        }
        return listOperations;
    }

    @Override
    public <T> void removeCacheList(String key) {
        ListOperations<String, T> listOperations = redisTemplate.opsForList();
        listOperations.trim(key, -1, 0);
    }

    /**
     * 获取缓存中List集合元素.
     *
     * @param key 键值
     * @param <T>
     * @return 对应List集合的数据
     */
    @Override
    public <T> List<T> getCacheList(String key) {
        ListOperations<String, T> listOperations = redisTemplate.opsForList();
        return listOperations.range(key, 0, -1);
    }

    /**
     * 缓存Set集合.
     *
     * @param key      缓存键值
     * @param paramSet 缓存数据Value值
     * @param <T>
     * @return 缓存数据的对象
     */
    @Override
    public <T> SetOperations<String, T> setCacheSet(String key, Set<T> paramSet) {
        SetOperations<String, T> setOperations = redisTemplate.opsForSet();
        if (null == paramSet || !paramSet.isEmpty()) {
            Iterator<T> iterator = paramSet.iterator();
            while (iterator.hasNext()) {
                setOperations.add(key, iterator.next());
            }
        }
        return setOperations;
    }

    /**
     * 获取Set集合数据.
     *
     * @param key 键值
     * @param <T>
     * @return 数据集合
     */
    @Override
    public <T> Set<T> getCacheSet(String key) {
        SetOperations<String, T> setOperations = redisTemplate.opsForSet();
        return setOperations.members(key);
    }

    /**
     * 缓存Map数据.
     *
     * @param key      存储键值
     * @param paramMap 存储数据集合
     * @return 缓存对象
     */
    @Override
    public <K, V> HashOperations<String, K, V> getCacheMap(String key, Map<K, V> paramMap) {
        HashOperations<String, K, V> hashOperations = redisTemplate.opsForHash();
        hashOperations.putAll(key, paramMap);
        //Or
        /*if (null == paramMap || !paramMap.isEmpty()) {
            for (Map.Entry<String, T> stringTEntry : paramMap.entrySet()) {
                hashOperations.put(key, stringTEntry.getKey(), stringTEntry.getValue());
            }
        }*/
        return hashOperations;
    }

    /**
     * 获取缓存Map数据.
     *
     * @param key 键值
     * @param <T>
     * @return 缓存对象
     */
    @Override
    public <T> Map<String, T> getCacheMap(String key) {
        HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
        return hashOperations.entries(key);
    }

}
