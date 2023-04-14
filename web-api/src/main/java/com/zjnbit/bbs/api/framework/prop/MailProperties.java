package com.zjnbit.bbs.api.framework.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 陈俊羽
 * @emp chenjunyu1 211100011
 * @date 2023/4/14 08:44
 * @Description
 **/
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
