package com.mawen.rabbitmq;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 消费者
 *
 * @author mawen
 * @create 2022-01-30 7:44
 */
public class HelloConsumer {
    private static final Logger log = LoggerFactory.getLogger(HelloConsumer.class);

    /**
     * 队列名称
     */
    private static final String QUEUE_NAME = "hello";

    private static final String IP = "192.168.190.190";

    private static final String USERNAME = "admin";

    private static final String PASSWORD = "admin";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        // 消费消息
        /**
         * 1.消费的队列
         * 2.消费成功后，是否自动应答
         * 3.消费者未成功消费的回调
         * 4.消费者取消消费的回调
         */
        channel.basicConsume(QUEUE_NAME, true, deliverCallback(), cancelCallback());
        log.info("消息接收完毕");

        RabbitMqUtils.close(channel, true);
    }

    // 接受消息时的回调
    private static DeliverCallback deliverCallback() {
        return (consumerTag, message) -> log.info(new String(message.getBody(), UTF_8));
    }

    // 取消消息时的回调
    private static CancelCallback cancelCallback() {
        return consumerTag -> {
            log.info("消费消息被中断");
        };
    }

}
