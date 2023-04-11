package com.zjnbit.bbs.api.model.dto;

import lombok.Data;

@Data
public class BaseAliyunOssDto {
    private String attachPath;
    private String ossUrl;
    private String cdnUrl;
    private String mimeType;
    private Long size;
}
