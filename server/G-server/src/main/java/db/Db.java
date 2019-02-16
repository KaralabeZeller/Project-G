package db;

import java.sql.Connection;

import common.GException;

public class Db {
	Connect connect;
	Connection connection;
	
	public Db() throws GException {
		connect = new Connect();
		connection = connect.getConnection();
		if(connection == null) {
			throw new GException("Faild to establish DB connection", "DB-001");
		}
	}
}
