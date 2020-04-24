package com.kingtrustcloud.familytree.cache;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version V1.0
 * @Title: LocalCacheServiceImpl
 * @Package com.kingtrustcloud.familytree.cache
 * @Description: (本地缓存实现类)
 * @author: tanyong
 * @date: 2020/3/5 18:41
 */
@ConditionalOnMissingBean(value = RedisCacheServiceImpl.class)
@Component
public class LocalCacheServiceImpl implements CacheService{

    /**
     * 缓存Map
     */
    private ConcurrentHashMap<String,Long> cacheMap=new ConcurrentHashMap<>();

    /**
     * 最多缓存个数，防止内存不足
     */
    private final int MAX_COUNT=10000000;

    private int cleanCount=0;

    /**
     * 清除阈值
     */
    private final int CLEAN_COUNT=500;

    @Override
    public void setKey(String key, long expire) {
        if(cacheMap.size()>MAX_COUNT) {
            cleanCache();
            return;
        }
        cacheMap.put(key,System.currentTimeMillis()+expire);
        if(cleanCount++>CLEAN_COUNT) {
            cleanCount=0;
            cleanCache();
        }
    }

    @Override
    public boolean isExpire(String key) {
        Long expireTime = cacheMap.get(key);
        if(expireTime==null) {
            return true;
        } else if(expireTime>System.currentTimeMillis()){
            return false;
        }
        return true;
    }

    /**
     * 清除过期key
     */
    private void cleanCache() {
        for(Map.Entry<String, Long> entry:cacheMap.entrySet()) {
            if(entry.getValue()<System.currentTimeMillis()) {
                cacheMap.remove(entry.getKey());
            }
        }
    }

}
