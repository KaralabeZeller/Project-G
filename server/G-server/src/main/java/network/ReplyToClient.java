package network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.JSONObject;

import common.constants.Modules;
import common.log.GLogger;

/**
 * Handles messages sent to the client in a separate thread
 *
 */
public class ReplyToClient implements Runnable {

    public JSONObject mString;
    public Socket mSocket;

    /**
     * Initializes the response object
     * 
     * @param response JSON object containing the response to the clients request
     * @param sIn Socket of the client 
     */
    public ReplyToClient(JSONObject response, Socket sIn) {

        this.mString = response;
        this.mSocket = sIn;
    }

    /**
     * @deprecated
     * Initializes the response object
     * 
     * @param response String object containing the response to the clients request
     * @param sIn Socket of the client 
     */
	public ReplyToClient(String string, Socket threadSocket) {
		this.mSocket = threadSocket;
		mString = new JSONObject();
		mString.put("response", "test - back");
	}

	/**
	 * Sends the response string trough the client socket
	 */
	@Override
    public void run() {

    	try {
            new PrintWriter(mSocket.getOutputStream(), true).println(mString.toString());
            GLogger.debug(Modules.NETWORK, "Answer to client {" + mSocket.toString() + "} - " + mString.toString());
    	} catch (IOException e) {
                    GLogger.error(Modules.NETWORK, "Error sending message to client: " +mSocket.getInetAddress());
        }
            
        

    }

}
