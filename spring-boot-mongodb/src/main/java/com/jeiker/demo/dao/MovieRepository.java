package com.jeiker.demo.dao;

import com.jeiker.demo.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @Author : xiao
 * @Date : 17/6/28 下午1:50
 */
public interface MovieRepository extends MongoRepository<Movie, String> {

    List<Movie> findAll();
}
