package com.open.rabbitmq.demo04;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liuxiaowei
 * @date 2022年09月07日 12:41
 * @Description
 * Headers Exchange 不依赖于 routing key 与 binding key 的匹配规则来路由消息，而是根据发送的消息内容中的 headers 属性进行匹配
 * - 在绑定 Queue 与 Exchange 时指定一组 headers 键值对。
 * - 当消息发送到 Exchange 时，RabbitMQ 会取到该消息的 headers（也是一个键值对的形式），
 *   对比其中的键值对是否完全匹配 Queue 与 Exchange 绑定时指定的键值对；如果完全匹配则消息会路由到该 Queue,否则不会路由到该 Queue
 */
@Data
public class Demo04Message implements Serializable {

    public static final String EXCHANGE = "EXCHANGE_DEMO_04";

    public static final String QUEUE = "QUEUE_DEMO_04";
    public static final String HEADER_KEY = "color";
    public static final String HEADER_VALUE = "red";

    /**
     * 编号
     */
    private Integer id;
}
