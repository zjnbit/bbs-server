package com.zjnbit.bbs.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.zjnbit.bbs.api.framework.constant.MqConst;
import com.zjnbit.bbs.api.model.dto.BbsTopicReplyAddDto;
import com.zjnbit.bbs.api.service.BaseRabbitMqService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseRabbitMqServiceImpl implements BaseRabbitMqService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void sendTopicReplyAdd(BbsTopicReplyAddDto data) {
        rabbitTemplate.convertAndSend(MqConst.EXCHANGE_TOPIC, MqConst.QUEUE_TOPIC_REPLY_ADD, JSON.toJSONString(data));
    }

    @Override
    public void sendTopicLikeRefresh(Long topicId) {
        rabbitTemplate.convertAndSend(MqConst.EXCHANGE_TOPIC, MqConst.QUEUE_TOPIC_LIKE_REFRESH, topicId);
    }

    @Override
    public void sendLogRequestMsg(String msg) {
        rabbitTemplate.convertAndSend(MqConst.EXCHANGE_LOG, MqConst.QUEUE_LOG_REQUEST_SAVE, msg);
    }
}
