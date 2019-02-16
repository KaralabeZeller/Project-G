package authentication;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import db.Connect;

public final class Auth {
	List<User> users;
	
	public Auth() {
		users = new ArrayList<User>();
	}
	
	public static JSONObject processRequest(JSONObject json) {
		json.put("response", "DENIED");
		try {
			 PreparedStatement pst = Connect.connection.prepareStatement("SELECT 1 from ACCOUNT where username=? and password=?");
		            
		     pst.setString(1, json.getString("name"));
		     pst.setString(2, json.getString("password"));
		            
		            
			ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
            	json.remove("response");
               	json.put("response", "OK");
               	break;
            }
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return json;
		
	}
	
}
