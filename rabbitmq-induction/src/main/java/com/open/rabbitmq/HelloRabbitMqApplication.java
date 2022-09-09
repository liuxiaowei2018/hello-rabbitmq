package com.open.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author liuxiaowei
 * @date 2022年09月07日 12:37
 * @Description
 */
@EnableAsync
@SpringBootApplication
public class HelloRabbitMqApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloRabbitMqApplication.class, args);
    }
}
