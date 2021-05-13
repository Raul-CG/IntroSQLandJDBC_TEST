package com.imatia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import com.imatia.domain.utils.MySQLConnection;

/* My database's table creation script:
	drop table if exists users;
	create table users (
	id int unsigned auto_increment not null,
	first_name varchar(32) not null,
	last_name varchar(32) not null,
	date_created timestamp default now(),
	is_admin boolean,
	primary key (id);

	Also exception handling is a mess.
 */

public class Main {

	public static void main(String [] args) throws SQLException {
		final Connection connection = MySQLConnection.getConnection();

		// create a SQL date object so we can use it in our INSERT statement
		final Calendar calendar = Calendar.getInstance();
		final java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

		// the mySQL insert statement
		final String insert = " insert into users (first_name, last_name, date_created, is_admin)" + " values (?, ?, ?, ?)";

		// create the mySQL insert preparedStatement
		final PreparedStatement preparedStmt = connection.prepareStatement(insert);
		preparedStmt.setString(1, "Raúl");
		preparedStmt.setString(2, "Carricoba");
		preparedStmt.setDate(3, startDate);
		preparedStmt.setBoolean(4, true);
		System.out.println("User inserted");

		// execute the preparedStatement
		preparedStmt.execute();

		final String select = "SELECT * FROM users";
		final Statement stmt = connection.createStatement();

		// execute the query, and get a java resultSet
		final ResultSet resultSet = stmt.executeQuery(select);

		Main.printResultSet(resultSet);
		System.out.println("Current users printed");

		final String delete = "delete from users where id > 1";
		final PreparedStatement deleteStmt = connection.prepareStatement(delete);

		deleteStmt.execute();
		System.out.println("All users except user with id = 1 deleted");

		final ResultSet resultSet2 = stmt.executeQuery(select);
		Main.printResultSet(resultSet2);
		System.out.println("Current users printed");

		if (!stmt.isClosed()) {
			stmt.close();
		}

		if (!connection.isClosed()) {
			connection.close();
		}
		System.out.println("\nDB execution test");
	}

	private static void printResultSet(ResultSet resultSet) throws SQLException {
		while (resultSet.next()) {
			final int id = resultSet.getInt("id");
			final String firstName = resultSet.getString("first_name");
			final String lastName = resultSet.getString("last_name");
			final Date dateCreated = resultSet.getDate("date_created");
			final boolean isAdmin = resultSet.getBoolean("is_admin");

			System.out.format("%s, %s, %s, %s, %s,\n", id, firstName, lastName, dateCreated, isAdmin);
		}
	}

}
