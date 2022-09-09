package com.open.rabbitmq.returnpro;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author liuxiaowei
 * @date 2022年09月08日 20:21
 * @Description
 * 当 Producer 成功发送消息到 RabbitMQ Broker 时，但是在通过 Exchange 进行匹配不到 Queue 时，Broker 会将该消息回退给 Producer
 */
@Slf4j
@Component
public class RabbitProducerReturnCallback implements RabbitTemplate.ReturnCallback {

    public RabbitProducerReturnCallback(RabbitTemplate rabbitTemplate) {
        rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.error("[returnedMessage][message: [{}] replyCode: [{}] replyText: [{}] exchange: [{}] routingKey: [{}]]",
                message, replyCode, replyText, exchange, routingKey);
    }
}
