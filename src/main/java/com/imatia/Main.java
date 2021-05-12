package com.imatia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import com.imatia.domain.utils.MySQLConnection;

/* My database's table creation script
	drop table if exists users;
	create table users (
	id int unsigned auto_increment not null,
	first_name varchar(32) not null,
	last_name varchar(32) not null,
	date_created timestamp default now(),
	is_admin boolean,
	primary key (id);
	
	Also exception handling is a mess, I didn't
	do it properly at all
*/

public class Main {

	public static void main(String [] args) throws SQLException {
		final Connection connection = MySQLConnection.getConnection();

		// create a sql date object so we can use it in our INSERT statement
		final Calendar calendar = Calendar.getInstance();
		final java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

		// the mysql insert statement
		final String query = " insert into users (first_name, last_name, date_created, is_admin)" + " values (?, ?, ?, ?)";

		// create the mysql insert preparedstatement
		final PreparedStatement preparedStmt = connection.prepareStatement(query);
		preparedStmt.setString(1, "Raúl");
		preparedStmt.setString(2, "Carricoba");
		preparedStmt.setDate(3, startDate);
		preparedStmt.setBoolean(4, true);

		// execute the preparedstatement
		preparedStmt.execute();

		if (!connection.isClosed()) {
			connection.close();
		}
		System.out.println("DB execution test");
	}

}
