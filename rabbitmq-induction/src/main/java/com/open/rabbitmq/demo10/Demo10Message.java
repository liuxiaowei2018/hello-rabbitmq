package com.open.rabbitmq.demo10;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liuxiaowei
 * @date 2022年09月07日 12:41
 * @Description 事务消息
 * RabbitMQ 提供的并不是完整的的事务消息的支持，缺少了回查机制
 * Spring-AMQP 通过 RabbitTransactionManager 来实现对 Spring Transaction 的集成，实际开发中，只需要配合使用 @Transactional 注解，来声明事务即可
 */
@Data
public class Demo10Message implements Serializable {

    public static final String EXCHANGE = "EXCHANGE_DEMO_10";
    public static final String QUEUE = "QUEUE_DEMO_10";
    public static final String ROUTING_KEY = "ROUTING_KEY_10";

    /**
     * 编号
     */
    private Integer id;
}
