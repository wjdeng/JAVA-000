package com.task.java.week08.shardingsphereshardingdemo.service.Impl;

import com.task.java.week08.shardingsphereshardingdemo.entity.User;
import com.task.java.week08.shardingsphereshardingdemo.entity.UserExample;
import com.task.java.week08.shardingsphereshardingdemo.mapper.UserMapper;
import com.task.java.week08.shardingsphereshardingdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Wayne.Deng
 * @Date 2020/12/9 上午9:41
 * @Version 1.0
 **/

@Service
public class UserServiceImpl implements UserService {

	@SuppressWarnings("all")
	@Autowired
	private UserMapper userMapper;

	@Override
	public User insert(User user) {

		userMapper.insert(user);
		return user;
	}

	@Override
	public List<User> listAll() {

		UserExample userExample = new UserExample();
		userExample.createCriteria();
		List<User> userList = userMapper.selectByExample(userExample);
		return userList;
	}
}
