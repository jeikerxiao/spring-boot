package com.jeiker.demo.init;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author : xiao
 * @Date : 17/9/30 上午11:23
 */
@Component
@ConfigurationProperties(prefix = "sysfig")
public class SysConfig {
    // UDP消息接收端口
    private int UdpReceivePort;
    // 线程池大小
    private int CorePoolSize;
    // 线程池最大连接数
    private int MaxPoolSize;
    // 线程池保持激活秒数
    private int KeepAliveSeconds;
    // 线程池队列容量
    private int QueueCapacity;

    public int getUdpReceivePort() {
        return UdpReceivePort;
    }

    public void setUdpReceivePort(int udpReceivePort) {
        UdpReceivePort = udpReceivePort;
    }

    public int getCorePoolSize() {
        return CorePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        CorePoolSize = corePoolSize;
    }

    public int getMaxPoolSize() {
        return MaxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        MaxPoolSize = maxPoolSize;
    }

    public int getKeepAliveSeconds() {
        return KeepAliveSeconds;
    }

    public void setKeepAliveSeconds(int keepAliveSeconds) {
        KeepAliveSeconds = keepAliveSeconds;
    }

    public int getQueueCapacity() {
        return QueueCapacity;
    }

    public void setQueueCapacity(int queueCapacity) {
        QueueCapacity = queueCapacity;
    }
}
