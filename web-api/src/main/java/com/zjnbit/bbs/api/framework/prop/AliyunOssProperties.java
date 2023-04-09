package com.zjnbit.bbs.api.framework.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 陈俊羽
 * @emp chenjunyu1 211100011
 * @date 2023/4/9 17:18
 * @Description
 **/
@Data
@ConfigurationProperties(prefix = "bbs.aliyun.oss")
public class AliyunOssProperties {
    private String bucket;
    private String endpoint;
    private String cdnUrl;
    private String callbackUrl;
}
