package com.zjnbit.bbs.api.framework.config;

import com.zjnbit.bbs.api.framework.prop.MinioProperties;
import com.zjnbit.bbs.api.framework.template.MinioTemplate;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 陈俊羽
 * @emp chenjunyu1 211100011
 * @date 2023/4/19 14:28
 * @Description
 **/
@Configuration
@EnableConfigurationProperties(MinioProperties.class)
public class MinioConfiguration {
    @Autowired
    MinioProperties minioProperties;
    @Bean
    @ConditionalOnMissingBean(MinioClient.class)
    public MinioClient minioClient()  {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
        return minioClient;
    }

    @Bean
    @ConditionalOnBean(MinioClient.class)
    public MinioTemplate minioTemplate(MinioClient minioClient) {
        return new MinioTemplate(minioClient, minioProperties.getBucket());
    }
}
