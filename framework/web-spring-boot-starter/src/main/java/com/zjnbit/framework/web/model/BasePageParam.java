package com.zjnbit.framework.web.model;

import lombok.Data;

@Data
public class BasePageParam {
    private long current = 1;
    private long size = 10;
}
