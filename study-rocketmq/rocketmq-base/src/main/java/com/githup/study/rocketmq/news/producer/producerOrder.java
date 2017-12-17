package com.githup.study.rocketmq.news.producer;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

import java.util.List;

/**
 * @author longhairen
 * @create 2017-12-16 14:24
 * @description
 **/
public class producerOrder {
    public static void main(String[] args) {
        try {
            DefaultMQProducer producer = new DefaultMQProducer("order_Producer");
            producer.setNamesrvAddr("192.168.199.245:9876");

            producer.start();

            // String[] tags = new String[] { "TagA", "TagB", "TagC", "TagD",
            // "TagE" };

            for (int i = 1; i <= 5; i++) {

                Message msg = new Message("TopicOrderTest", "order_1", "KEY" + i, ("order_1 " + i).getBytes());

                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer) arg;
                        int index = id % mqs.size();
                        return mqs.get(index);
                    }
                }, 0);

                System.out.println(sendResult);
            }
            for (int i = 1; i <= 5; i++) {

                Message msg = new Message("TopicOrderTest", "order_2", "KEY" + i, ("order_2 " + i).getBytes());

                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer) arg;
                        int index = id % mqs.size();
                        return mqs.get(index);
                    }
                }, 1);

                System.out.println(sendResult);
            }
            for (int i = 1; i <= 5; i++) {

                Message msg = new Message("TopicOrderTest", "order_3", "KEY" + i, ("order_3 " + i).getBytes());

                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer) arg;
                        int index = id % mqs.size();
                        return mqs.get(index);
                    }
                }, 2);

                System.out.println(sendResult);
            }

            producer.shutdown();
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
