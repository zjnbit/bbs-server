package com.zjnbit.framework.web.exception;


import com.zjnbit.framework.web.model.BaseError;

public class RequestException extends RuntimeException {
    private String code;
    private String message;

    public RequestException(BaseError errorEnum) {
        this.code = errorEnum.getErrCode();
        this.message = errorEnum.getErrMsg();
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
