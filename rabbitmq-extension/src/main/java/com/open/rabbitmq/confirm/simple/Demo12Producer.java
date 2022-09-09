package com.open.rabbitmq.confirm.simple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liuxiaowei
 * @date 2022年09月08日 19:53
 * @Description
 */
@Slf4j
@Component
public class Demo12Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 同步Confirm模式
     *  Producer 要使用同步的 Confirm 模式，需要调用 #invoke(action, acks, nacks) 方法
     * @date 2022/9/8 20:07
     * @param id
     */
    public void syncSend(Integer id) {
        Demo12Message message = new Demo12Message();
        message.setId(id);
        // 同步发送消息
        rabbitTemplate.invoke(operations -> {
            // 同步发送消息
            operations.convertAndSend(Demo12Message.EXCHANGE, Demo12Message.ROUTING_KEY, message);
            log.info("[doInRabbit][发送消息完成]");
            // 等待确认
            operations.waitForConfirms(0); // timeout 参数，如果传递 0 ，表示无限等待
            log.info("[doInRabbit][等待 Confirm 完成]");
            return null;
        }, (deliveryTag, multiple) -> log.info("[handle][Confirm 成功]"),
                (deliveryTag, multiple) -> log.info("[handle][Confirm 成功]"));
    }
}
