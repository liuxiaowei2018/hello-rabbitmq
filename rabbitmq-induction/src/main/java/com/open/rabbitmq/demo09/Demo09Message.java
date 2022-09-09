package com.open.rabbitmq.demo09;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liuxiaowei
 * @date 2022年09月07日 12:41
 * @Description 顺序消息
 * 完全严格顺序 ：在【普通顺序消息】的基础上，Consumer 严格顺序消费。
 * 这里演示的是 有且仅启动一个 Consumer 消费该队列，保证 Consumer 严格顺序消费。
 * 如果是分布式的消费者集群 需要分布式锁保证消费顺序
 */
@Data
public class Demo09Message implements Serializable {

    private static final String QUEUE_BASE = "QUEUE_DEMO_9-";
    public static final String QUEUE_0 = QUEUE_BASE + "0";
    public static final String QUEUE_1 = QUEUE_BASE + "1";
    public static final String QUEUE_2 = QUEUE_BASE + "2";
    public static final String QUEUE_3 = QUEUE_BASE + "3";

    public static final int QUEUE_COUNT = 4;

    public static final String EXCHANGE = "EXCHANGE_DEMO_9";

    /**
     * 编号
     */
    private Integer id;
}
