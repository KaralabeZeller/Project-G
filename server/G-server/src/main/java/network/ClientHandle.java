package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import common.constants.Modules;
import common.log.GLogger;

public class ClientHandle implements Runnable {

    InputStream is = null;
    OutputStream os = null;
    InputStreamReader isr = null;
    BufferedReader br = null;
    PrintWriter pw = null;
    boolean isDone = false;

    Socket threadSocket = null;

    public ClientHandle(Socket cs) {
    	
        this.threadSocket = cs;

    }

    @Override
    public void run() {

        try {
            is = threadSocket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            os = threadSocket.getOutputStream();
            pw = new PrintWriter(os, true);

            String line = new String();
            GLogger.debug(Modules.NETWORK, "Waiting for client {" + threadSocket.getInetAddress() + "}");
            
            while ((!isDone) && (line = br.readLine()) != null) {
            	GLogger.debug(Modules.NETWORK, "Read from client {" + threadSocket.getInetAddress() + "} - " + line);
                if(line.equals("test")) {

                	GLogger.debug(Modules.NETWORK, "Answer to client {" + threadSocket.getInetAddress() + "} - Test-back");
                	Thread tx = new Thread(new ReplyToClient("Test-back",this.threadSocket));
                	tx.start();
                }

                if (line.equalsIgnoreCase("BYE")) {

                	GLogger.debug(Modules.NETWORK, "Disconnect client {" + threadSocket.getInetAddress() + "}");
                    isDone = true;

                }
            }
        } catch (IOException e) {

            GLogger.error(Modules.NETWORK, "IO Exception during client communication");
            e.printStackTrace();
        } finally {

                try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
                pw.close();
                
                ClientStore.removeClient(threadSocket);
                try {
					threadSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

        }

    }

}

