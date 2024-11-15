package com.shdatalink.kotlin.common.constants

import org.apache.commons.lang3.time.FastDateFormat
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * 日期时间格式
 */
object DatePattern {

    const val NORM_YEAR_PATTERN = "yyyy"
    const val NORM_MONTH_PATTERN = "yyyy-MM"
    val NORM_MONTH_FORMAT: FastDateFormat = FastDateFormat.getInstance("yyyy-MM")
    val NORM_MONTH_FORMATTER = createFormatter("yyyy-MM")
    const val SIMPLE_MONTH_PATTERN = "yyyyMM"
    val SIMPLE_MONTH_FORMAT: FastDateFormat = FastDateFormat.getInstance("yyyyMM")
    val SIMPLE_MONTH_FORMATTER = createFormatter("yyyyMM")
    const val NORM_DATE_PATTERN = "yyyy-MM-dd"
    val NORM_DATE_FORMAT: FastDateFormat = FastDateFormat.getInstance("yyyy-MM-dd")
    val NORM_DATE_FORMATTER = createFormatter("yyyy-MM-dd")
    const val NORM_TIME_PATTERN = "HH:mm:ss"
    val NORM_TIME_FORMAT: FastDateFormat = FastDateFormat.getInstance("HH:mm:ss")
    val NORM_TIME_FORMATTER = createFormatter("HH:mm:ss")
    const val NORM_DATETIME_MINUTE_PATTERN = "yyyy-MM-dd HH:mm"
    val NORM_DATETIME_MINUTE_FORMAT: FastDateFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm")
    val NORM_DATETIME_MINUTE_FORMATTER = createFormatter("yyyy-MM-dd HH:mm")
    const val NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss"
    val NORM_DATETIME_FORMAT: FastDateFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss")
    val NORM_DATETIME_FORMATTER = createFormatter("yyyy-MM-dd HH:mm:ss")
    const val NORM_DATETIME_MS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS"
    val NORM_DATETIME_MS_FORMAT: FastDateFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS")
    val NORM_DATETIME_MS_FORMATTER = createFormatter("yyyy-MM-dd HH:mm:ss.SSS")
    const val ISO8601_PATTERN = "yyyy-MM-dd HH:mm:ss,SSS"
    val ISO8601_FORMAT: FastDateFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss,SSS")
    val ISO8601_FORMATTER = createFormatter("yyyy-MM-dd HH:mm:ss,SSS")
    const val CHINESE_DATE_PATTERN = "yyyy年MM月dd日"
    val CHINESE_DATE_FORMAT: FastDateFormat = FastDateFormat.getInstance("yyyy年MM月dd日")
    val CHINESE_DATE_FORMATTER = createFormatter("yyyy年MM月dd日")
    const val CHINESE_DATE_TIME_PATTERN = "yyyy年MM月dd日HH时mm分ss秒"
    val CHINESE_DATE_TIME_FORMAT: FastDateFormat = FastDateFormat.getInstance("yyyy年MM月dd日HH时mm分ss秒")
    val CHINESE_DATE_TIME_FORMATTER = createFormatter("yyyy年MM月dd日HH时mm分ss秒")
    const val PURE_DATE_PATTERN = "yyyyMMdd"
    val PURE_DATE_FORMAT: FastDateFormat = FastDateFormat.getInstance("yyyyMMdd")
    val PURE_DATE_FORMATTER = createFormatter("yyyyMMdd")
    const val PURE_TIME_PATTERN = "HHmmss"
    val PURE_TIME_FORMAT: FastDateFormat = FastDateFormat.getInstance("HHmmss")
    val PURE_TIME_FORMATTER = createFormatter("HHmmss")
    const val PURE_DATETIME_PATTERN = "yyyyMMddHHmmss"
    val PURE_DATETIME_FORMAT: FastDateFormat = FastDateFormat.getInstance("yyyyMMddHHmmss")
    val PURE_DATETIME_FORMATTER = createFormatter("yyyyMMddHHmmss")
    const val PURE_DATETIME_MS_PATTERN = "yyyyMMddHHmmssSSS"
    val PURE_DATETIME_MS_FORMAT: FastDateFormat = FastDateFormat.getInstance("yyyyMMddHHmmssSSS")
    val PURE_DATETIME_MS_FORMATTER = createFormatter("yyyyMMddHHmmssSSS")
    const val HTTP_DATETIME_PATTERN = "EEE, dd MMM yyyy HH:mm:ss z"
    const val JDK_DATETIME_PATTERN = "EEE MMM dd HH:mm:ss zzz yyyy"
    const val UTC_SIMPLE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss"
    const val UTC_SIMPLE_MS_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS"
    const val UTC_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    const val UTC_WITH_ZONE_OFFSET_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ"
    const val UTC_WITH_XXX_OFFSET_PATTERN = "yyyy-MM-dd'T'HH:mm:ssXXX"
    const val UTC_MS_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val UTC_MS_WITH_ZONE_OFFSET_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    const val UTC_MS_WITH_XXX_OFFSET_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"

    private fun createFormatter(pattern: String?): DateTimeFormatter {
        return DateTimeFormatter.ofPattern(pattern, Locale.getDefault()).withZone(ZoneId.systemDefault())
    }


}
