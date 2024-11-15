package com.shdatalink.kotlin.modules.system.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.shdatalink.kotlin.modules.system.model.entity.SysDict
import org.apache.ibatis.annotations.Mapper

/**
 * 字典 Mapper 接口
 */
@Mapper
interface SysDictMapper: BaseMapper<SysDict> {

}
