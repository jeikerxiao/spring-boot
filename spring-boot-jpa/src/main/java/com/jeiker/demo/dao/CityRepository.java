package com.jeiker.demo.dao;

import com.jeiker.demo.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author : xiao
 * @Date : 17/3/20 下午2:03
 */
public interface CityRepository extends JpaRepository<City, Integer>{
}
