package com.shdatalink.kotlin.common.core.mybatisplus

import com.baomidou.mybatisplus.annotation.DbType
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * mybatis plus 配置类
 *
 * @author huyulong
 */
@Configuration
class MybatisPlusConfiguration {

    /**
     * mybatis插件配置
     * @return MybatisPlusInterceptor
     */
    @Bean
    fun mybatisPlusInterceptor(): MybatisPlusInterceptor {
        val interceptor = MybatisPlusInterceptor()
        // 分页插件配置
        val paginationInnerInterceptor = PaginationInnerInterceptor()
        /** 数据库类型 */
        paginationInnerInterceptor.dbType = DbType.MYSQL
        /** 是否生成countSql优化掉left join */
        paginationInnerInterceptor.isOptimizeJoin = true
        interceptor.addInnerInterceptor(paginationInnerInterceptor)

        return interceptor
    }

}
