package com.zjnbit.bbs.api.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseOauthSigninTypeEnum {

    EMAIL("email", "邮箱"),
    USERNAME("username", "用户名"),
    ;

    private String code;
    private String desc;

}
