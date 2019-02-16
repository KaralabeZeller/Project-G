package network;

import common.GException;
import common.constants.Modules;
import common.log.GLogger;

public class Network {
	
	public Network() throws GException {
		GLogger.info(Modules.NETWORK, "Starting...");
		new Thread(new Server()).start();
		return;
	}
}
