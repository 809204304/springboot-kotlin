package com.shdatalink.kotlin.modules.system.service.impl

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.shdatalink.kotlin.common.core.base.PageParam
import com.shdatalink.kotlin.common.core.base.result.PageResult
import com.shdatalink.kotlin.modules.system.mapper.SysDictMapper
import com.shdatalink.kotlin.modules.system.model.entity.SysDict
import com.shdatalink.kotlin.modules.system.model.param.DictQueryParam
import com.shdatalink.kotlin.modules.system.service.ISysDictService
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
 * 字典 服务实现类
 */
@Service
class SysDictServiceImpl : ISysDictService, ServiceImpl<SysDictMapper, SysDict>() {

    private val logger = LoggerFactory.getLogger(this::class.java)


    override fun pageQuery(param: PageParam<DictQueryParam>): PageResult<SysDict> {
        // 处理查询参数
        val queryParam = param.queryParam ?: DictQueryParam();

        // 构造分页查询条件
        val queryWrapper = KtQueryWrapper(SysDict())
            .like(StringUtils.isNoneBlank(queryParam.name), SysDict::name, queryParam.name)
            .eq(StringUtils.isNoneBlank(queryParam.code), SysDict::code, queryParam.code)

        // 构建分页对象
        var page: IPage<SysDict> = param.buildPage()

        // 执行单表分页查询
        page = page(page, queryWrapper)

        // 处理查询后的分页结果
        return PageResult(page.records, page.total)
    }

}
