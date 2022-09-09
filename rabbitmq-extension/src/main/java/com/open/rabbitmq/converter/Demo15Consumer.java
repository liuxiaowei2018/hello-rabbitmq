package com.open.rabbitmq.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liuxiaowei
 * @date 2022年09月08日 20:34
 * @Description
 */
@Slf4j
@Component
@RabbitListener(queues = Demo15Message.QUEUE)
public class Demo15Consumer {

    @RabbitHandler(isDefault = true)
    public void onMessage(Message message) {
        // 通过查看具体消息内容，判断是不是真的使用 JSON 格式，所以采用 AMQP Message 接收消息
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), new String(message.getBody()));
    }

}
