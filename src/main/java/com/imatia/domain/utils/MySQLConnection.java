package com.imatia.domain.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (final ClassNotFoundException e1) {
			System.out.println("ClassNotFoundException: unable to find a class for \"com.mysql.cj.jdbc.Driver\".");
			e1.printStackTrace();
		}
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IntroSQLandJDBC", "user", "changeIt");
		} catch (final SQLException e) {
			System.out.println("SQLException: unable to establish connection to database.");
			e.printStackTrace();
		}
		return con;
	}
}
