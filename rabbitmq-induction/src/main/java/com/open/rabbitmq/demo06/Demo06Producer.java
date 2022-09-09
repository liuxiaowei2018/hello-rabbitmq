package com.open.rabbitmq.demo06;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liuxiaowei
 * @date 2022年09月07日 12:44
 * @Description
 */
@Component
public class Demo06Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id) {
        Demo06Message message = new Demo06Message();
        message.setId(id);
        rabbitTemplate.convertAndSend(Demo06Message.EXCHANGE, Demo06Message.ROUTING_KEY, message);
    }

}
