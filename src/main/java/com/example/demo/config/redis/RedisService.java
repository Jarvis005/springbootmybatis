package com.example.demo.config.redis;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 前缀
     */
    public static final String KEY_PREFIX_VALUE = "kaluli:value:";
    public static final String KEY_PREFIX_SET   = "kaluli:set:";
    public static final String KEY_PREFIX_LIST  = "kaluli:list:";
    public static final String KEY_PREFIX_HASH  = "kaluli:hash:";

    // 跟车宝
    private static final String KEY_PREFIX_HASH_FOLL_CAR  = "mft100:truckfollower:";
    //-------------------------------------------------------新增缓存 start-----------------------------------------

    /**
     * 缓存value操作
     *
     * @param k
     * @param v
     * @param time
     * @return
     */
    public boolean cacheValue(String k, String v, long time) {
        String key = KEY_PREFIX_VALUE + k;
        try {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            valueOps.set(key, v);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存value操作
     *
     * @param k
     * @param v
     * @return
     */
    public boolean cacheValue(String k, String v) {
        return cacheValue(k, v, -1);
    }

    /**
     * 缓存 object value
     *
     * @param k
     * @param v
     * @return
     */
    public boolean cacheObject(String k, Object v) {
        String json = JSON.toJSONString(v);
        return cacheValue(k, json);
    }

    /**
     * 缓存 object value
     *
     * @param k
     * @param v
     * @param time
     * @return
     */
    public boolean cacheObject(String k, Object v, long time) {
        String json = JSON.toJSONString(v);
        return cacheValue(k, json, time);
    }

    /**
     * 缓存set value操作
     *
     * @param k
     * @param v
     * @param time
     * @return
     */
    public boolean cacheSetValue(String k, String v, long time) {
        String key = KEY_PREFIX_SET + k;
        try {
            SetOperations<String, String> valueOps = redisTemplate.opsForSet();
            valueOps.add(key, v);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存set  value
     *
     * @param k
     * @param v
     * @return
     */
    public boolean cacheSetValue(String k, String v) {
        return cacheSetValue(k, v, -1);
    }

    /**
     * 缓存set
     *
     * @param k
     * @param v
     * @param time
     * @return
     */
    public boolean cacheSet(String k, Set<String> v, long time) {
        String key = KEY_PREFIX_SET + k;
        try {
            SetOperations<String, String> setOps = redisTemplate.opsForSet();
            setOps.add(key, v.toArray(new String[v.size()]));
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存set
     *
     * @param k
     * @param v
     * @return
     */
    public boolean cacheSet(String k, Set<String> v) {
        return cacheSet(k, v, -1);
    }

    /**
     * list缓存 value
     *
     * @param k
     * @param v
     * @param time
     * @return
     */
    public boolean cacheListValue(String k, String v, long time) {
        String key = KEY_PREFIX_LIST + k;
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            listOps.rightPush(key, v);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存list value
     *
     * @param k
     * @param v
     * @return
     */
    public boolean cacheListValue(String k, String v) {
        return cacheListValue(k, v, -1);
    }

    /**
     * 缓存list
     *
     * @param k
     * @param v
     * @param time
     * @return
     */
    public boolean cacheList(String k, List<String> v, long time) {
        String key = KEY_PREFIX_LIST + k;
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            long l = listOps.rightPushAll(key, v);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存list
     *
     * @param k
     * @param v
     * @return
     */
    public boolean cacheList(String k, List<String> v) {
        return cacheList(k, v, -1);
    }

    /**
     * 缓存hash key-value
     *
     * @param k
     * @param hashKey
     * @param v
     * @param time
     * @return
     */
    public boolean cacheHashValue(String k, String hashKey, String v, long time) {
        String key = KEY_PREFIX_HASH + k;
        try {
            HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
            hashOps.put(key, hashKey, v);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存hash key-value
     *
     * @param k
     * @param hashKey
     * @param v
     * @return
     */
    public boolean cacheHashValue(String k, String hashKey, String v) {
        return cacheHashValue(k, hashKey, v, -1);
    }

    /**
     * 缓存 hash
     *
     * @param k
     * @param v
     * @param time
     * @return
     */
    public boolean cacheHash(String k, Map<String, String> v, long time) {
        String key = KEY_PREFIX_HASH + k;
        try {
            HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
            hashOps.putAll(key, v);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存 hash
     *
     * @param k
     * @param v
     * @return
     */
    public boolean cacheHash(String k, Map<String, String> v) {
        return cacheHash(k, v, -1);
    }


//---------------------------------------------------------新增缓存 end ------------------------------------------


//---------------------------------------------------------读取缓存 start ------------------------------------------

    /**
     * 获取缓存
     *
     * @param k
     * @return
     */
    public String getValue(String k) {
        try {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            return valueOps.get(KEY_PREFIX_VALUE + k);
        } catch (Throwable t) {
            logger.error("获取缓存失败key[" + KEY_PREFIX_VALUE + k + ", error[" + t + "]");
        }
        return null;
    }

    /**
     * 获取缓存set数据
     *
     * @param k
     * @return
     */
    public Set<String> getSet(String k) {
        try {
            SetOperations<String, String> setOps = redisTemplate.opsForSet();
            return setOps.members(KEY_PREFIX_SET + k);
        } catch (Throwable t) {
            logger.error("获取set缓存失败key[" + KEY_PREFIX_SET + k + ", error[" + t + "]");
        }
        return null;
    }

    /**
     * 获取list缓存
     *
     * @param k
     * @param start
     * @param end
     * @return
     */
    public List<String> getList(String k, long start, long end) {
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            return listOps.range(KEY_PREFIX_LIST + k, start, end);
        } catch (Throwable t) {
            logger.error("获取list缓存失败key[" + KEY_PREFIX_LIST + k + ", error[" + t + "]");
        }
        return null;
    }

    /**
     * 获取总条数, 可用于分页
     *
     * @param k
     * @return
     */
    public long getListSize(String k) {
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            return listOps.size(KEY_PREFIX_LIST + k);
        } catch (Throwable t) {
            logger.error("获取list长度失败key[" + KEY_PREFIX_LIST + k + "], error[" + t + "]");
        }
        return 0;
    }

    /**
     * 获取总条数, 可用于分页
     *
     * @param listOps
     * @param k
     * @return
     */
    public long getListSize(ListOperations<String, String> listOps, String k) {
        try {
            return listOps.size(KEY_PREFIX_LIST + k);
        } catch (Throwable t) {
            logger.error("获取list长度失败key[" + KEY_PREFIX_LIST + k + "], error[" + t + "]");
        }
        return 0;
    }

    /**
     * 获取缓存 hash  value
     *
     * @param k
     * @return
     */
    public String getHashValue(String k, String hashKey) {
        try {
            HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
            return hashOps.get(KEY_PREFIX_HASH + k, hashKey);
        } catch (Throwable t) {
            logger.error("获取缓存失败key[" + KEY_PREFIX_HASH + k + "], hashKey[" + hashKey + "], error[" + t + "]");
        }
        return null;
    }

    /**
     * 获取缓存 hash  value
     *
     * @param k
     * @return
     */
    public List<String> getMultiHashValue(String k, List<String> hashKeys) {
        try {
            HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
            return hashOps.multiGet(KEY_PREFIX_HASH + k, hashKeys);
        } catch (Throwable t) {
            logger.error("获取缓存失败key[" + KEY_PREFIX_HASH + k + "], hashKey[" + JSON.toJSONString(hashKeys) + "], " +
                    "error[" + t + "]");
        }
        return null;
    }

    /**
     * 获取缓存
     *
     * @param k
     * @return
     */
    public Map<String, String> getHash(String k) {
        try {
            HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
            return hashOps.entries(KEY_PREFIX_HASH + k);
        } catch (Throwable t) {
            logger.error("获取缓存失败key[" + KEY_PREFIX_HASH + k + "], error[" + t + "]");
        }
        return null;
    }

    /**
     * 获取hash缓存 所有key
     *
     * @param k
     * @return
     */
    public Set<String> getHashKeys(String k) {
        try {
            HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
            return hashOps.keys(KEY_PREFIX_HASH + k);
        } catch (Throwable t) {
            logger.error("获取缓存失败key[" + KEY_PREFIX_HASH + k + "], error[" + t + "]");
        }
        return null;
    }

    /**
     * 获取hash缓存 所有values
     *
     * @param k
     * @return
     */
    public List<String> getHashValues(String k) {
        try {
            HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
            return hashOps.values(KEY_PREFIX_HASH + k);
        } catch (Throwable t) {
            logger.error("获取缓存失败key[" + KEY_PREFIX_HASH + k + "], error[" + t + "]");
        }
        return null;
    }

//---------------------------------------------------------读取缓存 end ------------------------------------------


//--------------------------------------------------------- 移除 start ------------------------------------------

    /**
     * 移除缓存
     *
     * @param k
     * @return
     */
    public boolean removeValue(String k) {
        return remove(KEY_PREFIX_VALUE + k);
    }

    /**
     * 移除缓存
     *
     * @param k
     * @return
     */
    public boolean removeSet(String k) {
        return remove(KEY_PREFIX_SET + k);
    }

    /**
     * 移除缓存
     *
     * @param k
     * @return
     */
    public boolean removeList(String k) {
        return remove(KEY_PREFIX_LIST + k);
    }

    /**
     * 移除缓存
     *
     * @param k
     * @return
     */
    public boolean removeHash(String k) {
        return remove(KEY_PREFIX_HASH + k);
    }

    /**
     * 移除缓存
     *
     * @param key
     * @return
     */
    private boolean remove(String key) {
        try {
            redisTemplate.delete(key);
            return true;
        } catch (Throwable t) {
            logger.error("删除缓存失败key[" + key + ", error[" + t + "]");
        }
        return false;
    }

    /**
     * 移除缓存hash 的value
     *
     * @param k
     * @param hashKey
     * @return
     */
    public boolean removeHashValue(String k, String hashKey) {
        try {
            HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
            hashOps.delete(KEY_PREFIX_HASH + k, hashKey);
            return true;
        } catch (Throwable t) {
            logger.error("删除缓存失败key[" + KEY_PREFIX_HASH + k + "],hashKey[" + hashKey + "], error[" + t + "]");
        }
        return false;
    }

    /**
     * 移除缓存 set value
     * @param k
     * @param value
     * @return
     */
    public boolean removeSetValue(String k, String value) {
        try {
            SetOperations<String, String> setOps = redisTemplate.opsForSet();
            setOps.remove(KEY_PREFIX_HASH + k, value);
            return true;
        } catch (Throwable t) {
            logger.error("删除缓存失败key[" + KEY_PREFIX_HASH + k + "],value[" + value + "], error[" + t + "]");
        }
        return false;
    }

//--------------------------------------------------------- 移除 end ------------------------------------------

//--------------------------------------------------------- 判断缓存是否存在 start ------------------------------

    /**
     * 判断缓存是否存在
     *
     * @param k
     * @return
     */
    public boolean hasValueKey(String k) {
        return hasKey(KEY_PREFIX_VALUE + k);
    }

    /**
     * 判断缓存是否存在
     *
     * @param k
     * @return
     */
    public boolean hasSetKey(String k) {
        return hasKey(KEY_PREFIX_SET + k);
    }

    /**
     * 判断缓存是否存在
     *
     * @param k
     * @return
     */
    public boolean hasListKey(String k) {
        return hasKey(KEY_PREFIX_LIST + k);
    }

    /**
     * 判断缓存是否存在
     *
     * @param k
     * @return
     */
    public boolean hasHashKey(String k) {
        return hasKey(KEY_PREFIX_HASH + k);
    }

    private boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Throwable t) {
            logger.error("判断缓存存在失败key[" + key + ", error[" + t + "]");
        }
        return false;
    }

//--------------------------------------------------------- 判断缓存是否存在 end ------------------------------------------

//--------------------------------------------------------- 设置缓存过期时间 start ----------------------------------------

    /**
     * 设置 string 缓存过期时间
     *
     * @param k
     * @param seconds
     * @return
     */
    public boolean setValueExpireTimeForValue(String k, long seconds) {
        String key = KEY_PREFIX_VALUE + k;
        return setExpireTime(key, seconds);
    }

    /**
     * 设置 set 缓存过期时间
     *
     * @param k
     * @param seconds
     * @return
     */
    public boolean setExpireTimeForSet(String k, long seconds) {
        String key = KEY_PREFIX_SET + k;
        return setExpireTime(key, seconds);
    }

    /**
     * 设置 hash 缓存过期时间
     *
     * @param k
     * @param seconds
     * @return
     */
    public boolean setExpireTimeForHash(String k, long seconds) {
        String key = KEY_PREFIX_HASH + k;
        return setExpireTime(key, seconds);
    }

    /**
     * 设置 list 缓存过期时间
     *
     * @param k
     * @param seconds
     * @return
     */
    public boolean setExpireTimeForList(String k, long seconds) {
        String key = KEY_PREFIX_LIST + k;
        return setExpireTime(key, seconds);
    }


    private boolean setExpireTime(String key, long seconds) {
        Boolean res = redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
        return res == null ? false : true;
    }
//--------------------------------------------------------- 设置缓存过期时间 end ----------------------------------------

    // 跟车宝 start
    public String getFlowCarHashValue(String k, String hashKey) {
        try {
            HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
            return hashOps.get(KEY_PREFIX_HASH_FOLL_CAR + k, hashKey);
        } catch (Throwable t) {
            logger.error("Get Hash Value Fail key[" + KEY_PREFIX_HASH + k + "], hashKey[" + hashKey + "], error[" + t + "]");
        }
        return null;
    }
    // 跟车宝 end
}
