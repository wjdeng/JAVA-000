package com.javatask.week05.starteruser.jdbc;

import java.sql.*;

/**
 * @Author Wayne.Deng
 * @Date 2020/11/18 下午4:18
 * @Version 1.0
 **/
public class PreparedStatementMethod {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/test";
	private static final String USER = "root";
	private static final String PASSPORT = "12345Abc";
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";


	public static void main(String[] args){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 注册JDBC驱动
			Class.forName(JDBC_DRIVER);

			// 打开链接
			conn = DriverManager.getConnection(DB_URL,USER,PASSPORT);

			// 执行查询
			ps = conn.prepareStatement("select * from test.tb_user where name = ? and age = ?");
			ps.setString(1, "wayne");
			ps.setInt(2, 27);

			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String age = rs.getString("age");
				System.out.println("用户id=" + id + ", 用户名=" + name + ", 用户年龄=" + age);

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
}
