package com.zjnbit.framework.web.util;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class UserUtils {

    public static Long getCurrentUserId() {
        RequestAttributes requestAttributes = null;
        try {
            requestAttributes = RequestContextHolder.currentRequestAttributes();
        } catch (IllegalStateException e) {
            log.info("未获取到 userId");
        }
        if (null != requestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            Object userIdObj = request.getAttribute("userId");
            if (null != userIdObj && StrUtil.isNotBlank(userIdObj.toString())) {
                return Long.parseLong(userIdObj.toString());
            }
        }
        return 0L;
    }
}
