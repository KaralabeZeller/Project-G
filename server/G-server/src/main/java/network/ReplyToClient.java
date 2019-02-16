package network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.JSONObject;

import common.constants.Modules;
import common.log.GLogger;

public class ReplyToClient implements Runnable {

    public JSONObject mString;
    public Socket mSocket;

    public ReplyToClient(JSONObject response, Socket sIn) {

        this.mString = response;
        this.mSocket = sIn;
    }

	public ReplyToClient(String string, Socket threadSocket) {
		this.mSocket = threadSocket;
		mString = new JSONObject();
		mString.put("response", "test - back");
	}

	@Override
    public void run() {

    	try {
            new PrintWriter(mSocket.getOutputStream(), true).println(mString.toString());
            GLogger.debug(Modules.NETWORK, "Answer to client {" + mSocket.getInetAddress() + "} - " + mString.toString());
    	} catch (IOException e) {
                    GLogger.error(Modules.NETWORK, "Error sending message to client: " +mSocket.getInetAddress());
        }
            
        

    }

}
