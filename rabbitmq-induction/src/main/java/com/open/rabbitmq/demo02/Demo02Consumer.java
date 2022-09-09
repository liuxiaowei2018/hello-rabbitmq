package com.open.rabbitmq.demo02;

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
@RabbitListener(queues = Demo02Message.QUEUE)
public class Demo02Consumer {

    @RabbitHandler
    public void onMessage(Demo02Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
