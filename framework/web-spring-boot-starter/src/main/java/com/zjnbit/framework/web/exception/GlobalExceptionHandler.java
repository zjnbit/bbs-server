package com.zjnbit.framework.web.exception;

import com.zjnbit.framework.web.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({RequestException.class})
    @ResponseStatus(HttpStatus.OK)
    public Result requestException(RequestException e) {
        return new Result(e.getCode(), e.getMessage(), null);
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.OK)
    public Result runtimeException(RuntimeException e) {
        log.error(e.getMessage(), e.getStackTrace());
        log.error(e.getMessage(), e.fillInStackTrace());
        log.error(e.getMessage(), e);
        return new Result(RequestError.B0500.getErrCode(), RequestError.B0500.getErrMsg(), e.getMessage());
    }

    @ExceptionHandler({NullPointerException.class})
    @ResponseStatus(HttpStatus.OK)
    public Result nullPointerException(NullPointerException e) {
        log.error(e.getMessage(), e.getStackTrace());
        log.error(e.getMessage(), e.fillInStackTrace());
        log.error(e.getMessage(), e);
        return new Result(RequestError.B0500.getErrCode(), RequestError.B0500.getErrMsg(), e.getMessage());
    }

}
