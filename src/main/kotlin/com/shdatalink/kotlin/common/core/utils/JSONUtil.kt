package com.shdatalink.kotlin.common.core.utils

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.shdatalink.kotlin.common.constants.DatePattern
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import java.math.BigDecimal
import java.math.BigInteger
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

/**
 * 封装Jackson得到的JSON工具类
 */
object JSONUtil {

    private val logger = LoggerFactory.getLogger(this::class.java)
    private val objectMapper: ObjectMapper = ObjectMapper()
    private val objectWrite: ObjectWriter = objectMapper.writerWithDefaultPrettyPrinter()

    init {
        // 禁止:将日期写为时间戳，解决日期格式化问题
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        // 禁止:FAIL_ON_EMPTY_BEANS
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
        // 允许：枚举使用toString方式
        objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)

        // 自定义扩展
        val javaTimeModule = JavaTimeModule()
        javaTimeModule.addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer(DatePattern.NORM_DATETIME_FORMATTER))
        javaTimeModule.addSerializer(LocalTime::class.java, LocalTimeSerializer(DatePattern.NORM_TIME_FORMATTER))
        javaTimeModule.addSerializer(LocalDate::class.java, LocalDateSerializer(DatePattern.NORM_DATE_FORMATTER))
        javaTimeModule.addSerializer(Long::class.java, ToStringSerializer.instance)
        javaTimeModule.addSerializer(Long::class.javaObjectType, ToStringSerializer.instance)
        javaTimeModule.addSerializer(BigDecimal::class.java, ToStringSerializer.instance)
        javaTimeModule.addSerializer(BigInteger::class.java, ToStringSerializer.instance)
        javaTimeModule.addDeserializer(LocalDateTime::class.java, LocalDateTimeDeserializer(DatePattern.NORM_DATETIME_FORMATTER))
        javaTimeModule.addDeserializer(LocalTime::class.java, LocalTimeDeserializer(DatePattern.NORM_TIME_FORMATTER))
        javaTimeModule.addDeserializer(LocalDate::class.java, LocalDateDeserializer(DatePattern.NORM_DATE_FORMATTER))

        // 注册模块
        objectMapper.registerModule(Jdk8Module())
        objectMapper.registerModule(KotlinModule.Builder().build())
        objectMapper.registerModule(javaTimeModule)
    }

    /**
     * 对象转json字符串
     *
     * @param value Any         对象
     * @param pretty Boolean?   是否格式化输出
     * @return String           json字符串
     */
    fun toJsonStr(value: Any?, pretty: Boolean? = false): String? {
        if (value == null) {
            return null
        }
        // 如果对象是字符串，直接返回
        if (value is String) {
            return value
        }

        try {
            // 判断是否需要格式化输出
            return if (pretty == true) {
                objectWrite.writeValueAsString(value)
            } else {
                objectMapper.writeValueAsString(value)
            }
        } catch (e: Exception) {
            logger.error("对象转json字符串失败", e)
        }
        return null
    }


    /**
     * json字符串转对象
     *
     * @param json String?    json字符串
     * @param clazz Class<T>  对象class
     * @return T?
     */
    fun <T> parseObject(json: String?, clazz: Class<T>): T? {
        if (StringUtils.isBlank(json)) {
            return null
        }

        try {
            return objectMapper.readValue(json, clazz)
        } catch (e: Exception) {
            logger.error("json字符串转对象失败", e)
        }
        return null
    }

    /**
     * json字符串转对象
     *
     * 说明：
     * 用于转换有泛型的对象
     *
     * 使用方法：
     * ```
     * // 将jsonStr转成List<SysUser>对象
     * val userList: List<SysUser>? = JSONUtil.parseObject(jsonStr, object: TypeReference<List<SysUser>>(){})
     *
     * // 将jsonStr转成ApiResult<Map<String, Any>>对象
     * val result: ApiResult<Map<String, Any>>? = JSONUtil.parseObject(jsonStr, object: TypeReference<ApiResult<Map<String, Any>>>(){})
     * ```
     * @param json String?    json字符串
     * @param valueTypeRef TypeReference<T>  值类型参考
     * @return T?
     */
    fun <T> parseObject(json: String?, valueTypeRef: TypeReference<T>): T? {
        if (StringUtils.isBlank(json)) {
            return null
        }

        try {
            return objectMapper.readValue(json, valueTypeRef)
        } catch (e: Exception) {
            logger.error("json字符串转对象失败", e)
        }
        return null
    }
}
