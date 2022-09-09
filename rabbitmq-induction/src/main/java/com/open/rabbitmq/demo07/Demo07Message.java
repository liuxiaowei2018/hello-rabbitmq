package com.open.rabbitmq.demo07;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liuxiaowei
 * @date 2022年09月07日 12:41
 * @Description 消费重试
 */
@Data
public class Demo07Message implements Serializable {

    public static final String EXCHANGE = "EXCHANGE_DEMO_07";
    public static final String DEAD_EXCHANGE = "DEAD_EXCHANGE_DEMO_07";

    // 正常队列
    public static final String QUEUE = "QUEUE_DEMO_07";
    // 死信队列
    public static final String DEAD_QUEUE = "DEAD_QUEUE_DEMO_07";

    // 正常路由键
    public static final String ROUTING_KEY = "ROUTING_KEY_07";
    // 死信路由键
    public static final String DEAD_ROUTING_KEY = "DEAD_ROUTING_KEY_07";

    /**
     * 编号
     */
    private Integer id;
}
