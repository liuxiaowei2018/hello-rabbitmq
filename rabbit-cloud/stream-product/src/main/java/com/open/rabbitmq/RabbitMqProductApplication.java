package com.open.rabbitmq;

import com.open.rabbitmq.event.MySource;
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
@EnableBinding(MySource.class)
@SpringBootApplication
public class RabbitMqProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMqProductApplication.class, args);
    }
}
