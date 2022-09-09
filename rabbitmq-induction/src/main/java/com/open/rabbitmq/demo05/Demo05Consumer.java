package com.open.rabbitmq.demo05;

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
@RabbitListener(queues = Demo05Message.QUEUE)
public class Demo05Consumer {

    /**
     * 正常消费消息
     * @date 2022/9/8 15:37
     * @param message
     */
    @RabbitHandler
    public void onMessage(Demo05Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
