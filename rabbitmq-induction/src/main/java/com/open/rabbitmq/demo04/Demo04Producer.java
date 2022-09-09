package com.open.rabbitmq.demo04;

import com.open.rabbitmq.demo03.Demo03Message;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liuxiaowei
 * @date 2022年09月07日 12:44
 * @Description
 */
@Component
public class Demo04Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id, String headerValue) {
        // 创建 MessageProperties 属性
        MessageProperties messageProperties = new MessageProperties();
        // 设置 header
        messageProperties.setHeader(Demo04Message.HEADER_KEY, headerValue);
        Demo04Message demo04Message = new Demo04Message();
        demo04Message.setId(id);
        Message message = rabbitTemplate.getMessageConverter().toMessage(demo04Message, messageProperties);
        // 同步发送消息
        rabbitTemplate.send(Demo04Message.EXCHANGE, null, message);
    }

}
