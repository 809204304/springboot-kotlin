package com.shdatalink.kotlin.modules.system.service

import com.baomidou.mybatisplus.extension.service.IService
import com.shdatalink.kotlin.common.core.base.PageParam
import com.shdatalink.kotlin.common.core.base.result.PageResult
import com.shdatalink.kotlin.modules.system.model.entity.SysDict
import com.shdatalink.kotlin.modules.system.model.param.DictQueryParam

/**
 * 字典 服务类
 */
interface ISysDictService : IService<SysDict> {

    /**
     * 分页查询
     */
    fun pageQuery(param: PageParam<DictQueryParam>): PageResult<SysDict>

}
