package com.zjnbit.bbs.api.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class BaseOauthSigninVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    private String nickname;
    private String token;
    private String tokenKey;
}
