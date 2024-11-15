package com.shdatalink.kotlin.common.core.web

import com.shdatalink.kotlin.common.core.base.result.ResultVM
import org.springframework.core.MethodParameter
import org.springframework.core.io.InputStreamResource
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice

@RestControllerAdvice
class ResponseAdvisor : ResponseBodyAdvice<Any?> {

    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        return true
    }

    override fun beforeBodyWrite(body: Any?, returnType: MethodParameter, selectedContentType: MediaType, selectedConverterType: Class<out HttpMessageConverter<*>>, request: ServerHttpRequest, response: ServerHttpResponse): Any? {
        return body as? ResultVM<*> ?: (body as? InputStreamResource ?: (body as? ByteArray ?: ResultVM.success(body)))
    }

}
