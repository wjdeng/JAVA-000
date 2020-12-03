package com.task.java.week07.sqltraining.controller;

import com.task.java.week07.sqltraining.model.User;
import com.task.java.week07.sqltraining.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @Author Wayne.Deng
 * @Date 2020/12/1 下午3:34
 * @Version 1.0
 **/


@Controller
@RequestMapping(value = "sql-training/")
public class SqlTrainingController {

	@SuppressWarnings("all")
	@Resource
	private DataSource druid;

	@SuppressWarnings("all")
	@Autowired
	private UserService userService;

	@RequestMapping(value = "demo")
	@ResponseBody
	public String demo(){
		return "hello world";
	}

	@RequestMapping(value = "preparedStatement")
	@ResponseBody
	public String preparedStatement(){

		long startTime = System.currentTimeMillis();
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = druid.getConnection();
			String sql = "insert into db.tb_user(id, user_name, passport, nickname, id_card, email, created_at, updated_at) values(?,?,?,?,?,?,now(), now());";
			preparedStatement = connection.prepareStatement(sql);

			for (int i=1; i<1000000; i++){
				preparedStatement.setLong(1,i);
				preparedStatement.setString(2,"name_example");
				preparedStatement.setString(3, "12345");
				preparedStatement.setString(4,"nickname_example");
				preparedStatement.setString(5, i+"");
				preparedStatement.setString(6, "abc@bbc.com");
				preparedStatement.addBatch();
			}

			preparedStatement.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		long endTime = System.currentTimeMillis();
		return "preparedStatement 方式花费的时间共计"+(endTime-startTime)+"ms";
	}

	@RequestMapping(value = "statement")
	@ResponseBody
	public String statement(){
		long startTime = System.currentTimeMillis();
		Statement statement = null;
		Connection connection = null;
		try {
			connection = druid.getConnection();
			statement = connection.createStatement();

			for (int i=1; i<1000000; i++){
				String sql = "insert into db.tb_user(id, user_name, passport, nickname, id_card, email, created_at, updated_at) values("+ i +
						",\'name_example\',\'12345\',\'nickname_example\',"+i+",\'abc@bbc.com\',now(), now());";
				statement.execute(sql);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		long endTime = System.currentTimeMillis();
		return "statement方式花费的时间共计"+(endTime-startTime)+"ms";
	}

	@RequestMapping(value = "insert")
	@ResponseBody
	public String insert(){
		long now = System.currentTimeMillis();
		User user = new User();
		user.setCreatedAt(now);
		user.setEmail("wayne@163.com");
		user.setIdCard(String.valueOf(System.currentTimeMillis()));
		user.setNickname("wayne");
		user.setUserName("WayneDeng");
		user.setPassport("12345Abc");
		user.setUpdatedAt(now);
		userService.insert(user);
		System.out.println("=======新增接口=======");
		return user.toString();
	}

	@RequestMapping(value = "select")
	@ResponseBody
	public List<User> select(){
		System.out.println("=======查询接口=======");
		 List<User> users = userService.listAllUsers();
		return users;
	}

}
