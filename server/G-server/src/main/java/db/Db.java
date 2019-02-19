package db;

import java.sql.Connection;

import common.GException;

/**
 * Initializes the DB connection
 * 
 *
 */
public class Db {
	Connect connect;
	Connection connection;
	
	/**
	 * Initializes the Connect object and a Connection
	 * 
	 * @throws GException in case the connection can not be initialized
	 */
	public Db() throws GException {
		connect = new Connect();
		connection = connect.getConnection();
		if(connection == null) {
			throw new GException("Faild to establish DB connection", "DB-001");
		}
	}
}
