package com.zjnbit.bbs.api.framework.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "bbs.aliyun.security")
public class AliyunSecurityProperties {
    private String accessKeyId;
    private String accessKeySecret;
}
