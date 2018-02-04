package com.github.study.netty.base.serialize;

import java.io.IOException;

/**
 * @author longhairen
 * @create 2018-01-17 23:05
 * @description
 **/
public interface Serializer {

    byte[] encode(Object msg) throws IOException;

    <T> T decode(byte[] buf, Class<T> type) throws IOException;
}
