package com.zjnbit.bbs.api.model.param;

import lombok.Data;

@Data
public class UserOauthLoginParam {
    private String loginType;
    private String account;
    private String secret;

    private String codeKey;
    private String code;
}
