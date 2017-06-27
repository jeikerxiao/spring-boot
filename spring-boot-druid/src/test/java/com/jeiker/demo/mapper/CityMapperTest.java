package com.jeiker.demo.mapper;

import com.jeiker.demo.entity.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author : xiao
 * @Date : 17/3/21 上午10:56
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CityMapperTest {

    @Autowired
    CityMapper cityMapper;

    @Test
    public void findCityById() throws Exception {
        City city = cityMapper.selectByPrimaryKey(1);
        assertEquals(city.getId(), Integer.valueOf(1));
    }
}