package com.open.rabbitmq.demo10;

import com.open.rabbitmq.HelloRabbitMqApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * @author liuxiaowei
 * @date 2022年09月08日 19:46
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloRabbitMqApplication.class)
public class Demo10ProducerTest {

    @Autowired
    private Demo10Producer producer;

    @Test
    public void testSyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        producer.syncSend(id);
        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
        // 10 秒后，Producer 提交事务 ->Consumer消费到该消息
    }

}