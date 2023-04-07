package com.zjnbit.bbs.api.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class BbsUserInfoVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String nickname;
    private String avatar;
    private String level;
}
