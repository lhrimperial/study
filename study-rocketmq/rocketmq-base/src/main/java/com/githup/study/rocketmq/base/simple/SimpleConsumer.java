package com.githup.study.rocketmq.base.simple;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.consumer.DefaultMQPullConsumer;
import com.alibaba.rocketmq.client.consumer.PullResult;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.message.MessageQueue;

import java.util.List;
import java.util.Set;

/**
 *
 */
public class SimpleConsumer {

    public static void main(String[] args)  throws MQClientException {
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("simple_comsumer_group");
        consumer.setNamesrvAddr("192.168.142.128:9876");
        consumer.setInstanceName("SimpleConsumer");
        consumer.start();

        //拉取订阅主题的队列，默认队列大小是4
        Set<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues("TopicTest");
        for(MessageQueue mq  : mqs){
            System.out.println("Consume from the queue: " + mq);
            long offset = 0;
            SINGLE_MQ : while(true){
                try{
                    PullResult pullResult = consumer.pullBlockIfNotFound(mq,null, offset,100);
                    List<MessageExt> list=pullResult.getMsgFoundList();
                    offset = pullResult.getNextBeginOffset();
                    for(MessageExt msg : list){
                        System.out.println(JSON.toJSONString(msg.getBody()));
                    }
                    switch(pullResult.getPullStatus()){
                        case FOUND:
                            //TODO
                            break;
                        case NO_MATCHED_MSG:
                            break;
                        case NO_NEW_MSG:
                            break SINGLE_MQ;
                        case OFFSET_ILLEGAL:
                            break;
                        default:
                            break;
                    }
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        consumer.shutdown();
    }
}
