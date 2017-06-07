package com.xinda.cache.service.impl;

import com.xinda.cache.service.Cache;
import com.xinda.cache.service.IRedisCache;
import com.xinda.system.sys.contant.RedisContants;
import com.xinda.um.user.dto.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Coundy.
 * @date 2017/6/7 23:50.
 */
public class UserFunctionCache implements Cache<SysUser> {

    private Logger logger = LoggerFactory.getLogger(UserFunctionCache.class);

    private String function;

    private String sqlId;

    private SqlSessionFactory sqlSessionFactory;

    private IRedisCache redisCacheManagerImpl;

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getSqlId() {
        return sqlId;
    }

    public void setSqlId(String sqlId) {
        this.sqlId = sqlId;
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public IRedisCache getRedisCacheManagerImpl() {
        return redisCacheManagerImpl;
    }

    @Autowired
    public void setRedisCacheManagerImpl(IRedisCache redisCacheManagerImpl) {
        this.redisCacheManagerImpl = redisCacheManagerImpl;
    }

    @Override
    public void clear() {
        getRedisCacheManagerImpl().removeCacheList(RedisContants.DEFAULT_KEY + function);
    }

    @Override
    public void reLoad() {
        clear();

        try (SqlSession sqlSession = getSqlSessionFactory().openSession()) {
            sqlSession.select(sqlId, (resultContext) -> {
                Object row = resultContext.getResultObject();
                getRedisCacheManagerImpl().setCacheList(RedisContants.DEFAULT_KEY + function, row);
            });
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
    }
}
