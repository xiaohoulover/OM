package com.xinda.system.sys.event;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 加载事件.
 * (因为实现了)ApplicationEvent类，无法手动在xml文件注册Bean.(暂未找到解决方法)
 *
 * @author Coundy.
 * @date 2017/5/26 18:02.
 */
public class ReLoadCacheEvent extends ApplicationEvent {

    private static final Logger logger = LoggerFactory.getLogger(ReLoadCacheEvent.class);

    private String msg;
    /**
     * 加载模块名称.
     */
    private String name;
    /**
     * 执行的Sql语句的方法路径.
     */
    private String sqlId;

    private SqlSessionFactory sqlSessionFactory;

    private RedisTemplate redisTemplate;

    public ReLoadCacheEvent(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ReLoadCacheEvent(Object source, String name, String sqlId) {
        super(source);
        this.name = name;
        this.sqlId = sqlId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 重新加载缓存.
     */
    public void reloadAllCache() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            sqlSession.select(sqlId, (resultContext) -> {
                Object row = resultContext.getResultObject();
                logger.info("ResultContext : [{}]", row);
                //redisCacheManagerImpl.setCacheList(RedisContants.OM_USER_KEY, );
            });
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
    }

}
