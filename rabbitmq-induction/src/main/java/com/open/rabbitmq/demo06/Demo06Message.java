package com.open.rabbitmq.demo06;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liuxiaowei
 * @date 2022年09月07日 12:41
 * @Description 批量消费消息
 * 阻塞等待最多 receiveTimeout 秒，拉取 batchSize 条消息，进行批量消费。
 * - 如果在 receiveTimeout 秒内已经成功拉取到 batchSize 条消息，则直接进行批量消费消息
 * - 如果在 receiveTimeout 秒还没拉取到 batchSize 条消息，不再等待，而是进行批量消费消息
 */
@Data
public class Demo06Message implements Serializable {

    public static final String EXCHANGE = "EXCHANGE_DEMO_06";
    public static final String QUEUE = "QUEUE_DEMO_06";
    public static final String ROUTING_KEY = "ROUTING_KEY_06";

    /**
     * 编号
     */
    private Integer id;
}
