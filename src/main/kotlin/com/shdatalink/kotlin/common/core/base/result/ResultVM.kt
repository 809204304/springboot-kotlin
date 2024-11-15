package com.shdatalink.kotlin.common.core.base.result

import com.shdatalink.kotlin.common.core.exception.enums.ErrorCodeEnum


/**
 * 返回结果
 */
class ResultVM<T> {

    /** 状态码 */
    var code: Int? = null

    /** 是否成功 */
    val success: Boolean
        get() = code == ErrorCodeEnum.SUCCESS.code

    /** 状态信息 */
    var message: String? = null

    /** 返回数据 */
    var data: T? = null

    /** 错误信息 */
    var error: String? = null


    constructor(code: Int?) : this(code, null)

    constructor(code: Int? = ErrorCodeEnum.SUCCESS.code, message: String? = null, data: T? = null, error: String? = null) {
        this.code = code
        this.message = message
        this.data = data
        this.error = error
    }

    fun setMessage(message: String?): ResultVM<T> {
        this.message = message
        return this
    }

    fun setError(error: String?): ResultVM<T> {
        this.error = error
        return this
    }

    fun setData(data: T?): ResultVM<T> {
        this.data = data
        return this
    }

    companion object {
        /**
         * 返回结果
         *
         * @param code Int
         * @param message String
         * @return ApiResult<E>
         */
        fun <E> result(code: Int?, message: String?, data: E? = null): ResultVM<E> {
            return ResultVM(code, message, data)
        }

        /**
         * 请求成功
         * @param data E?
         * @return ApiResult<E>
         */
        fun <E> success(data: E? = null): ResultVM<E> {
            return ResultVM(ErrorCodeEnum.SUCCESS.code, ErrorCodeEnum.SUCCESS.msg, data)
        }

        /**
         * 请求失败
         * @param code Int?
         * @param message String?
         * @param data E?
         * @return ApiResult<E>
         */
        fun <E> fail(code: Int? = ErrorCodeEnum.FAIL.code, message: String? = ErrorCodeEnum.FAIL.msg, data: E? = null): ResultVM<E> {
            return ResultVM(code, message, data)
        }
    }
}
