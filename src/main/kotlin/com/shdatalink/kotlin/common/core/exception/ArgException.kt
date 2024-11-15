package com.shdatalink.kotlin.common.core.exception

import com.shdatalink.kotlin.common.core.exception.enums.ErrorCodeEnum

/**
 * 自定义异常 - 参数异常
 *
 * @author gcc
 */
class ArgException: RuntimeException {

    var code: Int? = null

    constructor(): this(ErrorCodeEnum.ERR_ARGUMENT.msg)

    constructor(message: String): this(ErrorCodeEnum.ERR_ARGUMENT.code, message)

    constructor(code: Int, message: String) : super(message) {
        this.code = code
    }

    constructor(message: String, cause: Throwable) : this(ErrorCodeEnum.ERR_ARGUMENT.code, message, cause)

    constructor(code: Int, message: String, cause: Throwable) : super(message, cause) {
        this.code = code
    }
}
