package com.mawen.rabbitmq;

import com.rabbitmq.client.Channel;

import static com.mawen.rabbitmq.RabbitMQConstant.*;

/**
 * 消费者
 *
 * @author mawen
 * @create 2022-01-30 7:44
 */
public class HelloConsumer {

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        // 消费消息
        /**
         * 1.消费的队列
         * 2.消费成功后，是否自动应答
         * 3.消费者未成功消费的回调
         * 4.消费者取消消费的回调
         */
        channel.basicConsume(QUEUE_NAME_HELLO, AUTO_ACK, RabbitMqUtils.deliverCallback, RabbitMqUtils.cancelCallback);

        RabbitMqUtils.close(channel, CLOSE_CONNECTION);
    }

}
