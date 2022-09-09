package com.open.rabbitmq.demo10;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liuxiaowei
 * @date 2022年09月08日 16:34
 * @Description
 */
@Slf4j
@Component
@RabbitListener(queues = Demo10Message.QUEUE)
public class Demo10Consumer {

    @RabbitHandler
    public void onMessage(Demo10Message message) {
        log.info("[onMessage][线程编号:{} 事务消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
