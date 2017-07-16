package com.xinda.system.sys.listener;

import com.xinda.cache.service.IRedisCache;
import com.xinda.system.sys.contant.RedisContants;
import com.xinda.system.sys.event.ReLoadCacheEvent;
import com.xinda.um.user.dto.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;

/**
 * 初始化加载数据到Redis监听器.
 *
 * @author Coundy.
 * @date 2017/5/26 15:07.
 */
public class LoadCacheDataListener implements ApplicationListener<ApplicationEvent> {

    private final static Logger logger = LoggerFactory.getLogger(LoadCacheDataListener.class);

    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private IRedisCache redisCacheManagerImpl;

    /**
     * 监听事件处理方法.
     *
     * @param applicationEvent
     */
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

        if (applicationEvent instanceof ContextStartedEvent) {//容器启动时触发事件
            logger.debug("ContextStartedEvent ...");

        }

        if (applicationEvent instanceof ContextStoppedEvent) {//容器停止时触发事件
            logger.debug("ContextStoppedEvent ...");

        }

        if (applicationEvent instanceof ContextRefreshedEvent) {//容器刷新时触发事件
            logger.debug("ContextRefreshedEvent ...");

        }

        if (applicationEvent instanceof ContextClosedEvent) {//容易关闭时触发事件
            logger.debug("ContextClosedEvent ...");

        }

        /*if (applicationEvent instanceof ReLoadCacheEvent) {
            logger.debug("Loading Cache Data ...");
            ReLoadCacheEvent reLoadCacheEvent = (ReLoadCacheEvent) applicationEvent;
            reLoadCacheEvent.reloadAllCache();
        }*/
        if (applicationEvent instanceof ReLoadCacheEvent) {
            logger.debug("Loading Cache Data ...");
            ReLoadCacheEvent reLoadCacheEvent = (ReLoadCacheEvent) applicationEvent;
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                sqlSession.select(reLoadCacheEvent.getSqlId(), (resultContext) -> {
                    Object row = resultContext.getResultObject();
                    redisCacheManagerImpl.setCacheList(RedisContants.OM_USER_KEY, (SysUser) row, -1);
                });
            } catch (Throwable e) {
                logger.error(e.getMessage(), e);
            }
        }

    }
}
