package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import common.User;
import common.Users;
import common.constants.Modules;
import common.log.GLogger;

/**
 * 
 * Stores the active clients
 *
 */
public final class ClientStore {
	private static Users users;
	
	public ClientStore() {
		users = new Users();
	}
	
	/**
	 * Adds client to the store
	 * 
	 * @param serverSocket Socket of the server
	 * @return the Socket of the accepted client
	 */
	public static Socket addClient(ServerSocket serverSocket) {
		User user = null;
		try {
			user = new User(serverSocket.accept());
		} catch (IOException e) {
			GLogger.error(Modules.NETWORK, "Failed to accept client");
			e.printStackTrace();
		}
		
		users.addUser(user);
		GLogger.debug(Modules.NETWORK, "Created new client socket: " + user.getSocket().toString());
		return user.getSocket();
		
	}
	
	/**
	 * Remoes the client from the list after it disconnects
	 * @param socket The socket of the client
	 */
	public static void removeClient(Socket socket) {
		users.removeUser(socket);
	}
}
