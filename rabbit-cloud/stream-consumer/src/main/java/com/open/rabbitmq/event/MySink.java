package com.open.rabbitmq.event;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author liuxiaowei
 * @date 2022年09月08日 20:54
 * @Description
 */
public interface MySink {

    String DEMO01_INPUT = "demo01-input";

    @Input(DEMO01_INPUT)
    SubscribableChannel demo01Input();

}
