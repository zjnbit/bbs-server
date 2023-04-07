package com.zjnbit.framework.tools.util;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class IpUtils {

    public static String getIpAddress() {
        String ip = "0.0.0.0";
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (null != requestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            return getIpAddress(request);
        } else {
            return "0.0.0.0";
        }
    }

    public static String getIpAddress(HttpServletRequest request) {
        String xip = request.getHeader("X-Real-IP");
        String xFor = request.getHeader("X-Forwarded-For");
        if (StrUtil.isNotEmpty(xFor) && !"unKnown".equalsIgnoreCase(xFor)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = xFor.indexOf(",");
            if (index != -1) {
                return xFor.substring(0, index);
            } else {
                return xFor;
            }
        }
        xFor = xip;
        if (StrUtil.isNotEmpty(xFor) && !"unKnown".equalsIgnoreCase(xFor)) {
            return xFor;
        }
        if (StrUtil.isBlank(xFor) || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("Proxy-Client-IP");
        }
        if (StrUtil.isBlank(xFor) || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StrUtil.isBlank(xFor) || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StrUtil.isBlank(xFor) || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StrUtil.isBlank(xFor) || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getRemoteAddr();
        }
        if (!Validator.isIpv4(xFor)) {
            xFor = "0.0.0.0";
        }
        return xFor;
    }

}
