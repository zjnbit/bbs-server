package com.zjnbit.bbs.api.listener;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.zjnbit.bbs.api.framework.constant.MqConst;
import com.zjnbit.bbs.api.mapper.LogRequestMapper;
import com.zjnbit.bbs.api.model.dto.BbsTopicReplyAddDto;
import com.zjnbit.bbs.api.model.entity.LogRequestEntity;
import com.zjnbit.bbs.api.service.BbsTopicService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitMqListener {

    @Autowired
    LogRequestMapper logRequestMapper;
    @Autowired
    BbsTopicService bbsTopicService;

    @RabbitListener(queues = MqConst.QUEUE_LOG_REQUEST_SAVE)
    public void logRequstSave(String msg, Channel channel, Message message) throws IOException {
        logRequestMapper.insert(JSON.parseObject(msg, LogRequestEntity.class));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    /**
     * 添加回复
     *
     * @author 非羽Army
     **/
    @RabbitListener(queues = MqConst.QUEUE_TOPIC_REPLY_ADD)
    public void topicReplyAdd(String msg, Channel channel, Message message) throws IOException {
        BbsTopicReplyAddDto bbsTopicReplyAddDto = JSON.parseObject(msg, BbsTopicReplyAddDto.class);
        bbsTopicService.addTopicReply(bbsTopicReplyAddDto);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    /**
     * 更新主题点赞数量
     *
     * @author 非羽Army
     **/
    @RabbitListener(queues = MqConst.QUEUE_TOPIC_LIKE_REFRESH)
    public void topicLickRefresh(String msg, Channel channel, Message message) throws IOException {
        Long topicId = Long.valueOf(msg);
        bbsTopicService.topicLikeRefresh(topicId);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
