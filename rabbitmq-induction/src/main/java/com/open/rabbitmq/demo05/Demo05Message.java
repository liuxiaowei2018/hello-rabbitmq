package com.open.rabbitmq.demo05;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liuxiaowei
 * @date 2022年09月07日 12:41
 * @Description 批量发送消息 && 批量消费消息
 * Spring-AMQP 提供的批量发送消息，它提供了一个 MessageBatch 消息收集器，将发送给相同 Exchange + RoutingKey 的消息们，“偷偷”收集在一起，当满足条件时候，一次性批量发送提交给 RabbitMQ Broker
 *
 */
@Data
public class Demo05Message implements Serializable {

    public static final String EXCHANGE = "EXCHANGE_DEMO_05";
    public static final String QUEUE = "QUEUE_DEMO_05";
    public static final String ROUTING_KEY = "ROUTING_KEY_05";

    /**
     * 编号
     */
    private Integer id;
}
