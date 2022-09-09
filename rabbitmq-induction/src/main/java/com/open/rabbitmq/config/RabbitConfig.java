package com.open.rabbitmq.config;

import com.open.rabbitmq.demo01.Demo01Message;
import com.open.rabbitmq.demo02.Demo02Message;
import com.open.rabbitmq.demo03.Demo03Message;
import com.open.rabbitmq.demo04.Demo04Message;
import com.open.rabbitmq.demo05.Demo05Message;
import com.open.rabbitmq.demo06.Demo06Message;
import com.open.rabbitmq.demo07.Demo07Message;
import com.open.rabbitmq.demo08.Demo08Message;
import com.open.rabbitmq.demo09.Demo09Message;
import com.open.rabbitmq.demo10.Demo10Message;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.batch.BatchingStrategy;
import org.springframework.amqp.rabbit.batch.SimpleBatchingStrategy;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author liuxiaowei
 * @date 2022年09月07日 12:42
 * @Description
 */
@Configuration
@EnableTransactionManagement
public class RabbitConfig {

    /**
     * Direct Exchange 示例的配置类
     * [Direct Exchange路由规则，是完全匹配 binding key 与routing key]
     */
    public static class DirectExchangeDemoConfiguration {
        // 创建 Queue
        @Bean
        public Queue demo01Queue() {
            return new Queue(Demo01Message.QUEUE, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }

        // 创建 Direct Exchange
        @Bean
        public DirectExchange demo01Exchange() {
            return new DirectExchange(Demo01Message.EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // exclusive: 是否排它
        }

        // 创建 Binding
        // Exchange：Demo01Message.EXCHANGE
        // Routing key：Demo01Message.ROUTING_KEY
        // Queue：Demo01Message.QUEUE
        @Bean
        public Binding demo01Binding() {
            return BindingBuilder.bind(demo01Queue()).to(demo01Exchange()).with(Demo01Message.ROUTING_KEY);
        }

    }

    /**
     * Topic Exchange 示例的配置类
     */
    public static class TopicExchangeDemoConfiguration {
        @Bean
        public Queue demo02Queue() {
            return new Queue(Demo02Message.QUEUE, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }
        @Bean
        public TopicExchange demo02Exchange() {
            return new TopicExchange(Demo02Message.EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // exclusive: 是否排它
        }
        @Bean
        public Binding demo02Binding() {
            return BindingBuilder.bind(demo02Queue()).to(demo02Exchange()).with(Demo02Message.ROUTING_KEY);
        }
    }

    /**
     * Fanout Exchange 示例的配置类
     */
    public static class FanoutExchangeDemoConfiguration {

        // 创建 Queue A
        @Bean
        public Queue demo03QueueA() {
            return new Queue(Demo03Message.QUEUE_A, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }

        // 创建 Queue B
        @Bean
        public Queue demo03QueueB() {
            return new Queue(Demo03Message.QUEUE_B, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }

        // 创建 Fanout Exchange
        @Bean
        public FanoutExchange demo03Exchange() {
            return new FanoutExchange(Demo03Message.EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // exclusive: 是否排它
        }

        // 创建 Binding A
        @Bean
        public Binding demo03BindingA() {
            return BindingBuilder.bind(demo03QueueA()).to(demo03Exchange());
        }

        // 创建 Binding B
        @Bean
        public Binding demo03BindingB() {
            return BindingBuilder.bind(demo03QueueB()).to(demo03Exchange());
        }
    }

    /**
     * Headers Exchange 示例的配置类
     */
    public static class HeadersExchangeDemoConfiguration {

        // 创建 Queue
        @Bean
        public Queue demo04Queue() {
            return new Queue(Demo04Message.QUEUE, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }

        // 创建 Headers Exchange
        @Bean
        public HeadersExchange demo04Exchange() {
            return new HeadersExchange(Demo04Message.EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // exclusive: 是否排它
        }

        // 创建 Binding
        // Exchange：Demo04Message.EXCHANGE
        // Queue：Demo04Message.QUEUE
        // Headers: Demo04Message.HEADER_KEY + Demo04Message.HEADER_VALUE
        @Bean
        public Binding demo4Binding() {
            return BindingBuilder.bind(demo04Queue()).to(demo04Exchange())
                    .where(Demo04Message.HEADER_KEY).matches(Demo04Message.HEADER_VALUE); // 配置 Headers 匹配
        }

    }

    /**
     * Direct Exchange 批量处理示例的配置类
     */
    public static class DirectExchangeBatchDemoConfiguration {

        // 创建 Queue
        @Bean
        public Queue demo05Queue() {
            return new Queue(Demo05Message.QUEUE, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }

        // 创建 Direct Exchange
        @Bean
        public DirectExchange demo05Exchange() {
            return new DirectExchange(Demo05Message.EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // exclusive: 是否排它
        }

        // 创建 Binding
        // Exchange：Demo05Message.EXCHANGE
        // Routing key：Demo05Message.ROUTING_KEY
        // Queue：Demo05Message.QUEUE
        @Bean
        public Binding demo05Binding() {
            return BindingBuilder.bind(demo05Queue()).to(demo05Exchange()).with(Demo05Message.ROUTING_KEY);
        }

    }

    /**
     * Direct Exchange 批量消费示例的配置类(06)
     */
    public static class DirectExchangeBatchConsumerDemoConfiguration {
        @Bean
        public Queue demo06Queue() {
            return new Queue(Demo06Message.QUEUE, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }
        @Bean
        public DirectExchange demo06Exchange() {
            return new DirectExchange(Demo06Message.EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // exclusive: 是否排它
        }
        @Bean
        public Binding demo06Binding() {
            return BindingBuilder.bind(demo06Queue()).to(demo06Exchange()).with(Demo06Message.ROUTING_KEY);
        }
    }

    /**
     * Direct Exchange 示例的配置类
     */
    public static class DirectExchangeRetryDemoConfiguration {
        @Bean
        public Queue demo07Queue() {
            return QueueBuilder.durable(Demo07Message.QUEUE) // durable: 是否持久化
                    .exclusive() // exclusive: 是否排它
                    .autoDelete() // autoDelete: 是否自动删除
                    .deadLetterExchange(Demo07Message.DEAD_EXCHANGE)
                    .deadLetterRoutingKey(Demo07Message.DEAD_ROUTING_KEY)
                    .build();
        }
        // 创建 Dead Queue
        @Bean
        public Queue demo07DeadQueue() {
            return new Queue(Demo07Message.DEAD_QUEUE, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }
        @Bean
        public DirectExchange demo07Exchange() {
            return new DirectExchange(Demo07Message.EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // exclusive: 是否排它
        }
        @Bean
        public DirectExchange demo07DeadExchange() {
            return new DirectExchange(Demo07Message.DEAD_EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // exclusive: 是否排它
        }

        // 创建 Binding
        // Exchange：Demo07Message.EXCHANGE
        // Routing key：Demo07Message.ROUTING_KEY
        // Queue：Demo07Message.QUEUE
        @Bean
        public Binding demo07Binding() {
            return BindingBuilder.bind(demo07Queue()).to(demo07Exchange()).with(Demo07Message.ROUTING_KEY);
        }

        // 创建 Dead Binding
        // Exchange：Demo07Message.EXCHANGE
        // Routing key：Demo07Message.DEAD_ROUTING_KEY
        // Queue：Demo07Message.DEAD_QUEUE
        @Bean
        public Binding demo07DeadBinding() {
            return BindingBuilder.bind(demo07DeadQueue()).to(demo07DeadExchange()).with(Demo07Message.DEAD_ROUTING_KEY);
        }
    }

    /**
     * Direct Exchange 定时队列示例的配置类
     */
    public static class DirectExchangeTtlDemoConfiguration {
        @Bean
        public Queue demo08Queue() {
            return QueueBuilder.durable(Demo08Message.QUEUE) // durable: 是否持久化
                    .exclusive() // exclusive: 是否排它
                    .autoDelete() // autoDelete: 是否自动删除
                    .ttl(10 * 1000) // 设置队列里的默认过期时间为 10 秒
                    .deadLetterExchange(Demo08Message.EXCHANGE)
                    .deadLetterRoutingKey(Demo08Message.DELAY_ROUTING_KEY)
                    .build();
        }
        @Bean
        public Queue demo08DelayQueue() {
            return new Queue(Demo08Message.DELAY_QUEUE, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }
        @Bean
        public DirectExchange demo08Exchange() {
            return new DirectExchange(Demo08Message.EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // exclusive: 是否排它
        }
        // 创建 Binding
        // Exchange：Demo08Message.EXCHANGE
        // Routing key：Demo08Message.ROUTING_KEY
        // Queue：Demo08Message.QUEUE
        @Bean
        public Binding demo08Binding() {
            return BindingBuilder.bind(demo08Queue()).to(demo08Exchange()).with(Demo08Message.ROUTING_KEY);
        }
        // 创建 Delay Binding
        // Exchange：Demo08Message.EXCHANGE
        // Routing key：Demo08Message.DELAY_ROUTING_KEY
        // Queue：Demo08Message.DELAY_QUEUE
        @Bean
        public Binding demo08DelayBinding() {
            return BindingBuilder.bind(demo08DelayQueue()).to(demo08Exchange()).with(Demo08Message.DELAY_ROUTING_KEY);
        }

    }

    /**
     * Direct Exchange-顺序消费示例的配置类
     */
    public static class DirectExchangeOrderDemoConfiguration {
        @Bean
        public Queue demo09Queue0() {
            return new Queue(Demo09Message.QUEUE_0);
        }
        @Bean
        public Queue demo09Queue1() {
            return new Queue(Demo09Message.QUEUE_1);
        }
        @Bean
        public Queue demo09Queue2() {
            return new Queue(Demo09Message.QUEUE_2);
        }
        @Bean
        public Queue demo09Queue3() {
            return new Queue(Demo09Message.QUEUE_3);
        }
        @Bean
        public DirectExchange demo09Exchange() {
            return new DirectExchange(Demo09Message.EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // exclusive: 是否排它
        }
        @Bean
        public Binding demo09Binding0() {
            return BindingBuilder.bind(demo09Queue0()).to(demo09Exchange()).with("0");
        }
        @Bean
        public Binding demo09Binding1() {
            return BindingBuilder.bind(demo09Queue1()).to(demo09Exchange()).with("1");
        }
        @Bean
        public Binding demo09Binding2() {
            return BindingBuilder.bind(demo09Queue2()).to(demo09Exchange()).with("2");
        }
        @Bean
        public Binding demo09Binding3() {
            return BindingBuilder.bind(demo09Queue3()).to(demo09Exchange()).with("3");
        }

    }

    /**
     * 事务消息配置类
     */
    public static class DirectExchangeTcDemoConfiguration {
        @Bean
        public Queue demo10Queue() {
            return new Queue(Demo10Message.QUEUE, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }
        @Bean
        public DirectExchange demo10Exchange() {
            return new DirectExchange(Demo10Message.EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // exclusive: 是否排它
        }
        @Bean
        public Binding demo11Binding() {
            return BindingBuilder.bind(demo10Queue()).to(demo10Exchange()).with(Demo10Message.ROUTING_KEY);
        }
    }

    @Bean
    public RabbitTransactionManager rabbitTransactionManager(ConnectionFactory connectionFactory, RabbitTemplate rabbitTemplate) {
        // 标记创建的 RabbitMQ Channel 是事务性的，从而可以使用 RabbitMQ 的事务消息
        rabbitTemplate.setChannelTransacted(true);
        // 创建 RabbitTransactionManager 对象
        return new RabbitTransactionManager(connectionFactory);
    }

    /**
     * 批量发送
     * @date 2022/9/8 15:38
     * @param connectionFactory
     * @return org.springframework.amqp.rabbit.core.BatchingRabbitTemplate
     */
    @Bean
    public BatchingRabbitTemplate batchRabbitTemplate(ConnectionFactory connectionFactory) {
        // 创建 BatchingStrategy 对象，代表批量策略
        // 超过收集的消息数量的最大条数。
        int batchSize = 16384;
        // 每次批量发送消息的最大内存
        int bufferLimit = 33554432;
        // 超过收集的时间的最大等待时长，单位：毫秒
        int timeout = 30000;
        BatchingStrategy batchingStrategy = new SimpleBatchingStrategy(batchSize, bufferLimit, timeout);
        // 创建 TaskScheduler 对象，用于实现超时发送的定时器
        TaskScheduler taskScheduler = new ConcurrentTaskScheduler();
        // 创建 BatchingRabbitTemplate 对象
        BatchingRabbitTemplate batchTemplate = new BatchingRabbitTemplate(batchingStrategy, taskScheduler);
        batchTemplate.setConnectionFactory(connectionFactory);
        return batchTemplate;
    }

    /**
     * 批量消费
     * @date 2022/9/8 13:48
     * @param configurer
     * @param connectionFactory
     * @return org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
     */
    @Bean(name = "consumerBatchContainerFactory")
    public SimpleRabbitListenerContainerFactory consumerBatchContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        // 创建 SimpleRabbitListenerContainerFactory 对象
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        // 配置消费者的监听器是批量消费消息的类型
        factory.setBatchListener(true);
        factory.setBatchSize(10);
        factory.setReceiveTimeout(30 * 1000L);
        factory.setConsumerBatchEnabled(true);
        return factory;
    }
}
