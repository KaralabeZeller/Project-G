package common;

import java.net.Socket;

import common.constants.Modules;
import common.log.GLogger;

/**
 * Contains info of the connected TCP client and logged in user
 * 
 *
 */
public class User {
	private Socket socket;
	private String name;
	private long id;
	
	/**
	 * Initializes client by the socket
	 * @param socket Socket of the connected peer
	 */
	public User(Socket socket) {
		this.socket = socket;
	}
	
	/**
	 * @return the socket
	 */
	public Socket getSocket() {
		return socket;
	}	
	
	public void updateUser(String name, long id) {
		this.name = name;
		this.id = id;
		GLogger.info(Modules.UM, "User updated with: " + name + ", ID: " + id);
	}
	
	public long getId() {
		return id;
	}
	
	@Override 
	public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        
        User user = (User) o;
        
        return user.getId() == id || user.getSocket().equals(socket);
    }
	
	@Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.socket != null ? this.socket.hashCode() : 0);
        return hash;
	}

	public String getName() {
		return name;
	}
}
