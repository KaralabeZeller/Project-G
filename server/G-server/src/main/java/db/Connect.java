package db;

import java.sql.*;

import common.constants.Modules;
import common.log.GLogger;

public final class Connect {
	
	public static Connection connection = null;
	
	
	private void Connect() {
		
	}
	
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
