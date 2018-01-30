package com.githup.study.rocketmq.before.news.producer;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

/**
 * @author longhairen
 * @create 2017-12-16 9:59
 * @description
 **/
public class Producer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
//        pushProducer();
        pullProducer();
    }


    public static void justForTest() {
        DefaultMQProducer producer = new DefaultMQProducer("Producer");
        producer.setNamesrvAddr("192.168.58.163:9876");
        try {
            producer.start();
            Message msg = new Message("PushTopic",
                    "push",
                    "1",
                    "Just for test.".getBytes());

            SendResult result = producer.send(msg);
            System.out.println("id:" + result.getMsgId() +
                    " result:" + result.getSendStatus());

            msg = new Message("PushTopic",
                    "push",
                    "2",
                    "Just for test.".getBytes());

            result = producer.send(msg);
            System.out.println("id:" + result.getMsgId() +
                    " result:" + result.getSendStatus());

            msg = new Message("PullTopic",
                    "pull",
                    "1",
                    "Just for test.".getBytes());

            result = producer.send(msg);
            System.out.println("id:" + result.getMsgId() +
                    " result:" + result.getSendStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            producer.shutdown();
        }
    }

    public static void pullProducer() throws MQClientException,InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("PullConsumerGroup");
        producer.setNamesrvAddr("192.168.199.245:9876");
        producer.setInstanceName("PullConsumerGroup");
        producer.start();
        for (int i = 0; i < 1000; i++) {
            try {
                Message msg = new Message("TopicTestMapBody",//topic
                        "TagA",//tag
                        ("Hello RocketMQ, QuickStart" + i).getBytes()//body
                );
                SendResult sendResult = producer.send(msg);
                System.out.println(sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }
        producer.shutdown();
    }

    public static void pushProducer() throws MQClientException,InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("QuickStartProducer");
        producer.setNamesrvAddr("192.168.199.245:9876");
        producer.setInstanceName("QuickStartProducer");
        producer.start();
        for (int i = 0; i < 1000; i++) {
            try {
                Message msg = new Message("QuickStart",//topic
                        "TagA",//tag
                        ("Hello RocketMQ, QuickStart" + i).getBytes()//body
                );
                SendResult sendResult = producer.send(msg);
                System.out.println(sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }
        producer.shutdown();
    }
}
