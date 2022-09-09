package com.open.rabbitmq.demo05;

import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liuxiaowei
 * @date 2022年09月07日 12:44
 * @Description
 */
@Component
public class Demo05Producer {

    @Autowired
    private BatchingRabbitTemplate batchingRabbitTemplate;

    /**
     * 批量发送
     *
     * @param id
     * @date 2022/9/7 12:46
     */
    public void syncSend(Integer id) {
        Demo05Message message = new Demo05Message();
        message.setId(id);
        // BatchingRabbitTemplate 通过重写 #send(String exchange, String routingKey, Message message, CorrelationData correlationData) 核心方法
        // 实现批量发送的功能
        batchingRabbitTemplate.convertAndSend(Demo05Message.EXCHANGE, Demo05Message.ROUTING_KEY, message);
    }


}
