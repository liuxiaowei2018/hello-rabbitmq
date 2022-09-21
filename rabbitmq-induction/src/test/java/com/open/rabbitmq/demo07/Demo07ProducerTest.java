package com.open.rabbitmq.demo07;

import com.open.rabbitmq.HelloRabbitMqApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * @author liuxiaowei
 * @date 2022年09月08日 16:07
 * @Description
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloRabbitMqApplication.class)
public class Demo07ProducerTest {

    @Autowired
    private Demo07Producer producer;

    @Test
    public void testSyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        producer.syncSend(id);
        log.info("[testSyncSend][发送编号：[{}] 发送成功]", id);
        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
        // Demo07Consumer 重试消费消息 3 次，每次间隔 1 秒，全部都失败，最终该消息转发到死信队列中
        // Demo07DeadConsumer 消费死信队列中的该消息
    }

}