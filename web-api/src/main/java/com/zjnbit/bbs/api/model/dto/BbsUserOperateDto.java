package com.zjnbit.bbs.api.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BbsUserOperateDto {
    private Long userId;
    private String nickname;
    private String ip;
    private LocalDateTime time;
}
