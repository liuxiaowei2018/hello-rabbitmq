package com.open.rabbitmq.rpc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liuxiaowei
 * @date 2022年09月08日 20:26
 * @Description
 */
@Slf4j
@Component
@RabbitListener(queues = RpcMessage.QUEUE)
public class DemoRpcConsumer {

    @RabbitHandler
    public String onMessage(RpcMessage message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
        return "xxx";
    }
}
