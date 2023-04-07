package com.zjnbit.bbs.api.service;

import com.zjnbit.bbs.api.model.dto.BbsTopicReplyAddDto;

public interface BaseRabbitMqService {

    void sendTopicReplyAdd(BbsTopicReplyAddDto data);

    void sendTopicLikeRefresh(Long topicId);

    void sendLogRequestMsg(String msg);
}
