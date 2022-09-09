package com.open.rabbitmq.ack;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liuxiaowei
 * @date 2022年09月08日 19:53
 * @Description
 */
@Component
public class Demo11Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id) {
        Demo11Message message = new Demo11Message();
        message.setId(id);
        // 同步发送消息
        rabbitTemplate.convertAndSend(Demo11Message.EXCHANGE, Demo11Message.ROUTING_KEY, message);
    }
}
