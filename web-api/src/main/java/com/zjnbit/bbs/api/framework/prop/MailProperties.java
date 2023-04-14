package com.zjnbit.bbs.api.framework.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "bbs.mail")
public class MailProperties {
    private String host;
    private Integer port;
    private String user;
    private String pass;
    private String from;
    private Boolean sslEnable;
}
