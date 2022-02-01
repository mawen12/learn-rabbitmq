package com.mawen.rabbitmq;

import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;

import static com.mawen.rabbitmq.RabbitMQConstant.*;

/**
 * 生产者
 *
 * @author mawen
 * @create 2022-01-29 20:47
 */
public class HelloProducer {
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        // 借助信道来声明队列
        /**
         * 1.队列名称
         * 2.durable 队列里面的消息是否持久化，默认存储在内存中，不进行持久化
         * 3.exclusive 该队列中的消息是否只提供一个消费者消费，是否进行消息共享
         * 4.autoDelete 最后一个消费者断开连接之后，该队列是否自动删除
         * 5.其他参数
         */
        channel.queueDeclare(QUEUE_NAME_HELLO, !DURABLE, !EXCLUSIVE, !AUTO_DELETE, null);
        String message = "Hello World";
        // 借助信道来发送消息
        /**
         * 1.exchange 交换机名称
         * 2.routing 路由键名称
         * 3.props 属性
         * 4.body 二进制消息体
         */
        channel.basicPublish("", QUEUE_NAME_HELLO, null, message.getBytes(StandardCharsets.UTF_8));
        System.out.println("消息发送完毕");

        RabbitMqUtils.close(channel, CLOSE_CONNECTION);
    }

}
