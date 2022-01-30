package com.mawen.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * RabbitMQ 工具类
 *
 * @author mawen
 * @create 2022-01-30 19:27
 */
public final class RabbitMqUtils {

    /**
     * @return 获取信道
     * @throws Exception
     */
    public static Channel getChannel() throws Exception {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 设置登录信息
        factory.setHost("192.168.190.190");
        factory.setUsername("admin");
        factory.setPassword("admin");
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
