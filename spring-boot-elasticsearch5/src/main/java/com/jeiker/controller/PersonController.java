package com.jeiker.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.jeiker.model.Person;
import com.jeiker.service.PersonService;

@RestController
@Api("用户")
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personservice;

    /**
     * 新增
     */
    @ApiOperation("新增用户")
    @PostMapping("/save/person")
    public Object save(@RequestBody Person person) {
        logger.info("name=" + person.getName());
        logger.info("sex=" + person.getSex());
        logger.info("age=" + person.getAge());
        logger.info("introduce=" + person.getIntroduce());
        logger.info("birthday=" + person.getBirthday());
        return personservice.savePerson(person);
    }

    /**
     * 聚合查询
     */
    @ApiOperation("聚合查询用户")
    @PostMapping("/query/person")
    public Object query(@RequestBody Person person) {
        return personservice.queryPerson(person);
    }

    /**
     * 更新
     *
     * @param person 更新对象
     * @return
     */
    @ApiOperation("修改用户")
    @PostMapping("/update/person")
    public Object updatePerson(@RequestBody Person person) {
        return personservice.updatePerson(person);
    }

    /**
     * 删除
     *
     * @param id 删除的数据id
     * @return
     */
    @ApiOperation("删除用户")
    @PostMapping("/del/person/{id}")
    public Object delPerson(@PathVariable("id") String id) {
        return personservice.delPerson(id);
    }

    /**
     * 获取数据
     *
     * @param id 想要获取的数据id
     * @return
     */
    @ApiOperation("查询用户")
    @GetMapping("/person/{id}")
    public Object getPerson(@PathVariable("id") String id) {
        return personservice.findPerson(id);
    }
}
