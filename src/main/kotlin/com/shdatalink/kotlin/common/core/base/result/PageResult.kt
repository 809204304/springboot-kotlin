package com.shdatalink.kotlin.common.core.base.result

/**
 * 分页查询返回结果
 */
class PageResult<T> private constructor() {

    /**
     * 数据集合
     */
    var records: List<T>? = mutableListOf()

    /**
     * 总记录数
     */
    var total: Long? = null

    constructor(records: List<T>, total: Long = 0) : this() {
        this.records = records
        this.total = total
    }


}
