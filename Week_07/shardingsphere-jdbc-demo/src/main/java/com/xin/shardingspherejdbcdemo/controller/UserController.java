package com.xin.shardingspherejdbcdemo.controller;

import com.xin.shardingspherejdbcdemo.entity.User;
import com.xin.shardingspherejdbcdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping(value = "/shardingsphere/")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/insert")
    public String saveUser() {
        long now = System.currentTimeMillis();
        User user = new User();
        user.setCreatedAt(now);
        user.setEmail("wayne@163.com");
        user.setIdCard(String.valueOf(System.currentTimeMillis()));
        user.setNickname("wayne");
        user.setUserName("WayneDeng");
        user.setPassport("12345Abc");
        user.setUpdatedAt(now);
        return userService.save(user);
    }

    @GetMapping("/select")
    public Object listUser() {
        return userService.list();
    }

}

