package com.open.rabbitmq;

import com.open.rabbitmq.event.MySink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author liuxiaowei
 * @date 2022年09月08日 21:01
 * @Description
 */
@Slf4j
@Component
public class Demo01Consumer {

    @StreamListener(MySink.DEMO01_INPUT)
    public void onMessage(@Payload Demo01Message message) {
        // 消费的消息是 POJO 类型，需要添加 @Payload 注解，声明需要进行反序列化成 POJO 对象
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
