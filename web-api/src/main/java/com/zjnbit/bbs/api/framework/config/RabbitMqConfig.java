package com.zjnbit.bbs.api.framework.config;

import com.zjnbit.bbs.api.framework.constant.MqConst;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMqConfig {
    @Bean
    DirectExchange logDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(MqConst.EXCHANGE_LOG)
                .durable(true)
                .build();
    }

    @Bean
    public Queue logRequestQueue() {
        return new Queue(MqConst.QUEUE_LOG_REQUEST_SAVE);
    }

    @Bean
    Binding logRequestBinding(DirectExchange logDirect, Queue logRequestQueue) {
        return BindingBuilder
                .bind(logRequestQueue)
                .to(logDirect)
                .with(MqConst.QUEUE_LOG_REQUEST_SAVE);
    }

    @Bean
    DirectExchange bbsTopicDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(MqConst.EXCHANGE_TOPIC)
                .durable(true)
                .build();
    }

    @Bean
    public Queue topicReplyAddQueue() {
        return new Queue(MqConst.QUEUE_TOPIC_REPLY_ADD);
    }

    @Bean
    Binding topicReplyAddBinding(DirectExchange bbsTopicDirect, Queue topicReplyAddQueue) {
        return BindingBuilder
                .bind(topicReplyAddQueue)
                .to(bbsTopicDirect)
                .with(MqConst.QUEUE_TOPIC_REPLY_ADD);
    }

    @Bean
    public Queue topicLikeRefreshQueue() {
        return new Queue(MqConst.QUEUE_TOPIC_LIKE_REFRESH);
    }

    @Bean
    Binding topicLikeRefreshBinding(DirectExchange bbsTopicDirect, Queue topicLikeRefreshQueue) {
        return BindingBuilder
                .bind(topicLikeRefreshQueue)
                .to(bbsTopicDirect)
                .with(MqConst.QUEUE_TOPIC_LIKE_REFRESH);
    }
}
