package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import common.GException;
import common.constants.Modules;
import common.constants.Net;
import common.log.GLogger;

/**
 * 
 * Java TCP Server multithreaded.
 * <p>
 * Every TCP client is handled in a separate Thread.
 * </p>
 *
 */
public class Server implements Runnable {

    ServerSocket ss = null;
    ClientStore clientStore;
    
    /**
     * Initializes the ClientStore object
     * 
     * @throws GException
     */
    public Server() throws GException {
    	clientStore = new ClientStore();
        
    }
    
    /**
     * If a client is connected, it's socket is added to the ClientSore, and a new ClientHandler is started on a new Thread
     * @throws GException 
     */
    private void manageConnection() {
    	Socket cs = clientStore.addClient(ss); 
    	
    	GLogger.info(Modules.NETWORK, "Connected to client: " + cs.toString());
        
    	Runnable cr = new ClientHandle(cs);
    	
    	Thread t = new Thread(cr);
    	t.setDaemon(true);
    	t.start();	
	}

    /**
     * Infinite loop, which waits for connecting peers
     */
	@Override
	public void run() {
		try {
		
		    ss = new ServerSocket(Net.PORT);
		    GLogger.info(Modules.NETWORK, "Server started on port " + Net.PORT +"! Waiting for clients...");
		    while (true) {
				manageConnection();


		    }
		
		} catch (IOException ex) {
			GLogger.error(Modules.NETWORK, "Server socket failed to start");		    
		} finally {
		
		    try {
		        ss.close();
		    } catch (Exception ex) {
		    }
		}
	}
}

