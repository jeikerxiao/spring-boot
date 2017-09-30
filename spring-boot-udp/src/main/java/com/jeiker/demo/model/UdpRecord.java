package com.jeiker.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @Author : xiao
 * @Date : 17/9/30 上午11:18
 */
@Entity
@Table
public class UdpRecord {
    // 消息id
    private long id;
    // 消息内容
    private String udpMsg;
    // 消息发送时间
    private Timestamp time;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUdpMsg() {
        return udpMsg;
    }

    public void setUdpMsg(String udpMsg) {
        this.udpMsg = udpMsg;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
