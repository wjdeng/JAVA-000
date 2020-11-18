package com.javatask.week05.starteruser.controller;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.sql.*;

/**
 * @Author Wayne.Deng
 * @Date 2020/11/18 下午3:50
 * @Version 1.0
 **/

@Controller
@RequestMapping(value = "/hikari")
public class HikariController implements InitializingBean{

	@Autowired
	DataSource dataSource;

	Connection conn = null;

	@Override
	public void afterPropertiesSet() throws Exception {
		conn = dataSource.getConnection();
	}


	@RequestMapping(value = "/test")
	@ResponseBody
	public String testHikari(){

		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		try {
			// 执行查询
			ps = conn.prepareStatement("select * from test.tb_user where name = ? and age = ?");
			ps.setString(1, "wayne");
			ps.setInt(2, 27);

			rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String age = rs.getString("age");
				String temp = "用户id=" + id + ", 用户名=" + name + ", 用户年龄=" + age;
				sb.append(temp).append("\n");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();

	}


}
