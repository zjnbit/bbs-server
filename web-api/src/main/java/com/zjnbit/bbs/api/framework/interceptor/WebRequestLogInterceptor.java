package com.zjnbit.bbs.api.framework.interceptor;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.zjnbit.bbs.api.model.entity.LogRequestEntity;
import com.zjnbit.bbs.api.service.BaseRabbitMqService;
import com.zjnbit.framework.tools.util.GetRequestJsonUtil;
import com.zjnbit.framework.tools.util.IpUtils;
import com.zjnbit.framework.web.constant.AppConst;
import com.zjnbit.framework.web.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class WebRequestLogInterceptor implements HandlerInterceptor {
    @Autowired
    BaseRabbitMqService baseRabbitMqService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute(AppConst.REQUST_LOG_START_TIME, System.currentTimeMillis());
        request.setAttribute(AppConst.REQUST_ID, IdUtil.fastUUID());
        if (!request.getMethod().equals("PUT")) {
            MyHttpServletRequestWrapper myWrapper = new MyHttpServletRequestWrapper(request);
            String jsonBody = GetRequestJsonUtil.getRequestJsonString(myWrapper);
            if (JSONUtil.isTypeJSON(jsonBody)) {
                JSONObject jsonObject = JSONUtil.parseObj(jsonBody);
                jsonBody = JSONUtil.toJsonStr(jsonObject);
            }
            request.setAttribute(AppConst.REQUEST_BODY, jsonBody);
        } else {
            request.setAttribute(AppConst.REQUEST_BODY, "");
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long startTime = Long.parseLong(request.getAttribute(AppConst.REQUST_LOG_START_TIME).toString());
        Long endTime = System.currentTimeMillis();
        LogRequestEntity logEntity = new LogRequestEntity();
        logEntity.setRequestId(request.getAttribute(AppConst.REQUST_ID).toString());
        logEntity.setUserId(StpUtil.getLoginId(0L));
        logEntity.setUri(request.getRequestURI());
        logEntity.setMethod(request.getMethod());
        logEntity.setUa(CharSequenceUtil.isNotBlank(request.getHeader("User-Agent")) ? request.getHeader("User-Agent") : "");
        logEntity.setPathParam(request.getQueryString() != null ? request.getQueryString() : "");
        logEntity.setBodyParam(CharSequenceUtil.isNotBlank((CharSequence) request.getAttribute(AppConst.REQUEST_BODY)) ? request.getAttribute(AppConst.REQUEST_BODY).toString() : "");
        logEntity.setIp(NetUtil.ipv4ToLong(IpUtils.getIpAddress(request)));
        logEntity.setBeginTime(LocalDateTimeUtil.of(startTime));
        logEntity.setEndTime(LocalDateTimeUtil.of(endTime));
        logEntity.setConsumedTime((int) (endTime - startTime));
        Object resultObj = request.getAttribute(AppConst.REQUEST_RESULT);
        if (resultObj != null && resultObj instanceof Result) {
            Result result = (Result) resultObj;
            logEntity.setResponseCode(result.getErrCode());
            logEntity.setResponseMsg(result.getErrMsg());
            logEntity.setResponseData(JSON.toJSONString(result.getData()));
        } else {
            logEntity.setResponseCode("");
            logEntity.setResponseMsg("");
            logEntity.setResponseData("");
        }
        baseRabbitMqService.sendLogRequestMsg(JSON.toJSONString(logEntity));
        log.info("api-访问日志记录，请求ID：" + logEntity.getRequestId() + "内容：" + JSON.toJSONString(logEntity));
    }

}
