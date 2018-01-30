package com.githup.study.rocketmq.before.base.provider;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

import java.util.Date;

/**
 * @author longhairen
 * @create 2017-10-15 11:26
 * @description
 **/
public class Producer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("rmq-group");
        producer.setNamesrvAddr("192.168.204.128:9876");
        producer.setInstanceName("rmq-instance");
//        producer.setVipChannelEnabled(false);
        producer.start();
        try {
            for (int i = 0; i < 3; i++) {
                Message msg = new Message("TopicA-test",
                        "TagA",
                        (new Date() + "Hello RocketMQ ,QuickStart" + i)
                                .getBytes()// body
                );
                SendResult sendResult = producer.send(msg);
                System.out.println(sendResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }
}
