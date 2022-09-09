package com.open.rabbitmq.demo08;

import com.open.rabbitmq.demo07.Demo07Message;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liuxiaowei
 * @date 2022年09月07日 12:44
 * @Description
 */
@Component
public class Demo08Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id,Integer delay) {
        Demo08Message message = new Demo08Message();
        message.setId(id);
        // 发送消息时，如果传递了方法参数 delay ，则我们会设置消息的 TTL 过期时间。
        rabbitTemplate.convertAndSend(Demo08Message.EXCHANGE, Demo08Message.ROUTING_KEY, message, message1 -> {
            // 设置消息的 TTL 过期时间
            if (delay != null && delay > 0) {
                // Spring-AMQP API 设计有问题，所以传入了 String
                message1.getMessageProperties().setExpiration(String.valueOf(delay));
            }
            return message1;
        });
    }

}
