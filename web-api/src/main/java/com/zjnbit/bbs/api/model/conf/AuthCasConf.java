package com.zjnbit.bbs.api.model.conf;

import lombok.Data;

@Data
public class AuthCasConf {
    private String clientId;
    private String clientSecret;
    private String serverUrl;
}
