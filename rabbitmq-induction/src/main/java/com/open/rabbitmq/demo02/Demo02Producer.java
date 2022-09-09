package com.open.rabbitmq.demo02;

import com.open.rabbitmq.demo01.Demo01Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author liuxiaowei
 * @date 2022年09月07日 12:44
 * @Description
 */
@Component
public class Demo02Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id, String routingKey) {
        Demo02Message message = new Demo02Message();
        message.setId(id);
        // 同步发送消息
        rabbitTemplate.convertAndSend(Demo02Message.EXCHANGE, routingKey, message);
    }

}
