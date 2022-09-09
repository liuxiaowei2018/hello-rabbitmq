package com.open.rabbitmq.demo03;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liuxiaowei
 * @date 2022年09月07日 12:47
 * @Description
 */
@Slf4j
@Component
@RabbitListener(queues = Demo03Message.QUEUE_A)
public class Demo03ConsumerA {

    @RabbitHandler
    public void onMessage(Demo03Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
