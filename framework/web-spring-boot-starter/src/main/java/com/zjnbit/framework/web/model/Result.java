package com.zjnbit.framework.web.model;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import com.zjnbit.framework.web.constant.AppConst;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Result<T> {
    private String errCode;
    private String errMsg;
    private T data;
    private LocalDateTime time = LocalDateTime.now();
    private String requestId;

    public Result() {
        if (StrUtil.isBlank(this.requestId)) {
            this.requestId = getCurrentRequestId();
        }
    }

    public Result(String errCode, String errMsg, T data) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.data = data;
        if (StrUtil.isBlank(this.requestId)) {
            this.requestId = getCurrentRequestId();
        }
    }

    public static <T> Result<T> success() {
        return success("00000", "操作成功", null);
    }

    public static <T> Result<T> success(T data) {
        return success("00000", "操作成功", data);
    }

    public static <T> Result<T> success(String errMsg, T data) {
        return success("00000", errMsg, data);
    }

    public static <T> Result<T> success(String errCode, String errMsg, T data) {
        return new Result<>(errCode, errMsg, data);
    }

    private String getCurrentRequestId() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null == attributes) {
            return null;
        }
        HttpServletRequest request = attributes.getRequest();
        String requestId = request.getAttribute(AppConst.REQUST_ID).toString();
        return CharSequenceUtil.isBlank(requestId) ? null : requestId;
    }

}