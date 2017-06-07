package com.xinda.cache.service.impl;

import com.xinda.cache.service.Cache;
import com.xinda.cache.service.ICacheManager;

import java.util.List;

/**
 * 缓存功能模块数据管理实现类.
 *
 * @author Coundy.
 * @date 2017/6/7 23:44.
 */
public class CacheManagerImpl implements ICacheManager {

    private List<Cache> caches;

    public List<Cache> getCaches() {
        return caches;
    }

    public void setCaches(List<Cache> caches) {
        this.caches = caches;
        if (null != caches) {
            for (Cache cache : caches) {
                cache.reLoad();
            }
        }
    }
}
