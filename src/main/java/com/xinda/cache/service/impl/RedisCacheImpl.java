package com.xinda.cache.service.impl;

import com.xinda.cache.service.IRedisCache;
import org.springframework.data.redis.core.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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

    @Override
    public boolean isContainsKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public <T> void removeKey(T t) {
        redisTemplate.delete(t);
    }

    @Override
    public <T> ValueOperations<String, T> setCacheValue(String key, T value, long expireTime) {
        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
        if (expireTime > 0) {
            valueOperations.set(key, value, expireTime, TimeUnit.SECONDS);
        }
        return valueOperations;
    }

    @Override
    public <T> T getCacheValue(String key) {
        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    @Override
    public ListOperations<String, T> setCacheList(String key, Object paramObject, long expireTime) {
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
        if (expireTime > 0) {
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        }
        return listOperations;
    }

    @Override
    public <T> void removeCacheList(String key) {
        /*ListOperations<String, T> listOperations = redisTemplate.opsForList();
        listOperations.trim(key, -1, 0);*/
        removeKey(key);
    }

    @Override
    public List<T> getCacheList(String key, long start, long end) {
        ListOperations<String, T> listOperations = redisTemplate.opsForList();
        return listOperations.range(key, start, end);
    }

    public long getCacheListSize(String key) {
        ListOperations<String, T> listOperations = redisTemplate.opsForList();
        return listOperations.size(key);
    }

    @Override
    public <T> SetOperations<String, T> setCacheSet(String key, T paramSet, long expireTime) {
        SetOperations<String, T> setOperations = redisTemplate.opsForSet();
        if (paramSet instanceof Set) {//集合
            Set set = (Set) paramSet;
            if (null != set || !set.isEmpty()) {
                Iterator<T> iterator = set.iterator();
                while (iterator.hasNext()) {
                    setOperations.add(key, iterator.next());
                }
            }
        } else {
            setOperations.add(key, paramSet);
        }
        if (expireTime > 0) {
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        }
        return setOperations;
    }

    @Override
    public Set<T> getCacheSet(String key) {
        SetOperations<String, T> setOperations = redisTemplate.opsForSet();
        return setOperations.members(key);
    }

    @Override
    public <T> long removeOneOfSet(String key, T value) {
        SetOperations<String, T> setOperations = redisTemplate.opsForSet();
        return setOperations.remove(key, value);
    }

    @Override
    public <K, V> HashOperations<String, K, V> setCacheMap(String key, K k, V v) {
        HashOperations<String, K, V> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(key, k, v);
        return hashOperations;
    }

    @Override
    public <K, V> HashOperations<String, K, V> setCacheMap(String key, Map<K, V> paramMap) {
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

    @Override
    public <T> Map<String, T> getCacheMap(String key) {
        HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
        return hashOperations.entries(key);
    }

    @Override
    public <K> void removeOneOfMap(String key, K k) {
        HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(key, k);
    }

}
