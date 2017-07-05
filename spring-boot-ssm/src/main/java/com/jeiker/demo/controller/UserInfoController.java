package com.jeiker.demo.controller;

import com.github.pagehelper.PageInfo;
import com.jeiker.demo.model.UserInfo;
import com.jeiker.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author : xiao
 * @Date : 17/7/4 下午4:11
 */
@RestController
@RequestMapping("/users")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping
    public PageInfo<UserInfo> getAll(UserInfo userInfo) {
        List<UserInfo> userInfoList = userInfoService.getAll(userInfo);
        return new PageInfo<UserInfo>(userInfoList);
    }

    @PostMapping("/add")
    public UserInfo add() {
        return new UserInfo();
    }

    @GetMapping("/view/{id}")
    public UserInfo view(@PathVariable Integer id) {
        UserInfo userInfo = userInfoService.getById(id);
        return userInfo;
    }

    @GetMapping("/delete/{id}")
    public ModelMap delete(@PathVariable Integer id) {
        ModelMap result = new ModelMap();
        userInfoService.deleteById(id);
        result.put("msg", "删除成功!");
        return result;
    }

    @PostMapping("/save")
    public ModelMap save(UserInfo userInfo) {
        ModelMap result = new ModelMap();
        String msg = userInfo.getId() == null ? "新增成功!" : "更新成功!";
        userInfoService.save(userInfo);
        result.put("userInfo", userInfo);
        result.put("msg", msg);
        return result;
    }
}
