package com.open.rabbitmq.errorhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

/**
 * @author liuxiaowei
 * @date 2022年09月08日 20:39
 * @Description
 * 执行顺序上，RabbitListenerErrorHandler 先于 ErrorHandler 执行(前提RabbitListenerErrorHandler需要继续抛出异常)
 * RabbitListenerErrorHandler 需要每个 @RabbitListener 注解上，需要每个手动设置下 errorHandler 属性。而 ErrorHandler 是相对全局的，所有 SimpleRabbitListenerContainerFactory 创建的 SimpleMessageListenerContainer 都会生效。
 */
@Slf4j
@Component
public class RabbitLoggingErrorHandler implements ErrorHandler {

    public RabbitLoggingErrorHandler(SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory) {
        rabbitListenerContainerFactory.setErrorHandler(this);
    }

    @Override
    public void handleError(Throwable t) {
        log.error("[handleError][发生异常]]", t);
    }

}
