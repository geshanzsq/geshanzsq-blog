package com.geshanzsq.blog.common.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * redis 工具类
 *
 * @author geshanzsq
 * @date 2022/3/20
 */
@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置缓存基本对象
     * @param key 缓存键
     * @param value 缓存值
     */
    public <T> void set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置缓存基本对象
     * @param key 缓存键
     * @param value 缓存值
     * @param expire 过期时间
     * @param timeUnit 时间单位
     * @param <T>
     */
    public <T> void set(String key, T value, long expire, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, expire, timeUnit);
    }

    /**
     * 设置缓存有效期
     * @param key 缓存键
     * @param expire 过期时间
     */
    public boolean expire(String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    /**
     * 设置缓存有效期
     * @param key 缓存键
     * @param expire 过期时间
     * @param timeUnit 时间单位
     */
    public boolean expire(String key, long expire, TimeUnit timeUnit) {
        return redisTemplate.expire(key, expire, timeUnit);
    }

    /**
     * 获取缓存基本对象
     * @param key 缓存键
     */
    public <T> T get(String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 通配符获取缓存键
     * @param pattern 通配符缓存键
     */
    public Collection<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 删除缓存对象
     * @param key 缓存键
     */
    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除缓存对象
     * @param collection 多个对象
     */
    public long delete(Collection collection) {
        return redisTemplate.delete(collection);
    }

    /**
     * 获取缓存有效期
     * @param key 缓存键
     */
    public Long getExpireTime(String key) {
        return redisTemplate.opsForValue().getOperations().getExpire(key);
    }

}
