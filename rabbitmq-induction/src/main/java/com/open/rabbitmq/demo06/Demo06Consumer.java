package com.open.rabbitmq.demo06;

import com.open.rabbitmq.demo05.Demo05Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liuxiaowei
 * @date 2022年09月07日 12:47
 * @Description
 */
@Slf4j
@Component
@RabbitListener(queues = Demo06Message.QUEUE,containerFactory = "consumerBatchContainerFactory")
public class Demo06Consumer {

    /**
     * 批量消费消息
     * @date 2022/9/8 15:37
     * @param messages
     */
    @RabbitHandler
    public void onMessage(List<Demo06Message> messages) {
        log.info("[onMessage][线程编号:{} 消息数量：{}]", Thread.currentThread().getId(), messages.size());
    }

}
