package com.zjnbit.bbs.api.framework.config;

import com.zjnbit.framework.web.constant.AppConst;
import com.zjnbit.framework.web.model.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class InterceptorResponse implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        ServletServerHttpResponse responseTemp = (ServletServerHttpResponse) response;
        HttpServletResponse resp = responseTemp.getServletResponse();
        ServletServerHttpRequest sshr = (ServletServerHttpRequest) request;
        HttpServletRequest requestTemp = sshr.getServletRequest();
        if (body instanceof Result) {
            Result result = (Result) body;
            requestTemp.setAttribute(AppConst.REQUEST_RESULT, result);
        }
        return body;
    }
}
