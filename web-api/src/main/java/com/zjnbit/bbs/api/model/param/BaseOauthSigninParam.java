package com.zjnbit.bbs.api.model.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BaseOauthSigninParam {
    @NotBlank(message = "登录类型不能为空")
    private String signinType;
    @NotBlank(message = "登录名不能为空")
    private String account;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "验证码不能为空")
    private String code;

}
