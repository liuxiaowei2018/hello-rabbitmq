package com.open.rabbitmq.demo02;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liuxiaowei
 * @date 2022年09月07日 12:41
 * @Description
 * 实现 Java Serializable 序列化接口。因为 RabbitTemplate 默认使用 Java 自带的序列化方式，进行序列化 POJO 类型的消息
 */
@Data
public class Demo02Message implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_02";

    public static final String EXCHANGE = "EXCHANGE_DEMO_02";

    /**
     * 匹配以 "x.xx" 结尾，开头可以是任意个单词
     */
    public static final String ROUTING_KEY = "#.x.xx";

    /**
     * 编号
     */
    private Integer id;
}
