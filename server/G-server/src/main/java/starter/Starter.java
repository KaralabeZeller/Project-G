package starter;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import network.Network;
import util.constants.Modules;
import util.log.GLogger;


/**
 * Initialises the main components:
 * - NETWORK:  			Handles client requests, responses
 * - DISPATCH: 			Interprets incoming requests, routes to the proper component
 * - DB: 				Postgres specific features
 * - UPDATE: 			Makes sure clients has the newest version of the game
 * - AUTHENTICATION: 	Handles user authentication and game creation
 * - GAME:				Busoness logic game handling
 */
public class Starter {
	
	private static GLogger logger;
	private static Network net;
	
	public static void main(String[] args) {
		
		logger = new GLogger(true);
	
		
		logger.info(Modules.NETWORK, "Starting...");
		
		// Start NETWORK component
		try {
			net = new Network();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		// Start DISPATCH component
		
		// Start DB component
		
		// Start UPDATE component
		
		// Start GAME component
		
		// Start AUTHENTICATION component

	}

}
