package com.open.rabbitmq.demo09;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author liuxiaowei
 * @date 2022年09月08日 16:34
 * @Description
 */
@Slf4j
@Component
@RabbitListener(queues = Demo09Message.QUEUE_0)
@RabbitListener(queues = Demo09Message.QUEUE_1)
@RabbitListener(queues = Demo09Message.QUEUE_2)
@RabbitListener(queues = Demo09Message.QUEUE_3)
public class Demo09Consumer {

    @RabbitHandler(isDefault = true)
    public void onMessage(Message<Demo09Message> message) {
        log.info("[onMessage][线程编号:{} Queue:{} 消息编号：{}]", Thread.currentThread().getId(), getQueue(message), message.getPayload().getId());
    }

    private static String getQueue(Message<Demo09Message> message) {
        return message.getHeaders().get("amqp_consumerQueue", String.class);
    }
}
