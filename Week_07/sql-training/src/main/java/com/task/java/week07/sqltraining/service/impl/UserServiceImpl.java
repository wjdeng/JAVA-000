package com.task.java.week07.sqltraining.service.impl;

import com.task.java.week07.sqltraining.config.DataSourceSelector;
import com.task.java.week07.sqltraining.config.DynamicDataSourceEnum;
import com.task.java.week07.sqltraining.dao.UserMapper;
import com.task.java.week07.sqltraining.model.User;
import com.task.java.week07.sqltraining.model.UserExample;
import com.task.java.week07.sqltraining.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author Wayne.Deng
 * @Date 2020/12/2 下午7:40
 * @Version 1.0
 **/
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	@DataSourceSelector(value = DynamicDataSourceEnum.MASTER)
	public void insert(User user) {
		userMapper.insert(user);
	}

	@Override
	@DataSourceSelector(value = DynamicDataSourceEnum.SLAVE)
	public List<User> listAllUsers() {

		UserExample userExample = new UserExample();
		userExample.createCriteria();
		List<User>  listUsers = userMapper.selectByExample(userExample);
		return listUsers;
	}
}
