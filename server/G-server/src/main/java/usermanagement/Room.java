package usermanagement;

import java.util.LinkedList;
import java.util.List;

import common.IdGenerator;
import common.User;
import common.Users;
import common.constants.Modules;
import common.log.GLogger;

public class Room {
	private List<User> users;
	private int gameId;
	private String name;
	private int roomId;
	
	public Room(int id, String name) {
		
		users = new LinkedList<User>();
		gameId = IdGenerator.createIntID();
		this.name = name;
		User user = Users.getUserById(id); 
		if(user != null) {
			GLogger.info(Modules.UM, "Room created: " + name);
			GLogger.info(Modules.UM, "Added user to room: " + user.getName());
		}
	}
	
	public void addUser(User user) {
			users.add(user);
			GLogger.info(Modules.UM, "Added user to room: " + user.getName());
	}
	
	public int getId() {
		return roomId;
	}
}
