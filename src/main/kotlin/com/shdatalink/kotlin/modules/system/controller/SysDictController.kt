package com.shdatalink.kotlin.modules.system.controller

import com.shdatalink.kotlin.common.core.base.PageParam
import com.shdatalink.kotlin.common.core.base.result.PageResult
import com.shdatalink.kotlin.modules.system.model.entity.SysDict
import com.shdatalink.kotlin.modules.system.model.param.DictQueryParam
import com.shdatalink.kotlin.modules.system.service.ISysDictService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 字典 前端控制器
 */
@RestController
@RequestMapping("/system/dict")
class SysDictController(
    private val sysUserService: ISysDictService
) {


    /**
     * 分页查询
     */
    @PostMapping("/page")
    fun page(@RequestBody param: PageParam<DictQueryParam>): PageResult<SysDict> {
        return sysUserService.pageQuery(param)
    }


}
