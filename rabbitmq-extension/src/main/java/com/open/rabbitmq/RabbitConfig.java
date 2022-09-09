package com.open.rabbitmq;

import com.open.rabbitmq.ack.Demo11Message;
import com.open.rabbitmq.confirm.simple.Demo12Message;
import com.open.rabbitmq.converter.Demo15Message;
import com.open.rabbitmq.rpc.RpcMessage;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuxiaowei
 * @date 2022年09月08日 19:53
 * @Description
 */
@Configuration
public class RabbitConfig {

    /**
     * ack交换机配置
     */
    public static class DirectExchangeDemoConfiguration {

        // 创建 Queue
        @Bean
        public Queue demo11Queue() {
            return new Queue(Demo11Message.QUEUE, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }

        // 创建 Direct Exchange
        @Bean
        public DirectExchange demo11Exchange() {
            return new DirectExchange(Demo11Message.EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // exclusive: 是否排它
        }

        // 创建 Binding
        // Exchange：Demo11Message.EXCHANGE
        // Routing key：Demo11Message.ROUTING_KEY
        // Queue：Demo11Message.QUEUE
        @Bean
        public Binding demo11Binding() {
            return BindingBuilder.bind(demo11Queue()).to(demo11Exchange()).with(Demo11Message.ROUTING_KEY);
        }
    }

    /**
     * confirm交换机配置
     */
    public static class DirectExchangeConfirmDemoConfiguration {

        @Bean
        public Queue demo12Queue() {
            return new Queue(Demo12Message.QUEUE, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }
        @Bean
        public DirectExchange demo12Exchange() {
            return new DirectExchange(Demo12Message.EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // exclusive: 是否排它
        }
        @Bean
        public Binding demo12Binding() {
            return BindingBuilder.bind(demo12Queue()).to(demo12Exchange()).with(Demo12Message.ROUTING_KEY);
        }
    }

    /**
     * Rpc配置类
     */
    public static class DirectExchangeRpcDemoConfiguration {
        @Bean
        public Queue demoRpcQueue() {
            return new Queue(RpcMessage.QUEUE, // Queue 名字
                    false, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }
        @Bean
        public DirectExchange demoRpcExchange() {
            return new DirectExchange(RpcMessage.EXCHANGE,
                    false,  // durable: 是否持久化
                    false);  // autoDelete: 是否自动删除
        }
        @Bean
        public Binding demoRpcBinding() {
            return BindingBuilder.bind(demoRpcQueue()).to(demoRpcExchange()).with(RpcMessage.ROUTING_KEY);
        }
    }

    /**
     * json序列化示例的配置类
     */
    public static class DirectExchangeJsonDemoConfiguration {
        @Bean
        public Queue demo15Queue() {
            return new Queue(Demo15Message.QUEUE, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }
        @Bean
        public DirectExchange demo15Exchange() {
            return new DirectExchange(Demo15Message.EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // exclusive: 是否排它
        }
        @Bean
        public Binding demo15Binding() {
            return BindingBuilder.bind(demo15Queue()).to(demo15Exchange()).with(Demo15Message.ROUTING_KEY);
        }
    }

    /**
     * 默认情况下，RabbitTemplate 采用 SimpleMessageConverter
     *  SimpleMessageConverter 内部，采用 Java 自带序列化方式，实现对 Java POJO 对象的序列化和反序列化
     * 建议采用 如下方式 Jackson2JsonMessageConverter ，使用 JSON 实现对 Java POJO 对象的序列化和反序列化
     * @date 2022/9/8 20:32
     * @return org.springframework.amqp.support.converter.MessageConverter
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
