package com.kingtrustcloud.familytree.cache;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @version V1.0
 * @Title: RedisCacheServiceImpl
 * @Package com.kingtrustcloud.familytree.cache
 * @Description: (redis缓存实现类)
 * @author: tanyong
 * @date: 2020/3/6 10:41
 */
@ConditionalOnProperty(prefix = "redis",value = "enable",havingValue = "true",matchIfMissing = false)
@Component
public class RedisCacheServiceImpl implements CacheService {

    @Override
    public void setKey(String key, long expire) {
        System.out.println("redis cache");
    }

    @Override
    public boolean isExpire(String key) {
        return false;
    }
}
