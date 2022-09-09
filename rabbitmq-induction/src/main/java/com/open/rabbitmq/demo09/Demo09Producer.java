package com.open.rabbitmq.demo09;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liuxiaowei
 * @date 2022年09月07日 12:44
 * @Description
 */
@Component
public class Demo09Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id) {
        Demo09Message message = new Demo09Message();
        message.setId(id);
        // 发送消息时，如果传递了方法参数 delay ，则我们会设置消息的 TTL 过期时间。
        rabbitTemplate.convertAndSend(Demo09Message.EXCHANGE, this.getRoutingKey(id), message);
    }

    /**
     * 基于消息的编号取余，路由到对应的子 Queue 中
     * @date 2022/9/8 17:10
     * @param id 
     * @return java.lang.String
     */
    private String getRoutingKey(Integer id) {
        return String.valueOf(id % Demo09Message.QUEUE_COUNT);
    }

}
