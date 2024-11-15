package com.shdatalink.kotlin.common.core.exception

import com.shdatalink.kotlin.common.core.base.result.ResultVM
import com.shdatalink.kotlin.common.core.exception.enums.ErrorCodeEnum
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import java.util.*

/**
 * 全局异常处理器
 */
@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger = LoggerFactory.getLogger(this::class.java)
    /**
     * 业务异常处理
     *
     * @param ex BizException
     * @return ResultVM<*>
     */
    @ExceptionHandler(BizException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun BizExceptionHandler(ex: BizException): ResultVM<*> {
        logger.warn("抛出业务异常：${ex}")
        return ResultVM.result(ex.code, ex.message, null)
    }

    /**
     * 参数异常处理
     *
     * @param ex ArgException
     * @return ResultVM<*>
     */
    @ExceptionHandler(ArgException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun ArgExceptionHandler(ex: ArgException): ResultVM<*> {
        logger.warn("抛出参数异常：", ex)
        return ResultVM.result(ex.code, ex.message, null)
    }

    /**
     * 绑定异常
     *
     * @param ex BindException
     * @return ResultVM<*>
     */
    @ExceptionHandler(BindException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun bindExceptionHandler(ex: BindException): ResultVM<*> {
        logger.warn("抛出绑定异常：", ex)
        try {
            val msg = Objects.requireNonNull(ex.bindingResult.fieldError)!!.defaultMessage
            if (StringUtils.isNotEmpty(msg)) {
                return ResultVM.result(ErrorCodeEnum.ERR_BIND_EXCEPTION.code, msg, ex.message)
            }
        } catch (e: Exception) {
            logger.warn("获取异常描述失败", e)
        }

        val msg = StringBuilder()
        ex.fieldErrors.forEach {
            msg.append("参数:[${it.objectName}.${it.field}]的传入值:[${it.rejectedValue}]与预期的字段类型不匹配.")
        }
        return ResultVM.result(ErrorCodeEnum.ERR_BIND_EXCEPTION.code, msg.toString(), ex.message)
    }

    /**
     * 方法参数类型不匹配异常
     *
     * @param ex MethodArgumentTypeMismatchException
     * @return ResultVM<*>?
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun methodArgumentTypeMismatchException(ex: MethodArgumentTypeMismatchException): ResultVM<*>? {
        logger.warn("抛出方法参数类型不匹配异常：", ex)
        val msg = "参数：[${ex.name}]的传入值：[${ex.value}]与预期的字段类型：[${Objects.requireNonNull(ex.requiredType)!!.name}]不匹配"
        return ResultVM.result(ErrorCodeEnum.ERR_ARGUMENT_TYPE_MISMATCH_EXCEPTION.code, msg, ex.message)
    }

    /**
     * 从请求中读取数据失败异常
     *
     * @param ex HttpMessageNotReadableException
     * @return ResultVM<*>?
     */
    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun httpMessageNotReadableException(ex: HttpMessageNotReadableException): ResultVM<*>? {
        logger.warn("抛出从请求中读取数据失败异常：", ex)
        var msg = ex.message
        if (StringUtils.containsAny(msg, "Could not read document:")) {
            msg = "无法正确的解析json类型的参数：${msg}"
        }

        // Controller接口参数为空
        if (StringUtils.containsAny(msg, "Required request body is missing")) {
            msg = "请求参数为空"
        }

        // 接口参数枚举值不正确
        if (StringUtils.containsAny(msg, "not one of the values accepted for Enum class")) {
            msg = "枚举参数值不正确"
        }
        return ResultVM.result(ErrorCodeEnum.ERR_REQUEST_PARAM_EXCEPTION.code, msg, ex.message)
    }




    /**
     * 其它异常
     * @param ex Exception
     * @return ResultVM<*>
     */
    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun otherExceptionHandler(ex: Exception): ResultVM<*> {
        logger.warn("抛出其它异常：", ex)
        return ResultVM.result(ErrorCodeEnum.SYSTEM_BUSY.code, ex.message, null)
    }

}
