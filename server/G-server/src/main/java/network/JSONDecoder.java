package network;

import org.json.JSONException;
import org.json.JSONObject;

import common.constants.Modules;
import common.log.GLogger;

public final class JSONDecoder {
	
	public static JSONObject decode(String jsonString) {
		JSONObject request = null;
    	try {
    		 request = new JSONObject(jsonString);
    	}catch(JSONException ex) {
    		GLogger.error(Modules.NETWORK, "Failed to decode JSON message: " + jsonString);
    	}
    	return request;
    	
	}
}
