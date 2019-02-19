package starter;

import common.GException;
import common.constants.Modules;
import common.log.GLogger;
import db.Db;
import network.Network;
import usermanagement.UM;


/**
 * Initialises the main components:
 * - NETWORK:  			Handles client requests, responses
 * - DB: 				Postgres specific features
 * - UPDATE: 			Makes sure clients has the newest version of the game
 * - AUTHENTICATION: 	Handles user authentication and game creation
 * - GAME:				Business logic game handling
 */
public class Starter {
	
	private static GLogger logger;
	private static Network net;
	private static UM um;
	private static Db db;
	
	public static void main(String[] args) {
		
		logger = new GLogger(true);
	
		
		initDb(); 		// Start DB component
		initAuth();		// Start AUTHENTICATION component
		initNetwork(); 	// Start NETWORK component
		
		
		// Start UPDATE component
		
		// Start GAME component
		
		

	}

	private static void initAuth() {
		GLogger.info(Modules.STARTER, "DB 			[Starting]");
		
		um = new UM();
		
		GLogger.info(Modules.STARTER, "DB 			[Started]");
		
	}

	private static void initDb() {
		GLogger.info(Modules.STARTER, "DB 			[Starting]");
		
		try {
			db = new Db();
		} catch (GException e) {	
			GLogger.info(Modules.STARTER, "DB 			[Failed] - " + e.getMessage() + " - " + e.getCode());
			System.exit(1);
		}
		
		GLogger.info(Modules.STARTER, "DB 			[Started]");
		
	}

	private static void initNetwork() {
		GLogger.info(Modules.STARTER, "NETWORK 		[Starting]");
		
		try {
			net = new Network();
		} catch (GException e) {
			GLogger.info(Modules.STARTER, "NETWORK 		[Failed] - " + e.getMessage() + " - " + e.getCode());
			System.exit(1);
		}	
		GLogger.info(Modules.STARTER, "NETWORK 		[Started]");
		
	}
	
	

}
