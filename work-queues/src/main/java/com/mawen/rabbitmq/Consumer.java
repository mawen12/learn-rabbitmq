package com.mawen.rabbitmq;

/**
 * 消费者
 *
 * @author mawen
 * @create 2022-01-30 20:22
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 2; i++) {
            new Thread(new Worker(), "Customer-" + i).start();
        }

        Thread.currentThread().join();
    }

}
