package com.open.rabbitmq.confirm.simple;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liuxiaowei
 * @date 2022年09月08日 19:52
 * @Description
 */
@Data
public class Demo12Message implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_12";

    public static final String EXCHANGE = "EXCHANGE_DEMO_12";

    public static final String ROUTING_KEY = "ROUTING_KEY_12";

    /**
     * 编号
     */
    private Integer id;
}
