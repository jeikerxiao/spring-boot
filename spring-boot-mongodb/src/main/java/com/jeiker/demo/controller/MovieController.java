package com.jeiker.demo.controller;

import com.jeiker.demo.dao.MovieRepository;
import com.jeiker.demo.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author : xiao
 * @Date : 17/6/28 下午1:51
 */
@RestController
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/movie/all")
    public List<Movie> getAll() {
        List<Movie> movieList = movieRepository.findAll();
        return movieList;
    }

}
