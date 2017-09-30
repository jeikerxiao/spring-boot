package com.jeiker.demo.init;

import com.jeiker.demo.handle.UdpServerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author : xiao
 * @Date : 17/9/30 上午11:26
 */
@Component
public class UdpServer {

    private static final Logger logger = LoggerFactory.getLogger(UdpServer.class);

    private static final int PORT = Integer.parseInt(System.getProperty("port", "8880"));

    @Async("myTaskAsyncPool")
    public void run(int udpReceivePort) {

        EventLoopGroup group = new NioEventLoopGroup();
        logger.info("Server start!  Udp Receive msg Port:" + udpReceivePort);

        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new UdpServerHandler());   // 设置服务端接收消息的 Handler (保存消息到 mysql 和 redis 中)

            b.bind(udpReceivePort).sync().channel().closeFuture().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

}
