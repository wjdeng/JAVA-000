package com.xin.shardingspherejdbcdemo.service.impl;


import com.xin.shardingspherejdbcdemo.entity.User;
import com.xin.shardingspherejdbcdemo.entity.UserExample;
import com.xin.shardingspherejdbcdemo.mapper.UserMapper;
import com.xin.shardingspherejdbcdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {


    @SuppressWarnings("all")
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> list() {
        UserExample userExample = new UserExample();
        userExample.createCriteria();
        List<User> users = userMapper.selectByExample(userExample);
        return users;
    }

    @Override
    public String save(User user) {

        userMapper.insert(user);
        return user.toString();
    }
}
