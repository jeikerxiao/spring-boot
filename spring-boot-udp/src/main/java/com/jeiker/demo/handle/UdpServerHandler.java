package com.jeiker.demo.handle;

import com.jeiker.demo.init.StartupEvent;
import com.jeiker.demo.model.UdpRecord;
import com.jeiker.demo.repository.mysql.UdpRepository;
import com.jeiker.demo.repository.redis.RedisRepository;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 接受UDP消息，并保存至redis的list链表中
 *
 * @Author : xiao
 * @Date : 17/9/30 上午11:28
 */
public class UdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private static final Logger logger = LoggerFactory.getLogger(UdpServerHandler.class);

    // 用来计算server接收到多少UDP消息
    private static int count = 0;

    @Override
    public void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {

        String receiveMsg = packet.content().toString(CharsetUtil.UTF_8);

        logger.info("Server <=== : {}", receiveMsg);

        UdpRecord udpRecord = new UdpRecord();

        // 判断接受到的UDP消息是否正确（未实现）
        if (StringUtils.isNotEmpty(receiveMsg)) {

            // 计算接收到的UDP消息的数量
            count++;

            // 1. 保存消息到 MySQL
            // 1.1 获取 UdpRepository 对象
            udpRecord.setUdpMsg(receiveMsg);
            udpRecord.setTime(getTime());
            UdpRepository udpRepository = (UdpRepository) StartupEvent.getBean(UdpRepository.class);
            // 1.2 将接收UDP消息的日志保存至 MySQL 中
            udpRepository.save(udpRecord);
            logger.info("MySQL <==== : {}", receiveMsg);

            // 2. 保存消息到 Redis
            // 2.1 获取 RedisRepository 对象
            RedisRepository redisRepository = (RedisRepository) StartupEvent.getBean(RedisRepository.class);
            // 2.2 将获取到的UDP消息保存至 Redis 的list列表中
            redisRepository.lpush("udp:msg", receiveMsg);
            redisRepository.setKey("UDPMsgNumber", String.valueOf(count));
            logger.info("Redis <==== : {}", receiveMsg);

            // 在这里可以返回一个UDP消息给对方，告知已接收到UDP消息，但考虑到这是UDP消息，此处可以注释掉
            // 方便调度，原接收数据返回
            ctx.write(new DatagramPacket(
                    Unpooled.copiedBuffer(receiveMsg, CharsetUtil.UTF_8), packet.sender()));
            logger.info("Server ===> : {}", receiveMsg);

        } else {
            logger.error("Received Error UDP Message:" + receiveMsg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        // We don't close the channel because we can keep serving requests.
    }

    private Timestamp getTime() {
        Date date = new Date();
        // Timestamp timestamp = new Timestamp(date.getTime());
        return new Timestamp(date.getTime());
    }
}
