package com.kingtrustcloud.familytree.cache;

/**
 * 缓存服务接口类
 */
public interface CacheService {

    /**
     * 缓存一个键，设置过期时间
     * @param key
     * @param expire 过期时间  毫秒
     */
    void setKey(String key,long expire);

    /**
     * 判断一个键是否已过期
     * @param key
     * @return
     */
    boolean isExpire(String key);
}
