package com.open.rabbitmq.errorhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.stereotype.Component;

/**
 * @author liuxiaowei
 * @date 2022年09月08日 20:37
 * @Description 对 Consumer 消费异常的处理
 * 实现 org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler 接口
 */
@Slf4j
@Component("rabbitListenerErrorHandler")
public class RabbitListenerErrorHandlerImpl implements RabbitListenerErrorHandler {

    @Override
    public Object handleError(Message amqpMessage, org.springframework.messaging.Message<?> message, ListenerExecutionFailedException exception) throws Exception {
        // 打印异常日志
        log.error("[handleError][amqpMessage:[{}] message:[{}]]", amqpMessage, message, exception);
        // 直接继续抛出异常 -(一定要继续抛出该异常，否则消费重试机制将失效)
        // return 结果，意味着 Consumer 消息成功
        throw exception;
    }
}
