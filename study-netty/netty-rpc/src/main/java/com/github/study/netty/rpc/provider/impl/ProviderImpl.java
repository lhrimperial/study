package com.github.study.netty.rpc.provider.impl;

import com.github.study.netty.rpc.provider.Provider;

/**
 *
 */
public class ProviderImpl implements Provider {

    @Override
    public String printMsg(String msg) {
        System.out.println("----" + msg + "----");
        return "Ni Hao " + msg;
    }
}
