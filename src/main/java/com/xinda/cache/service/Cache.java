package com.xinda.cache.service;

/**
 * 缓存值对象.
 *
 * @author Coundy.
 * @date 2017/6/7 23:48.
 */
public interface Cache<T> {

    void clear();

    void reLoad();

}
