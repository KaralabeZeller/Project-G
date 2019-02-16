package starter;

import common.GException;
import common.constants.Modules;
import common.log.GLogger;
import db.Db;
import network.Network;


/**
 * Initialises the main components:
 * - NETWORK:  			Handles client requests, responses
 * - DISPATCH: 			Interprets incoming requests, routes to the proper component
 * - DB: 				Postgres specific features
 * - UPDATE: 			Makes sure clients has the newest version of the game
 * - AUTHENTICATION: 	Handles user authentication and game creation
 * - GAME:				Business logic game handling
 */
public class Starter {
	
	private static GLogger logger;
	private static Network net;
	private static Db db;
	
	public static void main(String[] args) {
		
		logger = new GLogger(true);
	
		
		GLogger.info(Modules.STARTER, "NEtWORK 		[Starting]");
		// Start NETWORK component
		try {
			net = new Network();
		} catch (GException e) {
			GLogger.info(Modules.STARTER, "NEtWORK 		[Failed] - " + e.getMessage() + " - " + e.getCode());
			System.exit(1);
		}
		
		GLogger.info(Modules.STARTER, "NEtWORK 		[Started]");
		// Start DISPATCH component
		
		GLogger.info(Modules.STARTER, "DB 			[Starting]");
		
		// Start DB component
		try {
			db = new Db();
		} catch (GException e) {	
			GLogger.info(Modules.STARTER, "DB 			[Failed] - " + e.getMessage() + " - " + e.getCode());
			System.exit(1);
		}
		
		GLogger.info(Modules.STARTER, "DB 			[Started]");
		// Start UPDATE component
		
		// Start GAME component
		
		// Start AUTHENTICATION component

	}

}
