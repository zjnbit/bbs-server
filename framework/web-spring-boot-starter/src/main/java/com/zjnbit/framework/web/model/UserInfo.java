package com.zjnbit.framework.web.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class UserInfo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    private String userType;
    private String nickname;
    private String gender;
    private String avatar;
}
