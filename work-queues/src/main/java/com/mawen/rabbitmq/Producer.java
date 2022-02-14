package com.mawen.rabbitmq;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

import static com.mawen.rabbitmq.RabbitMQConstant.*;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 生产者
 *
 * @author mawen
 * @create 2022-01-30 20:33
 */
public class Producer {
    private static final Logger log = LoggerFactory.getLogger(Producer.class);

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        channel.queueDeclare(QUEUE_NAME_HELLO, !DURABLE, !EXCLUSIVE, !AUTO_DELETE, null);

        // 接收控制台的消息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String message = scanner.next();
            channel.basicPublish("", QUEUE_NAME_HELLO, null, message.getBytes(UTF_8));
            log.info("发送消息完成：{}.", message);
        }
    }
}
