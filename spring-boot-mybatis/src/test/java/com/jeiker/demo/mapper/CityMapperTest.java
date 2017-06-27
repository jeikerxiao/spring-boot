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
 * @Date : 17/3/21 下午7:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CityMapperTest {

    @Autowired
    CityMapper cityMapper;

    @Test
    public void selectByPrimaryKey() throws Exception {
        City city = cityMapper.selectByPrimaryKey(1);
        assertTrue("错误:返回结果应该为1.", city.getId() == 1);
    }
}