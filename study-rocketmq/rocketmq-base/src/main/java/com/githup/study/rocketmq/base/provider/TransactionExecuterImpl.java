package com.githup.study.rocketmq.base.provider;

import com.alibaba.rocketmq.client.producer.LocalTransactionExecuter;
import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.common.message.Message;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author longhairen
 * @create 2017-10-15 16:42
 * @description
 **/
public class TransactionExecuterImpl implements LocalTransactionExecuter {
    private AtomicInteger transactionIndex = new AtomicInteger(1);

    @Override
    public LocalTransactionState executeLocalTransactionBranch(final Message msg, final Object arg) {
        int value = transactionIndex.getAndIncrement();

        System.out.println("TransactionExecuterImpl : " + msg);
        if (value == 0) {
            throw new RuntimeException("Could not find db");
        } else if ((value % 5) == 0) {
            return LocalTransactionState.ROLLBACK_MESSAGE;
        } else if ((value % 4) == 0) {
            return LocalTransactionState.COMMIT_MESSAGE;
        }

        return LocalTransactionState.UNKNOW;
    }
}