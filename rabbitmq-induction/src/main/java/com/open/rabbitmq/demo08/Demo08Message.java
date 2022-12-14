package com.open.rabbitmq.demo08;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liuxiaowei
 * @date 2022年09月07日 12:41
 * @Description 定时消息
 * 采用ttl队列+死信队列实现
 * 生产环境推荐使用Delayed Message Plugin
 */
@Data
public class Demo08Message implements Serializable {

    public static final String EXCHANGE = "EXCHANGE_DEMO_08";

    // 正常队列
    public static final String QUEUE = "QUEUE_DEMO_08";
    // 延迟队列（死信队列）
    public static final String DELAY_QUEUE = "DELAY_QUEUE_DEMO_08";

    // 正常路由键
    public static final String ROUTING_KEY = "ROUTING_KEY_08";
    // 延迟路由键（死信路由键）
    public static final String DELAY_ROUTING_KEY = "DELAY_ROUTING_KEY_08";

    /**
     * 编号
     */
    private Integer id;
}
