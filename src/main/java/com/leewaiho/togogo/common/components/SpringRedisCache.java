package com.leewaiho.togogo.common.components;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * redis缓存
 * <p>
 * 采用Jedis或Jedis Sentinel
 */
@Component
public class SpringRedisCache {
    
    public final static String CAHCENAME = "cache";//缓存名
    private static final Logger logger = Logger.getLogger(SpringRedisCache.class);
    @Autowired
    private RedisTemplate redisTemplate;
    
    public void closeConnection() {
        redisTemplate.exec();
        RedisConnectionUtils.unbindConnection(redisTemplate.getConnectionFactory());
    }
    
    public <T> boolean putCache(String key, T obj) {
        redisTemplate.opsForValue().set(key, obj);
        return true;
    }
    
    public <T> void putCacheWithExpireTime(String key, T obj, final long expireTime) {
        redisTemplate.opsForValue().set(key, obj, expireTime, TimeUnit.SECONDS);
    }
    
    public <T> boolean putListCache(String key, List<T> objList) {
        redisTemplate.opsForValue().set(key, objList);
        return true;
    }
    
    public <T> boolean putListCacheWithExpireTime(String key, List<T> objList, final long expireTime) {
        redisTemplate.opsForValue().set(key, objList, expireTime);
        return true;
    }
    
    public <T> boolean setObjExpireTime(String key, final long expireTime) {
        redisTemplate.opsForValue().set(key, expireTime);
        return true;
    }
    
    public <T> T getCache(final String key, Class<T> targetClass) {
        T obj = (T) redisTemplate.opsForValue().get(key);
        return obj;
    }
    
    public <T> List<T> getListCache(final String key, Class<T> targetClass) {
        /*byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
            @Override
			public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.get(key.getBytes());
			}
		});
		if (result == null) {
			return null;
		}
		return ProtoStuffSerializerUtil.deserializeList(result, targetClass);*/
        List<T> retList = (List<T>) redisTemplate.opsForValue().get(key);
        return retList;
    }
    
    /**
     * 精确删除key
     *
     * @param key
     */
    public void deleteCache(String key) {
        redisTemplate.delete(key);
    }
    
    /**
     * 模糊删除key
     *
     * @param pattern
     */
    public void deleteCacheWithPattern(String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }
    
    /**
     * 清空所有缓存
     */
    public void clearCache() {
        deleteCacheWithPattern(SpringRedisCache.CAHCENAME + "|*");
    }
    
    public boolean containsKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Throwable t) {
            logger.error("判断缓存存在失败key[" + key + ", error[" + t + "]");
        }
        return false;
    }
    
    /**
     * 指定缓存的失效时间
     *
     * @param key  缓存KEY
     * @param time 失效时间(秒)
     */
    public void expire(String key, long time) {
        if (time > 0) {
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
    }
    
    public long getExpireTime(String key) {
        return redisTemplate.getExpire(key, TimeUnit.MILLISECONDS) + System.currentTimeMillis();
        //return redisTemplate.getExpire(key);
    }
    
    /**
     * 模糊查询keys
     *
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }
    
    public String getKey(String identify, String scope, String action) {
        StringBuffer keyName = new StringBuffer(identify).append("_").append(scope).append(action);
        return keyName.toString().toUpperCase();
    }
}
