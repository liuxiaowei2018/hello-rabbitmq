package com.open.rabbitmq.demo01;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author liuxiaowei
 * @date 2022年09月07日 12:44
 * @Description
 */
@Component
public class Demo01Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 指定 Exchange + RoutingKey ，从而路由到一个 Queue 中
     * @date 2022/9/7 12:46
     * @param id
     */
    public void syncSend(Integer id) {
        Demo01Message message = new Demo01Message();
        message.setId(id);
        // 同步发送消息
        rabbitTemplate.convertAndSend(Demo01Message.EXCHANGE, Demo01Message.ROUTING_KEY, message);
    }

    /**
     * 此处即使我们传入的 RoutingKey 为队列名，一样可以发到对应队列
     * @date 2022/9/7 12:46
     * @param id
     */
    public void syncSendDefault(Integer id) {
        Demo01Message message = new Demo01Message();
        message.setId(id);
        // 同步发送消息
        rabbitTemplate.convertAndSend(Demo01Message.QUEUE, message);
    }

    @Async
    public ListenableFuture<Void> asyncSend(Integer id) {
        try {
            // 发送消息
            this.syncSend(id);
            // 返回成功的 Future
            return AsyncResult.forValue(null);
        } catch (Throwable ex) {
            // 返回异常的 Future
            return AsyncResult.forExecutionException(ex);
        }
    }

}
