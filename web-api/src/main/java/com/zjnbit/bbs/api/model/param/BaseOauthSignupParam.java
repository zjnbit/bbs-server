package com.zjnbit.bbs.api.model.param;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class BaseOauthSignupParam {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^([a-f\\d]{32}|[A-F\\d]{32})$", message = "错误的密码格式，请输入32位MD5加密后的密码")
    private String password;
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    @NotBlank(message = "验证码不能为空")
    private String code;
    @NotBlank(message = "昵称不能为空")
    private String nickname;
}
