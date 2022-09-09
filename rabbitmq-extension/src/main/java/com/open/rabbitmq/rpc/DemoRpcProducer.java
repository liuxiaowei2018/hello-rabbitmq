package com.open.rabbitmq.rpc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author liuxiaowei
 * @date 2022年09月08日 20:26
 * @Description
 */
@Slf4j
@Component
public class DemoRpcProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public String syncSend(Integer id) {
        // 创建 Demo01Message 消息
        RpcMessage message = new RpcMessage();
        message.setId(id);
        // <1> 创建 CorrelationData 对象
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        // <2> 同步发送消息，并接收结果
        return (String) rabbitTemplate.convertSendAndReceive(RpcMessage.EXCHANGE, RpcMessage.ROUTING_KEY, message, correlationData);
    }

}
