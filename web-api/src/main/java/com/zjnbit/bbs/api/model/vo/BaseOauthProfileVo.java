package com.zjnbit.bbs.api.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zjnbit.bbs.api.framework.annotation.DictName;
import lombok.Data;

@Data
public class BaseOauthProfileVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String mobile;
    private String email;
    private String username;
    private String nickname;
    @DictName(name = "userbase:sex")
    private String gender;
    private String avatar;
    private Integer score;
    private String status;
    private String level;
}
