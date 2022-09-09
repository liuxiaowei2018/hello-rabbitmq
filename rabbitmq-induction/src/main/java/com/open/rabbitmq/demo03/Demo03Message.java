package com.open.rabbitmq.demo03;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liuxiaowei
 * @date 2022年09月07日 12:41
 * @Description
 * 实现 Java Serializable 序列化接口。因为 RabbitTemplate 默认使用 Java 自带的序列化方式，进行序列化 POJO 类型的消息
 * 无需定义 RoutingKey。因为，Fanout Exchange 仅需要 Exchange 即可
 */
@Data
public class Demo03Message implements Serializable {

    public static final String EXCHANGE = "EXCHANGE_DEMO_03";

    public static final String QUEUE_A = "QUEUE_DEMO_03_A";
    public static final String QUEUE_B = "QUEUE_DEMO_03_B";

    /**
     * 编号
     */
    private Integer id;
}
