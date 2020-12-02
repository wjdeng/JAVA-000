package com.task.java.week07.sqltraining.service;

import com.task.java.week07.sqltraining.model.User;

import java.util.List;

/**
 * @Author Wayne.Deng
 * @Date 2020/12/2 下午7:38
 * @Version 1.0
 **/
public interface UserService {


	public void insert(User user);

	public List<User> listAllUsers();
}
