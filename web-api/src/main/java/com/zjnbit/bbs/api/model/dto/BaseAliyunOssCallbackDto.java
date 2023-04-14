package com.zjnbit.bbs.api.model.dto;

import lombok.Data;

@Data
public class BaseAliyunOssCallbackDto {
    private String filename;
    private String mimeType;
    private Long size;
}
