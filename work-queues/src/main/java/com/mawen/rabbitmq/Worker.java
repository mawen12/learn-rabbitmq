package com.mawen.rabbitmq;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.mawen.rabbitmq.RabbitMQConstant.*;

/**
 * 消费者工作线程
 *
 * @author mawen
 * @create 2022-01-30 19:45
 */
public class Worker implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                // 获取信道
                Channel channel = RabbitMqUtils.getChannel();
                // 消费消息
                channel.basicConsume(QUEUE_NAME_HELLO, AUTO_ACK, RabbitMqUtils.deliverCallback, RabbitMqUtils.cancelCallback);
                // 此处需要等待一段时间，否则会因为未完成而提前关闭
                Thread.sleep(100L);
                RabbitMqUtils.close(channel, CLOSE_CONNECTION);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
