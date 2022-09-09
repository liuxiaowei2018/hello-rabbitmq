package com.open.rabbitmq.demo03;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liuxiaowei
 * @date 2022年09月07日 12:44
 * @Description
 */
@Component
public class Demo03Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 指定 Exchange + 无须指定routingKey ，从而路由到一个 Queue 中
     *
     * @param id
     * @date 2022/9/7 12:46
     */
    public void syncSend(Integer id) {
        Demo03Message message = new Demo03Message();
        message.setId(id);
        // 同步发送消息
        rabbitTemplate.convertAndSend(Demo03Message.EXCHANGE, null, message);
    }


}
