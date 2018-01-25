package com.github.study.netty.base;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.List;

/**
 * @author longhairen
 * @create 2018-01-06 23:39
 * @description
 **/
public class NettyServer {

    public static void startServer(int port) throws Exception{
        //指定mainReactor
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //指定subReactor
        EventLoopGroup workGroup = new NioEventLoopGroup();
        //用户自定义的ThreadPool
        EventExecutorGroup threadPool = new DefaultEventExecutorGroup(1);

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioSctpServerChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)// 设置TCP参数
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new ByteToMessageDecoder() {
                                @Override
                                protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

                                }
                            });   // 解码处理器
                            p.addLast(new LineBasedFrameDecoder(1024));   // 编码处理器
                            p.addLast(threadPool, new ComputeWithSqlHandler());   // 附带SQL查询的计算
                        }
                    });
            // 绑定到本地端口等待客户端连接
            ChannelFuture f = bootstrap.bind(port).sync();

            // 等待接受客户端连接的Channel被关闭
            f.channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
            threadPool.shutdownGracefully();
        }
    }
}
