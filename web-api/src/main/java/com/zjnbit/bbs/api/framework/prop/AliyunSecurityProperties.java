package com.zjnbit.bbs.api.framework.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 陈俊羽
 * @emp chenjunyu1 211100011
 * @date 2023/4/9 17:15
 * @Description
 **/
@Data
@ConfigurationProperties(prefix = "bbs.aliyun.security")
public class AliyunSecurityProperties {
    private String accessKeyId;
    private String accessKeySecret;
}
