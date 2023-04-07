package com.zjnbit.framework.web.api;

import com.zjnbit.framework.web.model.Result;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseApi<S> {
    @Autowired
    protected S baseService;

    public BaseApi() {
    }

    public S getBaseService() {
        return this.baseService;
    }

    public <T> Result<T> success(T object) {
        return Result.success(object);
    }

    public <T> Result<T> success() {
        return Result.success();
    }
}