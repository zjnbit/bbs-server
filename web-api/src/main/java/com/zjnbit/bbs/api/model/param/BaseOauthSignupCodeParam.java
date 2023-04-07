package com.zjnbit.bbs.api.model.param;

import lombok.Data;

@Data
public class BaseOauthSignupCodeParam {
    private String type;
    private String target;

    private String code;
}
