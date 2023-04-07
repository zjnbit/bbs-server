package com.zjnbit.bbs.api.model.param;

import lombok.Data;

@Data
public class BbsTopicReplyParam {
    private Long topicId;
    private Long parentReplyId;
    private Long quoteReplyId;
    private String replyContent;
}
