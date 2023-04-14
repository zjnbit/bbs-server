package com.zjnbit.bbs.api.framework.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "bbs.aliyun.oss")
public class AliyunOssProperties {
    private String bucket;
    private String endpoint;
    private String cdnUrl;
    private String callbackUrl;
}
