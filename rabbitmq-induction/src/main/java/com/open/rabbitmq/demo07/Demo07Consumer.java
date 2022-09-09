package com.open.rabbitmq.demo07;

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
@RabbitListener(queues = Demo07Message.QUEUE)
public class Demo07Consumer {

    /**
     * 消费重试演示
     * @date 2022/9/8 15:37
     * @param message
     */
    @RabbitHandler
    public void onMessage(Demo07Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
        // 此处抛出一个 RuntimeException 异常，模拟消费失败
        throw new RuntimeException("模拟消费失败");
    }

}
