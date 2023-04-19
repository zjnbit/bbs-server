package com.zjnbit.bbs.api.framework.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 陈俊羽
 * @emp chenjunyu1 211100011
 * @date 2023/4/19 14:29
 * @Description
 **/
@Data
@ConfigurationProperties(prefix = "bbs.minio")
public class MinioProperties {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucket;
}
