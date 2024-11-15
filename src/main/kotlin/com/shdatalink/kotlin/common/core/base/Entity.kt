package com.shdatalink.kotlin.common.core.base

import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.annotation.TableField
import java.time.LocalDateTime

/**
 * 基础实体类
 * 包括id、create_time、create_by、update_by、update_time字段的表继承的基础实体
 *
 */
abstract class Entity(
        /** 最后修改时间 */
        @TableField(value = UPDATE_TIME_COLUMN, fill = FieldFill.INSERT_UPDATE)
        open var updateTime: LocalDateTime? = null,

        /** 最后修改人ID */
        @TableField(value = UPDATED_BY_COLUMN, fill = FieldFill.INSERT_UPDATE)
        open var updatedBy: String? = null,
) : SuperEntity() {

    companion object {
        const val UPDATE_TIME = "updateTime"
        const val UPDATE_TIME_COLUMN = "update_time"
        const val UPDATED_BY = "updatedBy"
        const val UPDATED_BY_COLUMN = "updated_by"
    }

}
