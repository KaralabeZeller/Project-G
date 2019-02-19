package usermanagement;

import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import common.User;
import common.Users;
import db.Connect;
import db.Queries;
import dispatcher.Dispatcher;

/**
 * Handles client user authentication
 *
 */
public final class UM {
	Users users;
	RoomManager roommanager;
	
	
	public UM() {
		users = new Users();
		roommanager = new RoomManager();
	}
	
	/**
	 * Processes the request JSON object
	 * @param json JSON object of the request
	 * @param socket 
	 * @return JSON opject containing the response to the request
	 */
	public static JSONObject processRequest(JSONObject json, Socket socket) {
		
		try {
			 PreparedStatement pst = Connect.connection.prepareStatement(Queries.SELECT_USER_ID);
		            
		     pst.setString(1, json.getString("name"));
		     pst.setString(2, json.getString("password"));
		            		            
			ResultSet resultSet = pst.executeQuery();
			if (resultSet.next() == false) {
				json.put("response", new JSONObject().put("status", "FAILED").put("message", "Username/Password incorrect")); 
			} else 
			{ 
				do { 
					int user_id = resultSet.getInt(1);
	               	json.put("response", new JSONObject().put("status", "OK").put("user_id", user_id));
	               	User user = Users.getUserBySocket(socket);
	               	user.updateUser(json.getString("name"), user_id);
	               	
	               	break; 
				} while (resultSet.next()); }

			
			resultSet.close();
            pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return json;
		
	}

	public static JSONObject processRequest(JSONObject json) {
		if(json.getString("sub_type").equals("create")) {
			Room room = new Room(json.getInt("user_id"), json.getString("room_name"));
			json.put("response", new JSONObject().put("status", "OK").put("user_id", room.getId()));
			if(!json.getJSONArray("members").isNull(0)) {
				JSONArray members = json.getJSONArray("members");
				for(int i = 0; i < members.length(); i++) {
					String member = members.getString(i);
					room.addUser(Users.getUserByName(member));
					inviteUser(room);
				}
			}
		}
		return json;
	}

	private static void inviteUser(Room room) {
		
		
	}
	
}
