package com.shdatalink.kotlin.common.core.mybatisplus

import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler
import com.shdatalink.kotlin.common.core.base.Entity
import com.shdatalink.kotlin.common.core.base.SuperEntity
import org.apache.ibatis.reflection.MetaObject
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * 自动填充处理
 * @author gcc
 */
@Component
class MybatisPlusMetaObjectHandler : MetaObjectHandler {

    /**
     * 插入元对象字段填充（用于插入时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    override fun insertFill(metaObject: MetaObject) {
        fillCreated(metaObject)
        fillUpdated(metaObject)
    }

    /**
     * 更新元对象字段填充（用于更新时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    override fun updateFill(metaObject: MetaObject) {
        fillUpdated(metaObject)
    }


    /**
     * 填充创建时间、创建人
     *
     * 说明：
     * 字段上未设置填充方式 fill = [FieldFill].INSERT也能进行填充
     * @param metaObject MetaObject
     */
    private fun fillCreated(metaObject: MetaObject) {
        // 有createTime字段，且字段值为null
        if (metaObject.hasGetter(SuperEntity.CREATE_TIME)) {
            val value = metaObject.getValue(SuperEntity.CREATE_TIME)
            // 设置创建时间
            if (value == null) {
                setFieldValByName(SuperEntity.CREATE_TIME, LocalDateTime.now(), metaObject)
            }
        }
    }

    /**
     * 填充修改时间修改人
     *
     * 说明：
     * 字段上未设置填充方式 fill = [FieldFill].UPDATE也能进行填充
     * @param metaObject MetaObject
     */
    private fun fillUpdated(metaObject: MetaObject) {
        // 有updateTime字段，且字段值为null
        if (metaObject.hasGetter(Entity.UPDATE_TIME)) {
            val value = metaObject.getValue(Entity.UPDATE_TIME)
            if (value == null) {
                setFieldValByName(Entity.UPDATE_TIME, LocalDateTime.now(), metaObject)
            }
        }
    }


}
