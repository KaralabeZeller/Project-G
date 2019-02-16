package network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import common.constants.Modules;
import common.log.GLogger;

public class ReplyToClient implements Runnable {

    public String mString;
    public Socket mSocket;

    public ReplyToClient(String s, Socket sIn) {

        this.mString = s;
        this.mSocket = sIn;
    }

    @Override
    public void run() {

    	try {
            new PrintWriter(mSocket.getOutputStream(), true).println(mString);
    	
    	} catch (IOException e) {
                    GLogger.error(Modules.NETWORK, "Error sending message to client: " +mSocket.getInetAddress());
        }
            
        

    }

}
