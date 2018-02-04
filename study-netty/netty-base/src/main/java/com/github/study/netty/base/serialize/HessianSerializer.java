package com.github.study.netty.base.serialize;

import java.io.IOException;

/**
 * @author longhairen
 * @create 2018-01-17 23:15
 * @description
 **/
public class HessianSerializer implements Serializer {
    @Override
    public byte[] encode(Object msg) throws IOException {
        return new byte[0];
    }

    @Override
    public <T> T decode(byte[] buf, Class<T> type) throws IOException {
        return null;
    }
}
