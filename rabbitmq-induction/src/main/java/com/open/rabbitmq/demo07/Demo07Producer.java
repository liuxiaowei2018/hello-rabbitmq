package com.open.rabbitmq.demo07;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liuxiaowei
 * @date 2022年09月07日 12:44
 * @Description
 */
@Component
public class Demo07Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id) {
        Demo07Message message = new Demo07Message();
        message.setId(id);
        rabbitTemplate.convertAndSend(Demo07Message.EXCHANGE, Demo07Message.ROUTING_KEY, message);
    }

}
