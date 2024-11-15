package com.shdatalink.kotlin.common.core.base

import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import java.io.Serializable
import java.time.LocalDateTime

/**
 * 基础实体
 * 包括id、create_time、create_by字段的表继承的基础实体
 */
abstract class SuperEntity(
        /** id */
        @TableId(value = FIELD_ID, type = IdType.INPUT)
        open var id: Long? = null,

        /** 创建时间 */
        @TableField(value = CREATE_TIME_COLUMN, fill = FieldFill.INSERT)
        open var createTime: LocalDateTime? = null,

        /** 创建人ID */
        @TableField(value = CREATED_BY_COLUMN, fill = FieldFill.INSERT)
        open var createdBy: String? = null,
) : Serializable {

    companion object {
        const val FIELD_ID = "id"
        const val CREATE_TIME = "createTime"
        const val CREATE_TIME_COLUMN = "create_time"
        const val CREATED_BY = "createdBy"
        const val CREATED_BY_COLUMN = "created_by"
    }

}
