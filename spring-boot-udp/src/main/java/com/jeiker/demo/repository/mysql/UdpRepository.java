package com.jeiker.demo.repository.mysql;

import com.jeiker.demo.model.UdpRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author : xiao
 * @Date : 17/9/30 上午11:20
 */
public interface UdpRepository extends JpaRepository<UdpRecord, Long> {

}
