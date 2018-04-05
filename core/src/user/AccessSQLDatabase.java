package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccessSQLDatabase
{
	/* Verify the user information in the database. If correct information, extract the information of 
	 * the user so that he/she can login. If not,  */
	
	public static void main(String[] args)
	{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try
		{
			// Reflection
			// Telling JDK that when you compile code, this class will be there at runtime
			Class.forName("com.mysql.jdbc.Driver");
			// Get connection to the SQL Database. Use SSL=false to tell SQL to not give you the warning that you aren't using SSL
			// SQLException might get thrown here if there is an error
			String yourPassword = "Equyi86V";
			conn = DriverManager.getConnection("jdbc:mysql://localhost/ProjectUserDatabase?user=root&password=" + yourPassword + "&useSSL=false");
			// How to access the database in other users' computers?
			
			ps = conn.prepareStatement("SELECT u.username, u.userPassword, u.userLevel, u.userWins, u.userLosses "
										+ " FROM ProjectUserTable u " + " WHERE u.username=? " + ";");
										//+ " AND u.userPassword=? " + ";");
			
			String userToFind = "rayandrie"; // This is supposed to be the username passed in from the front end
			String passwordToMatch = "qwert"; // This is supposed to be the password passed in from the front end
			
			// USERNAMES SHOULD BE UNIQUE
			
			ps.setString(1, userToFind);
			
			// Embedding SQL Code
			rs = ps.executeQuery();
			
			// Get the data from the database so that user can log in
			
			String username = "";
			String password = "";
			int userLevel;
			int userWins;
			int userLosses;
			
			if (rs.next() == true) // User exists in the database
			{
				if (passwordToMatch.equals(rs.getString("userPassword")))
				{
					username = userToFind;
					password = passwordToMatch;
					userLevel = rs.getInt("userLevel");
					userWins = rs.getInt("userWins");
					userLosses = rs.getInt("userLosses");
					
					System.out.println("Username: " + username + ", Password: " + password + ", Level: "
										+ userLevel + ", User Wins: " + userWins + ", User Losses: " + userLosses);
				}
				else // Here, username exists, but password is wrong
				{
					System.out.println("Invalid Password!");
				}
			}
			else // User does not exist in the database
			{
				System.out.println("User does not exist in the Database!");
			}
		}
		catch (SQLException io)
		{
			System.out.println("sqle: " + io.getMessage());
		}
		catch (ClassNotFoundException io)
		{
			System.out.println("cnfe: " + io.getMessage());
		}
		finally
		{
			try
			{
				if (ps != null)
				{
					ps.close();
				}
				if (rs != null)
				{
					rs.close();
				}
				if (st != null)
				{
					st.close();
				}
				if (conn != null)
				{
					conn.close();
				}
			}
			catch (SQLException io)
			{
				System.out.println("Error in closing stream: " + io.getMessage());
			}
		}
	}
}