package com.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBManager {
	// this is our Dao class it will contain all the database related code

	static final String DBDriver = "com.mysql.jdbc.Driver";
	static final String DBUrl = "jdbc:mysql://localhost:3306/healthcare";
	static final String DBUser = "root";
	static final String DBPass = "";

	static {
		try {
			Class.forName(DBDriver);
		} catch (ClassNotFoundException e) {
			System.exit(0);
		}
	}

	public static int getUser(String username, String password, String email, String address, String phoneNo,
			String age, String sex) {

		int i = 0;
		try {

			Connection con = DriverManager.getConnection(DBUrl, DBUser, DBPass);

			String sql = "insert into user (username,password,email,address,phoneNo,age,sex)values(?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setString(4, address);
			ps.setString(5, phoneNo);
			ps.setString(6, age);
			ps.setString(7, sex);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return i;
	}
}
