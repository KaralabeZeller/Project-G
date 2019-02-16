package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import common.Client;
import common.constants.Modules;
import common.log.GLogger;

public final class ClientStore {
	static List<Client> activeClients = new ArrayList<Client>();
	
	public static Socket getSocketById(long id) {
		Socket socket = null;
		
		for(Client client : activeClients ) {
			if(client.getId() == id)
				socket = client.getSocket();
		}
		return socket;
	}
	
	public static Socket addClient(ServerSocket serverSocket) {
		Client client = null;
		try {
			client = new Client(serverSocket.accept());
		} catch (IOException e) {
			GLogger.error(Modules.NETWORK, "Failed to accept client");
			e.printStackTrace();
		}
		activeClients.add(client);
		GLogger.debug(Modules.NETWORK, "Created new client socket: " + client.getSocket().getInetAddress());
		return client.getSocket();
	}
	
	public static void updateClient(Socket socket, String user, long id) {
		for(Client client : activeClients ) {
			if(client.getSocket().getInetAddress().equals(socket.getInetAddress())) {
				client.setUserName(user);
				client.setId(id);
			}
		}
	}
	
	public static void removeClient(Socket socket) {
		for (Iterator<Client> it = activeClients.iterator(); it.hasNext(); ) {
			Client client = it.next();
			if(client.getSocket().getInetAddress().equals(socket.getInetAddress())) {
				it.remove();
				GLogger.info(Modules.NETWORK, "Removed client: " + client.getSocket().getInetAddress());
				break;
			}
		}
	}
}
