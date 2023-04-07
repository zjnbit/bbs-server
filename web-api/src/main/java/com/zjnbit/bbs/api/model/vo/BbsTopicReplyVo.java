package com.zjnbit.bbs.api.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BbsTopicReplyVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long topicId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentReplyId;
    private String parentReplyContent;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentReplyPostUserId;
    private String parentReplyPostUserNickname;
    private LocalDateTime parentReplyPostTime;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long quoteReplyId;
    private String quoteReplyContent;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long quoteReplyPostUserId;
    private String quoteReplyPostUserNickname;
    private LocalDateTime quoteReplyPostTime;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long postUserId;
    private String postUserNickname;
    private LocalDateTime postTime;
    private Integer likeCount;
    private String replyContent;

}
