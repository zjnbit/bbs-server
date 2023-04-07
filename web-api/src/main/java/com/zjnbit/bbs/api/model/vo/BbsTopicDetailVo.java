package com.zjnbit.bbs.api.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BbsTopicDetailVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String topicTitle;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long postUser;
    private String postUserNickname;
    private LocalDateTime postTime;
    private Integer replyCount;
    private Integer likeCount;
    private Integer favoriteCount;
    private String content;
    private Boolean isContentModified;
}
