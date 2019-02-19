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

/**
 * Handles the communication with the client
 *
 */
public class ClientHandle implements Runnable {

    InputStream is = null;
    InputStreamReader isr = null;
    BufferedReader br = null;
    boolean isDone = false;

    Socket threadSocket = null;

    /**
     * Initializes the client socket
     * @param cs Socket of the client
     */
    public ClientHandle(Socket cs) {
    	
        this.threadSocket = cs;

    }

    /**
     * Responsible for communication.
     * Check if received message can be converted to JSON object. Skips message if not
     * TODO: define exit message, when client terminates. currently it is "bye"
     */
    @Override
    public void run()  {

        try {
            is = threadSocket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            String line = new String();
            GLogger.debug(Modules.NETWORK, "Waiting for client {" + threadSocket.toString() + "}");
            
            while ((!isDone) && (line = br.readLine()) != null) {
            	processInput(line);
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

	private void processInput(String line) {
		GLogger.debug(Modules.NETWORK, "Read from client {" + threadSocket.toString() + "} - " + line);
    	
    	JSONObject request =  JSONDecoder.decode(line);
    	if(request != null) {
    		JSONObject response = Dispatcher.messageFromNetwork(request, this.threadSocket);
    		Thread tx = new Thread(new ReplyToClient(response,this.threadSocket));
        	tx.start();
    	}

        if (line.equalsIgnoreCase("BYE")) {

        	GLogger.debug(Modules.NETWORK, "Disconnect client {" + threadSocket.toString() + "}");
            isDone = true;

        }
		
	}

}

