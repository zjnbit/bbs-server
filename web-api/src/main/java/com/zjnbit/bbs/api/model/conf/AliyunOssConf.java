package com.zjnbit.bbs.api.model.conf;

import lombok.Data;

@Data
public class AliyunOssConf {
    private String bucket;
    private String endpoint;
    private String bucketUrl;
    private String cdnUrl;
    private String callbackUrl;
}
