package com.shdatalink.kotlin.common.core.redis

import com.shdatalink.kotlin.common.core.jackson.KtObjectMapper
import com.shdatalink.kotlin.common.core.redis.helper.RedisHelper
import com.shdatalink.kotlin.common.core.redis.serializer.CustomJackson2JsonRedisSerializer
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

/**
 * redis 配置类
 *
 * @author huyulong
 */
@EnableCaching
@Configuration
class RedisConfiguration {

    /**
     * 配置spring cache管理器
     * @param connectionFactory RedisConnectionFactory
     * @return CacheManager
     */
    @Bean
    fun cacheManager(connectionFactory: RedisConnectionFactory): CacheManager {
        val jsonRedisSerializer = getRedisSerializer()
        val stringRedisSerializer = StringRedisSerializer()

        val configuration = RedisCacheConfiguration.defaultCacheConfig()
            // 缓存双冒号变成单冒号  test::key::123  -> test:key:123
            .computePrefixWith { prefix: String -> "$prefix:" }
            // 禁用空值
            .disableCachingNullValues()
            // 使用StringRedisSerializer来序列化和反序列化redis的key值
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer))
            // 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jsonRedisSerializer))
            // 缓存有效期 // TODO 应该从配置文件中读取
            .entryTtl(Duration.ofDays(1))
        return RedisCacheManager.builder(connectionFactory).cacheDefaults(configuration).build()
    }


    /**
     * 配置 RedisTemplate
     * @param connectionFactory RedisConnectionFactory
     * @return RedisTemplate<String, Any>
     */
    @Bean("redisTemplate")
    fun redisTemplate(connectionFactory: RedisConnectionFactory): RedisTemplate<String, Any> {
        val jsonRedisSerializer = getRedisSerializer()
        val stringRedisSerializer = StringRedisSerializer()

        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.setConnectionFactory(connectionFactory)
        redisTemplate.keySerializer = stringRedisSerializer
        redisTemplate.valueSerializer = jsonRedisSerializer
        redisTemplate.hashKeySerializer = stringRedisSerializer
        redisTemplate.hashValueSerializer = jsonRedisSerializer

        return redisTemplate
    }


    /**
     * 配置 StringRedisTemplate
     * @param connectionFactory RedisConnectionFactory
     * @return StringRedisTemplate
     */
    @Bean("stringRedisTemplate")
    fun stringRedisTemplate(connectionFactory: RedisConnectionFactory): StringRedisTemplate {
        val stringRedisTemplate = StringRedisTemplate()
        stringRedisTemplate.setConnectionFactory(connectionFactory)
        return stringRedisTemplate
    }

    /**
     * 配置Redis帮助类
     * @param redisTemplate RedisTemplate<String, Any>
     * @return RedisOps
     */
    @Bean
    fun redisHelper(redisTemplate: RedisTemplate<String, Any>): RedisHelper {
        return RedisHelper(redisTemplate)
    }

    /**
     * 获取配置好的 Jackson2JsonRedisSerializer对象
     *
     * @return
     */
    private fun getRedisSerializer(): CustomJackson2JsonRedisSerializer<Any> {
        return CustomJackson2JsonRedisSerializer(Any::class.java).also {
            it.setObjectMapper(KtObjectMapper.instance)
        }
    }
}
