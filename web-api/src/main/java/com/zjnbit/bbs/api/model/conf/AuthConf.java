package com.zjnbit.bbs.api.model.conf;

import lombok.Data;

@Data
public class AuthConf {
    private AuthCasConf cas;

    private AuthSecretConf secret;

}
