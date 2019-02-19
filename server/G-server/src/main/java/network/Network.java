package network;

import common.GException;
import common.constants.Modules;
import common.log.GLogger;

/**
 * 
 * Initializes the network component
 *
 */
public class Network {
	
	/**
	 * Initializes the network component
	 * 
	 * @throws GException In case of network issues.
	 */
	public Network() throws GException {
		GLogger.info(Modules.NETWORK, "Starting...");
		new Thread(new Server()).start();
		return;
	}
}
