package com.shdatalink.kotlin.common.core.base

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import jakarta.validation.Valid

/**
 * 分页查询参数
 */
class PageParam<T> private constructor() {
    /** 当前页 */
    var pageNum: Long = 1

    /** 每页显示条数 */
    var pageSize: Long = 10

    /** 查询条件 */
    @Valid
    var queryParam: T? = null


    constructor(pageNum: Long, pageSize: Long) : this() {
        this.pageNum = pageNum
        this.pageSize = pageSize
    }

    constructor(pageNum: Long, pageSize: Long, queryParam: T? = null) : this(pageNum, pageSize) {
        this.queryParam = queryParam
    }


    /**
     * 构建分页对象
     * @return IPage<E>
     */
    fun <E> buildPage(): IPage<E> {
        val page: Page<E> = Page<E>(this.pageNum, this.pageSize)
        return page
    }

}
