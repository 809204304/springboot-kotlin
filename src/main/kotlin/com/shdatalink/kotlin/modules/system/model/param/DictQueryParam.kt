package com.shdatalink.kotlin.modules.system.model.param

/**
 * 字典查询参数
 */
data class DictQueryParam(

    /**
     * 字典名称（模糊搜索）
     */
    var name: String? = null,

    /**
     * 字典类型（精确搜索）
     */
    var code: String? = null,
)
