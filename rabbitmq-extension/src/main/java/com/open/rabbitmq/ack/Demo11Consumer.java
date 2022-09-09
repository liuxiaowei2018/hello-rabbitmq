package com.open.rabbitmq.ack;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author liuxiaowei
 * @date 2022年09月08日 19:54
 * @Description
 */
@Slf4j
@Component
@RabbitListener(queues = Demo11Message.QUEUE)
public class Demo11Consumer {

    @RabbitHandler
    public void onMessage(Demo11Message message, Channel channel,
                          @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
        // 提交消费进度
        if (message.getId() % 2 == 1) {
            // ack 确认消息
            // 第二个参数 multiple ，用于批量确认消息，为了减少网络流量，手动确认可以被批处。
            // 1. 当 multiple 为 true 时，则可以一次性确认 deliveryTag 小于等于传入值的所有消息
            // 2. 当 multiple 为 false 时，则只确认当前 deliveryTag 对应的消息
            channel.basicAck(deliveryTag, false);
        }
        // 在消费逻辑中，我们故意只提交消费的消息的 Demo12Message.id 为奇数的消息。
        // 😈 这样，我们只需要发送一条 id=1 ，一条 id=2 的消息，如果第二条的消费进度没有被提交，就可以说明手动提交消费进度成功
    }

}
