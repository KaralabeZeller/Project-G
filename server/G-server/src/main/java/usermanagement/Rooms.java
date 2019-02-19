package usermanagement;

import java.util.LinkedList;
import java.util.List;

public class Rooms {
	private List<Room> rooms;
	
	public Rooms() {
		rooms = new LinkedList<Room>();
	}
	
	public void addRoom(Room room) {
		rooms.add(room);
	}
}
