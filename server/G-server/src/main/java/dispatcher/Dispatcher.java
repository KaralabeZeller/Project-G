package dispatcher;

import java.net.Socket;

import org.json.JSONObject;

import common.Message;
import usermanagement.UM;

/**
 * 
 * Routes messages between modules
 *  - recieves JSON objects from the network and routes it to the proper component based on its type
 *  - sends the response to the request from the inner modules to the network
 */
public final class Dispatcher {
	
	 
	public Dispatcher() {
		
	}
	
	/**
	 * Routes the JSON object to the appropriate inner module and sends the reponse back
	 * 
	 * @param json Input JSON object
	 * @param threadSocket 
	 * @return JSON object containing the response
	 */
	public static JSONObject messageFromNetwork(JSONObject json, Socket socket) {
		if(json.getString("type").equals("auth")) {
			json = UM.processRequest(json, socket);
		}
		
		if(json.getString("type").equals("party")) {
			json = UM.processRequest(json);
		}
		
		return json;
	}

	private static void messageToAuth(Message message) {
		// TODO Auto-generated method stub
		
	}

}
