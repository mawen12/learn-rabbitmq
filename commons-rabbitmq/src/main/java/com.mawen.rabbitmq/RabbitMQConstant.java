package com.mawen.rabbitmq;

/**
 * RabbitMQ 常量
 *
 * @author mawen
 * @create 2022-01-30 19:52
 */
public interface RabbitMQConstant {
    // 队列名称
    String QUEUE_NAME_HELLO = "hello";

    // 主机ip
    String IP = "192.168.190.190";

    // 用户名
    String USERNAME = "admin";

    // 密码
    String PASSWORD = "admin";

    // 持久化
    boolean DURABLE = true;

    // 消息不共享
    boolean EXCLUSIVE = true;

    // 自动删除
    boolean AUTO_DELETE = true;

    // 自动确认
    boolean AUTO_ACK = true;

    // 断开连接
    boolean CLOSE_CONNECTION = true;
}
