package com.jeiker.demo.repository;

import com.jeiker.demo.domain.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author : xiao
 * @Date : 17/6/30 上午11:02
 */
public interface CityRepository extends ElasticsearchRepository<City, Integer> {
}
