package com.github.study.netty.base.serialize;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author longhairen
 * @create 2018-01-17 23:09
 * @description
 **/
public class NettyMessageEncoder<T> extends MessageToByteEncoder {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private final byte type = 0X00;
    private final byte flag = 0X0F;

    private Serializer serializer = SerializerFactory.getSerializer();
    private Class<T> clazz;
    public NettyMessageEncoder(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        try {
            out.writeByte(type);
            out.writeByte(flag);

            byte[] data = serializer.encode(msg);
            out.writeInt(data.length);
            out.writeBytes(data);

            //logger.info("write type:{}, flag:{}, length:{}", type, flag, data.length);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
