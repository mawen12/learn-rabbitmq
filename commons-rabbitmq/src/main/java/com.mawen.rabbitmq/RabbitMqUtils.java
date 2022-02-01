package com.mawen.rabbitmq;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.mawen.rabbitmq.RabbitMQConstant.*;

/**
 * RabbitMQ 工具类
 *
 * @author mawen
 * @create 2022-01-30 19:27
 */
public final class RabbitMqUtils {
    private static final Logger log = LoggerFactory.getLogger(RabbitMqUtils.class);

    // 接收消息回调
    public static final DeliverCallback deliverCallback = (consumerTag, message) -> {
        System.out.println("接收的消息时：" + new String(message.getBody()));
        log.info("消息为{}.",new String(message.getBody()));
    };

    // 消息取消回调
    public static final CancelCallback cancelCallback = consumerTag -> {
        System.out.println("接收的消息时：");
        log.info("消息消费中断， {}.", consumerTag);
    };

    /**
     * @return 获取信道
     * @throws Exception
     */
    public static Channel getChannel() throws Exception {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 设置登录信息
        factory.setHost(IP);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        // 创建连接
        Connection connection = factory.newConnection();
        // 创建并返回信道
        return connection.createChannel();
    }

    /**
     * 关闭信道及连接
     * @param channel 信道
     * @param closeConnection 是否关闭连接
     * @throws Exception
     */
    public static void close(Channel channel, boolean closeConnection) throws Exception {
        channel.close();
        if (closeConnection)
            channel.getConnection().close();
    }



}
