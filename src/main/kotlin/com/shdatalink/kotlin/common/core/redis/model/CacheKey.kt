package com.shdatalink.kotlin.common.core.redis.model

import org.apache.commons.lang3.StringUtils
import org.springframework.data.redis.core.RedisTemplate
import com.shdatalink.kotlin.common.core.redis.helper.NullVal
import java.time.Duration
import java.util.*

/**
 * 缓存key
 *
 * @author huyulong
 */
interface CacheKey {
    /**
     * key 前缀
     */
    fun getPrefix(): String

    /**
     * key 过期时间
     */
    fun getExpire(): Duration? = null

    /**
     * 获取redisTemplate
     */
    fun getRedisTemplate(): RedisTemplate<String, Any>
}

/**
 * 构造完整key
 *
 * @param prefix 前缀 不可空
 * @param suffix key后缀 可空
 */
fun CacheKey.buildKey(prefix: String, suffix: Any?): String {
    return if (Objects.nonNull(suffix)) {
        StringUtils.join(":", prefix, suffix)
    } else {
        prefix
    }
}

/**
 * 设置key过期
 *
 * @param key     完整的key 不可空
 * @param expire  超时时间 可空 为空不设置key过期
 */
fun CacheKey.expire(key: String, expire: Duration?) {
    expire?.let {
        getRedisTemplate().expire(key, it)
    }
}

/**
 * 判断缓存值是否为空对象
 *
 * @param value T
 * @return Boolean
 */
fun <T> CacheKey.isNullVal(value: T): Boolean {
    val isNull = value == null || NullVal::class.java == value.javaClass
    return isNull || value is Map<*, *> && (value as Map<*, *>).isEmpty()
}

/**
 * 创建一个空对象
 *
 * @return NullVal
 */
fun CacheKey.createNullVal(): NullVal = NullVal()

/**
 * 返回正常值或null值
 *
 * @param value T
 * @return T?
 */
fun <T> CacheKey.returnVal(value: T): T? = if (isNullVal(value)) null else value
