package db;

import java.sql.*;

import common.constants.Modules;
import common.log.GLogger;

/**
 * 
 * Handles the Postgres conenction as a static object
 *
 */
public final class Connect {
	
	public static Connection connection = null;
	
	
	private void Connect() {
		
	}
	/**
	 * Checks if the connection has been initialized or not. If not creates a new connection. 
	 * 
	 * @return A Connection object as a singleton
	 */
	public Connection getConnection() {
		if(connection == null) {
			GLogger.info(Modules.DB, "Connecting to Project-G DB..");
			try {
				connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "projectg");
					 			
				GLogger.info(Modules.DB, "Connected to Project-G DB");
			} catch (Exception e) {
				GLogger.error(Modules.DB, "Connection failed to Project-G DB - " + e.getMessage() );
			}
		}
		return connection;
	}
	
	
}
