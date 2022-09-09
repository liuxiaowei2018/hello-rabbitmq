package com.open.rabbitmq;

import com.open.rabbitmq.event.MySink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author liuxiaowei
 * @date 2022年09月07日 12:37
 * @Description
 */
@EnableAsync
@EnableBinding(MySink.class)
@SpringBootApplication
public class RabbitMqConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMqConsumerApplication.class, args);
    }
}
