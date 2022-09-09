package com.open.rabbitmq.event;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author liuxiaowei
 * @date 2022年09月08日 20:54
 * @Description
 */
public interface MySource {

    @Output("demo01-output")
    MessageChannel demo01Output();

}
