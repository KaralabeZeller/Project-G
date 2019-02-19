package common;

import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import common.constants.Modules;
import common.log.GLogger;

public final class Users {
	private static List<User> users;
	
	public Users( ) {
		users = new LinkedList<User>();
	}
	
	public static  List<User> getUsers() {
		return users;
	}
	
	public static void addUser(User user) {
		if(!users.contains(user))
			users.add(user);
	}
	
	public static void removeUser(Socket socket) {
		for (Iterator<User> it = users.iterator(); it.hasNext(); ) {
			User client = it.next();
			if(client.getSocket().equals(socket)) {
				it.remove();
				GLogger.info(Modules.NETWORK, "Removed client: " + client.getSocket().toString());
				break;
			}
		}
	}
	
	public static User getUserBySocket(Socket s) {
		User user = null;
		for(User foundUser : users) {
			if(foundUser.getSocket().equals(s)) {
				user = foundUser;
				break;
			}
		}
		return user;
	}
	
	public static User getUserById(int Id) {
		User user = null;
		for(User foundUser : users) {
			if(foundUser.getId() == Id) {
				user = foundUser;
				break;
			}
		}
		return user;
	}

	public static User getUserByName(String name) {
		User user = null;
		for(User foundUser : users) {
			if(foundUser.getName().equals(name)) {
				user = foundUser;
				break;
			}
		}
		return user;
	}
}
