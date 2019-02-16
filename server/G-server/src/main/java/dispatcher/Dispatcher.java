package dispatcher;

import org.json.JSONObject;

import authentication.Auth;
import common.Message;

public final class Dispatcher {
	
	 
	public Dispatcher() {
		
	}
	
	public static JSONObject messageFromNetwork(JSONObject json) {
		if(json.getString("type").equals("auth")) {
			json = Auth.processRequest(json);
		}
		return json;
	}

	private static void messageToAuth(Message message) {
		// TODO Auto-generated method stub
		
	}
}
