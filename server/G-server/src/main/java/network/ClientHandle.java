package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

import org.json.JSONException;
import org.json.JSONObject;

import common.constants.Modules;
import common.log.GLogger;
import dispatcher.Dispatcher;

public class ClientHandle implements Runnable {

    InputStream is = null;
    InputStreamReader isr = null;
    BufferedReader br = null;
    boolean isDone = false;

    Socket threadSocket = null;

    public ClientHandle(Socket cs) {
    	
        this.threadSocket = cs;

    }

    @Override
    public void run()  {

        try {
            is = threadSocket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            String line = new String();
            GLogger.debug(Modules.NETWORK, "Waiting for client {" + threadSocket.getInetAddress() + "}");
            
            while ((!isDone) && (line = br.readLine()) != null) {
            	GLogger.debug(Modules.NETWORK, "Read from client {" + threadSocket.getInetAddress() + "} - " + line);
            	JSONObject request = null;
            	try {
            		 request = new JSONObject(line);
            	}catch(JSONException ex) {
            		GLogger.error(Modules.NETWORK, "Failed to decode JSON message: " + line);
            	}
            	
            	if(request != null) {
            		JSONObject response = Dispatcher.messageFromNetwork(request);
            		Thread tx = new Thread(new ReplyToClient(response,this.threadSocket));
                	tx.start();
            	}

                if (line.equalsIgnoreCase("BYE")) {

                	GLogger.debug(Modules.NETWORK, "Disconnect client {" + threadSocket.getInetAddress() + "}");
                    isDone = true;

                }
            }
        } catch (Exception e) {

            GLogger.error(Modules.NETWORK, "IO Exception during client communication");
        } finally {

                try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
                
                ClientStore.removeClient(threadSocket);
                try {
					threadSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

        }

    }

}

