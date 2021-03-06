package com.githup.study.rocketmq.before.news.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.consumer.DefaultMQPullConsumer;
import com.alibaba.rocketmq.client.consumer.PullResult;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.message.MessageQueue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author longhairen
 * @create 2017-12-16 10:17
 * @description
 **/
public class PullConsumer {
    //Java缓存
    private static final Map<MessageQueue,Long> offseTable = new HashMap<MessageQueue,Long>();

    public static void main(String[]args) throws MQClientException {
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("PullConsumerGroup");
        consumer.setNamesrvAddr("192.168.199.245:9876");
        consumer.start();
        //拉取订阅主题的队列，默认队列大小是4
        Set<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues("TopicTestMapBody");
        for(MessageQueue mq  : mqs){
            System.out.println("Consume from the queue: " + mq);
            SINGLE_MQ : while(true){
                try{
                    PullResult pullResult = consumer.pullBlockIfNotFound(mq,null, getMessageQueueOffset(mq),32);
                    List<MessageExt> list=pullResult.getMsgFoundList();
                    if(list != null && list.size() < 100){
                        for(MessageExt msg : list){
                            System.out.println(JSON.toJSONString(msg.getBody()));
                        }
                    }
                    System.out.println(pullResult.getNextBeginOffset());
                    putMessageQueueOffset(mq,pullResult.getNextBeginOffset());
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
    private static void putMessageQueueOffset(MessageQueue mq,long offset){
        offseTable.put(mq,offset);
    }

    private static long getMessageQueueOffset(MessageQueue mq){
        Long offset=offseTable.get(mq);
        if(offset!=null){
            System.out.println(offset);
            return offset;
        }
        return 0;
    }
}
