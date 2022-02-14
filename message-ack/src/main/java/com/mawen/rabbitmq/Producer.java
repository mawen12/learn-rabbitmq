package com.mawen.rabbitmq;

import com.rabbitmq.client.Channel;

import java.util.Scanner;

import static com.mawen.rabbitmq.RabbitMQConstant.*;


/**
 * 生产者
 *
 * @author mawen
 * @create 2022-02-11 20:45
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        // 获取连接
        Channel channel = RabbitMqUtils.getChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME_HELLO, !DURABLE, !EXCLUSIVE, !AUTO_DELETE, null);
        // 接收控制台的消息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String message = scanner.next();

        }
    }


}
