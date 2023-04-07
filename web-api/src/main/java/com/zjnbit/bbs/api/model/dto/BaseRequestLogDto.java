package com.zjnbit.bbs.api.model.dto;

import lombok.Data;

@Data
public class BaseRequestLogDto {
    private String requestId;
    private Long userId;
    private String uri;
    private String method;
    private String ua;
    private String pathParam;
    private String bodyParam;
    private String ip;
    private String beginTime;
    private String endTime;
    private Integer consumedTime;
    private String responseCode;
    private String responseMsg;
    private String responseData;
    private String createTime;
}
