package com.shdatalink.kotlin.modules.system.model.entity

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableName
import com.shdatalink.kotlin.common.core.base.Entity

/**
 * 字典
 */
@TableName(value = "sys_dict")
class SysDict: Entity() {

    /** 名称 */
    @TableField(value = "name")
    var name: String? = null

    /** 编码 */
    @TableField(value = "code")
    var code: String? = null

    /** 描述 */
    @TableField(value = "describe_")
    var describe: String? = null

    /** 排序 */
    @TableField(value = "sort_value")
    var sortValue: Int? = null
}
