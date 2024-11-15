package com.shdatalink.kotlin.common.core.exception

import com.shdatalink.kotlin.common.core.exception.enums.ErrorCodeEnum


/**
 * 自定义异常 - 业务异常
 */
class BizException : RuntimeException {

    var code: Int? = null

    constructor() : this(ErrorCodeEnum.ERR_BUSINESS.msg)

    constructor(message: String) : this(ErrorCodeEnum.ERR_BUSINESS.code, message)

    constructor(code: Int, message: String) : super(message) {
        this.code = code
    }

    constructor(message: String, cause: Throwable) : this(ErrorCodeEnum.ERR_BUSINESS.code, message, cause)

    constructor(code: Int, message: String, cause: Throwable) : super(message, cause) {
        this.code = code
    }
}
