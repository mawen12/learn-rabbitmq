package com.mawen.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

/**
 * 生产者
 *
 * @author mawen
 * @create 2022-01-29 20:47
 */
public class HelloProducer {
    /**
     * 队列名称
     */
    private static final String QUEUE_NAME = "hello";

    private static final String IP = "192.168.190.190";

    private static final String USERNAME = "admin";

    private static final String PASSWORD = "admin";


    public static void main(String[] args) throws Exception {
        // 连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 登录信息
        factory.setHost(IP);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        // 创建连接
        Connection connection = factory.newConnection();
        // 获取信道
        Channel channel = connection.createChannel();
        // 借助信道来声明队列
        /**
         * 1.队列名称
         * 2.durable 队列里面的消息是否持久化，默认存储在内存中，不进行持久化
         * 3.exclusive 该队列中的消息是否只提供一个消费者消费，是否进行消息共享
         * 4.autoDelete 最后一个消费者断开连接之后，该队列是否自动删除
         * 5.其他参数
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World";
        // 借助信道来发送消息
        /**
         * 1.exchange 交换机名称
         * 2.routing 路由键名称
         * 3.props 属性
         * 4.body 二进制消息体
         */
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
        System.out.println("消息发送完毕");

        channel.close();
        connection.close();
    }

}
