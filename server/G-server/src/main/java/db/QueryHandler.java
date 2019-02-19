package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public final class QueryHandler {
	
	public static ResultSet getResult(String query, List<String> params) {
		ResultSet resultSet = null;
		try {
			 PreparedStatement pst = Connect.connection.prepareStatement(Queries.SELECT_USER_ID);
		     
			 for(int i = 0; i < params.size(); i++)
			 {
				 pst.setString(i, params.get(i));
			 }      
		            
			 resultSet = pst.executeQuery();
           
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return resultSet;
	}
	
}
