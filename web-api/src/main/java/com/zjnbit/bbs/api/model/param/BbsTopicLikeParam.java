package com.zjnbit.bbs.api.model.param;

import lombok.Data;

@Data
public class BbsTopicLikeParam {
    private Long topicId;
    private Boolean isLike;
}
