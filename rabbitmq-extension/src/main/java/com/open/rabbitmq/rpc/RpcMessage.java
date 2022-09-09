package com.open.rabbitmq.rpc;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liuxiaowei
 * @date 2022年09月08日 20:23
 * @Description
 */
@Data
public class RpcMessage implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_rpc";

    public static final String EXCHANGE = "EXCHANGE_DEMO_rpc";

    public static final String ROUTING_KEY = "ROUTING_KEY_rpc";

    /**
     * 编号
     */
    private Integer id;
}
