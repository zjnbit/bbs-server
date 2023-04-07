package com.zjnbit.bbs.api.model.param;

import lombok.Data;

@Data
public class BbsTopicPostParam {
    private Long nodeId;
    private Long topicId;
    private String topicTitle;
    private String topicContent;
}
