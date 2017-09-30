package com.jeiker.demo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.SocketUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 此处的UDP发送的客户端是官方公布的客户端
 * 在此只是为了测试，不做修改，直接引用
 *
 * @Author : xiao
 * @Date : 17/9/30 上午11:33
 */
public final class UdpClient {

    private static final Logger logger = LoggerFactory.getLogger(UdpClient.class);


    static final int PORT = Integer.parseInt(System.getProperty("port", "8993"));

    public static void main(String[] args) throws Exception {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new UdpClientHandler());

            Channel ch = b.bind(0).sync().channel();

            String UdpMsg = "this a message form client !";

            logger.info("Client ===> : {}", UdpMsg);

            // Broadcast the QOTM request to port 8880.
            ch.writeAndFlush(new DatagramPacket(
                    Unpooled.copiedBuffer(UdpMsg, CharsetUtil.UTF_8),
                    SocketUtils.socketAddress("localhost", PORT))).sync();

            // UdpClientHandler 接收到服务器响应的消息时，会关闭 DatagramChannel (数据报通道)
            // 如果 DatagramChannel (数据报通道) 没有在 5秒内关闭，那么打印错误日志，并退出
            if (!ch.closeFuture().await(5000)) {
                logger.error("Server response timed out.");
            }
        } finally {
            group.shutdownGracefully();
        }
    }
}
