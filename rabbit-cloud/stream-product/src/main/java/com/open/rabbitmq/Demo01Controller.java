package com.open.rabbitmq;

import com.open.rabbitmq.event.MySource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @author liuxiaowei
 * @date 2022年09月08日 20:56
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/demo01")
public class Demo01Controller {

    @Resource
    private MySource mySource;

    @GetMapping("/send")
    public boolean send() {
        // <1> 创建 Message
        Demo01Message message = new Demo01Message();
        message.setId(new Random().nextInt());
        // <2> 创建 Spring Message 对象
        Message<Demo01Message> springMessage = MessageBuilder.withPayload(message).build();
        // <3> 发送消息
        boolean result = mySource.demo01Output().send(springMessage);
        log.info("[send][发送编号：[{}] 发送成功]", message.getId());
        return result;
    }

    @GetMapping("/send_delay")
    public boolean sendDelay() {
        // 创建 Message
        Demo01Message message = new Demo01Message();
        message.setId(new Random().nextInt());
        Message<Demo01Message> springMessage = MessageBuilder.withPayload(message)
                // 设置延迟时间，单位：毫秒
                .setHeader("x-delay", 5000)
                .build();
        // 发送消息
        boolean sendResult = mySource.demo01Output().send(springMessage);
        log.info("[sendDelay][发送消息完成, 结果 = {}]", sendResult);
        return sendResult;
    }
}