package com.open.rabbitmq.demo08;

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
@RabbitListener(queues = Demo08Message.DELAY_QUEUE)
public class Demo08Consumer {

    @RabbitHandler
    public void onMessage(Demo08Message message) {
        log.info("[onMessage][线程编号:{} 延时消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
