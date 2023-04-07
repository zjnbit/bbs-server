package com.zjnbit.bbs.api.model.dto;

import lombok.Data;

@Data
public class BbsTopicReplyAddDto {
    private Long topicId;
    private BbsUserOperateDto bbsUserOperateDto;

    public BbsTopicReplyAddDto() {
    }

    public BbsTopicReplyAddDto(Long topicId, BbsUserOperateDto bbsUserOperateDto) {
        this.topicId = topicId;
        this.bbsUserOperateDto = bbsUserOperateDto;
    }
}
