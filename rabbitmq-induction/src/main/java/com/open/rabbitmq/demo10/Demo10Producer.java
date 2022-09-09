package com.open.rabbitmq.demo10;

import com.open.rabbitmq.demo08.Demo08Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liuxiaowei
 * @date 2022年09月07日 12:44
 * @Description
 */
@Slf4j
@Component
public class Demo10Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Transactional
    public void syncSend(Integer id) throws InterruptedException {
        Demo08Message message = new Demo08Message();
        message.setId(id);
        // 发送消息时，如果传递了方法参数 delay ，则我们会设置消息的 TTL 过期时间。
        rabbitTemplate.convertAndSend(Demo08Message.EXCHANGE, Demo08Message.ROUTING_KEY, message);
        log.info("[syncSend][发送编号：[{}] 发送成功]", id);
        // 等待 Thread#sleep(long millis) 10 秒，判断 RabbitMQ 事务是否生效
        // - 如果同步发送消息成功后，Consumer 立即消费到该消息，说明未生效。
        // - 如果 Consumer 是 10 秒之后，才消费到该消息，说明已生效
        Thread.sleep(10 * 1000L);
    }

}
