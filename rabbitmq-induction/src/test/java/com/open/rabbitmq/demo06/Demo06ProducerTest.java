package com.open.rabbitmq.demo06;

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
 * @date 2022年09月08日 15:43
 * @Description
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloRabbitMqApplication.class)
public class Demo06ProducerTest {

    @Autowired
    private Demo06Producer producer;

    @Test
    public void testSyncSend01() throws InterruptedException {
        // 发送 3 条消息
        // 超时情况下的批量消费
        this.testSyncSend(3);
    }

    @Test
    public void testSyncSend02() throws InterruptedException {
        // 发送 3 条消息
        // 未超时情况下的批量消费
        this.testSyncSend(10);
    }

    private void testSyncSend(int n) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            // 同步发送消息
            int id = (int) (System.currentTimeMillis() / 1000);
            producer.syncSend(id);
            log.info("[testSyncSendMore][发送编号：[{}] 发送成功]", id);
        }
        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

}