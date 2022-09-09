package com.open.rabbitmq.converter;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liuxiaowei
 * @date 2022年09月08日 19:53
 * @Description
 */
@Component
public class Demo15Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id) {
        Demo15Message message = new Demo15Message();
        message.setId(id);
        // 同步发送消息
        rabbitTemplate.convertAndSend(Demo15Message.EXCHANGE, Demo15Message.ROUTING_KEY, message);
    }
}
