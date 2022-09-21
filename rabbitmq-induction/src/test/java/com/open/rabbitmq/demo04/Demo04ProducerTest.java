package com.open.rabbitmq.demo04;

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
 * @date 2022年09月08日 13:23
 * @Description
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloRabbitMqApplication.class)
public class Demo04ProducerTest {

    @Autowired
    private Demo04Producer producer;

    @Test
    public void testSyncSendSuccess() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        producer.syncSend(id, Demo04Message.HEADER_VALUE);
        log.info("[testSyncSend][发送编号：[{}] 发送成功]", id);
        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
        // #testSyncSendSuccess() 方法，发送消息的 Headers 的值 "red" ，可以匹配到 "DEMO_QUEUE_04"
    }

    @Test
    public void testSyncSendFailure() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        producer.syncSend(id, "error");
        log.info("[testSyncSend][发送编号：[{}] 发送成功]", id);
        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
        // #testSyncSendFailure() 方法，发送消息的 Headers 的值 "error" ，无法匹配到 "DEMO_QUEUE_04"
    }
}